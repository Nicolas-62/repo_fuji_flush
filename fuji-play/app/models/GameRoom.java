package models;

import java.util.*;

import com.google.gson.JsonObject;

import controllers.Security;
import play.libs.*;
import play.libs.F.*;
import services.GameService;
import services.HandService;

public class GameRoom {
    
    // ~~~~~~~~~ Let's chat! 
    
    final ArchivedEventStream<JsonObject> gameEvents = new ArchivedEventStream<JsonObject>(10);
    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<JsonObject> join(String playerNickName) {  
    	System.out.println(playerNickName+" ready to play");
        return gameEvents.eventStream();
    }
    public void playCard(Long handId, Integer index, String uuid) {
    	Game game = GameService.getByUUID(uuid);
		Hand hand = HandService.getById(handId);
		User player = hand.player;
		System.out.println(player.nickName+" playCard");

		GameService.playCard(hand, hand.cards.get(index));
		Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
		GameService.ruleCompareAndDiscard(game, currentHandPlayer);
		GameService.nextPlayer(game, hand);
		GameService.ruleFullTurn(game);	
		
		gameEvents.publish(gameToJSON(game));
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

    static GameRoom instance = null;
    public static GameRoom get() {
        if(instance == null) {
            instance = new GameRoom();
        }
        return instance;
    }
    
}

