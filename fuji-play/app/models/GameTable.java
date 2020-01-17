package models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.adapters.CardAdapter;
import models.adapters.GameAdapter;
import models.adapters.HandAdapter;
import models.adapters.PlayerAdapter;
import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;
import services.GameEventService;
import services.GameService;
import services.HandService;

public class GameTable {
	
    private static final Gson gsonBuilder = new GsonBuilder()
            .registerTypeAdapter(Game.class, new GameAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Hand.class, new HandAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .create();	
    
    final ArchivedEventStream<JsonObject> gameEvents = new ArchivedEventStream<JsonObject>(1);
    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<JsonObject> join(String playerNickName) {  
    	System.out.println(playerNickName+" ready to play");
        return gameEvents.eventStream();
    }
    public void playCard(Long handId, Integer index, String uuid) {
    	Game game = GameService.findByUUID(uuid);
		Hand hand = HandService.getById(handId);
		User player = hand.player;
		System.out.println(player.nickName+" playCard");
		GameService.playCard(hand, hand.cards.get(index));
		GameEventService.addGameEvent(game, player.nickName+" played card "+hand.cardP.value);
		Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
		GameService.ruleCompareAndDiscard(game, currentHandPlayer);
		GameService.nextPlayer(game, hand);
		GameService.ruleFullTurn(game);	
		// parse game to JSON object
		JsonObject obj = new JsonParser().parse(gsonBuilder.toJson(game)).getAsJsonObject();
		gameEvents.publish(obj);
    }
    public JsonObject gameToJSON(Game game) {
    	JsonObject obj = new JsonObject();
    	obj.addProperty("currentPlayerId", game.currentPlayer.id);
    	obj.addProperty("isFinisched", game.isFinished);
    	
    	obj.add("hands", handsToJSON(game.hands));
    	return obj;
    }
    public JsonObject handsToJSON(List<Hand> hands) {
    	JsonObject obj = new JsonObject();
    	int i=0;
    	for(Hand hand : hands) {
    		obj.add(""+i, handToJSON(hand));
    		i++;
    	}
        return obj;
    }    
    // ~~~~~~~~~ Game room events
    public JsonObject handToJSON(Hand hand) {
        JsonObject obj = new JsonObject();
        obj.addProperty("playerId", hand.player.id);
        obj.addProperty("hasLeft", hand.hasLeft);
        if(hand.cardP != null){
         obj.addProperty("cardP", hand.cardP.value);
        }
        else{
            obj.addProperty("cardP", "null");
        }

        obj.add("cards", cardsToJSON(hand.cards));
        return obj;
    }
    public JsonObject cardsToJSON(List<Card> cards) {
    	JsonObject obj = new JsonObject();
    	for(Card card : cards) {
    		obj.add(String.valueOf(cards.indexOf(card)), cardToJSON(card));
    	}
        return obj;
    }
    public JsonObject cardToJSON(Card card) {
    	JsonObject obj = new JsonObject();
    	obj.addProperty("value", card.value);
    	return obj;
    }
    
    // ~~~~~~~~~ Chat room factory

    static GameTable instance = null;
    public static GameTable get() {
        if(instance == null) {
            instance = new GameTable();
        }
        return instance;
    }
    
}

