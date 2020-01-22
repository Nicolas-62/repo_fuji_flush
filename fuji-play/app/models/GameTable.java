package models;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.adapters.CardAdapter;
import models.adapters.GameEventAdapter;
import models.adapters.GameTableAdapter;
import models.adapters.HandAdapter;
import models.adapters.PlayerAdapter;
import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;
import services.GameEventService;
import services.GameService;
import services.HandService;
import services.UserService;

public class GameTable {
	
	static GameTable instance = null;	
	final ArchivedEventStream<JsonObject> gameEvents = new ArchivedEventStream<JsonObject>(1);
    private static final Gson gameBuilder = new GsonBuilder()
            .registerTypeAdapter(Game.class, new GameTableAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Hand.class, new HandAdapter())
	            .registerTypeAdapter(User.class, new PlayerAdapter())
	            .registerTypeAdapter(Card.class, new CardAdapter())
	            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(GameEvent.class, new GameEventAdapter())
            .create();
    
    public static GameTable get() {
    	if(instance == null) {
    		instance = new GameTable();
    	}
    	return instance;
    }    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<JsonObject> join() {  
    	System.out.println("welcome to gameStream");
        return gameEvents.eventStream();
    }
    public void playCard(String gameUuid, String email, int cardIndex) {
    	Game game = GameService.findByUUID(gameUuid);
    	User player = UserService.getByEmail(email);
		Hand hand = HandService.getByPlayerAndGame(player, game);
		System.out.println(player.nickName+" playCard");
		GameService.playCard(hand, hand.cards.get(cardIndex));
		GameEventService.addGameEvent(game, player.nickName+" played card "+hand.cardP.value);
		Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
		GameService.ruleCompareAndDiscard(game, currentHandPlayer);
		GameService.nextPlayer(game, hand);
		GameService.ruleFullTurn(game);	
		// parse game to JSON object
		JsonObject obj = new JsonParser().parse(gameBuilder.toJson(game)).getAsJsonObject();
		gameEvents.publish(obj);
    }
	public  void leave(String uuid, String email) {
		Game game = GameService.findByUUID(uuid);
		User player = UserService.getByEmail(email);
		GameService.leave(game, player);
		// Game Event pour quitter une partie
		GameEventService.addGameEvent(game, player.nickName+" left the game !");		
		JsonObject obj = new JsonParser().parse(gameBuilder.toJson(game)).getAsJsonObject();
		gameEvents.publish(obj);
	}
}

