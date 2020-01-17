package models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import controllers.Security;
import models.adapters.CardAdapter;
import models.adapters.GameAdapter;
import models.adapters.HandAdapter;
import models.adapters.PlayerAdapter;
import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;
import services.GameEventService;
import services.GameService;
import services.HandService;
import services.UserService;

public class GameRoom {
	
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
    
    final ArchivedEventStream<JsonObject> gameRoomEvents = new ArchivedEventStream<JsonObject>(1);
    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<JsonObject> join() {  
    	System.out.println("welcome to gameRoomStream");
        return gameRoomEvents.eventStream();
    }
	public void addGame(Game game) {
		User player = UserService.getByEmail(game.author.email);
		game = GameService.addGame(player, game.nbPlayerMissing);		
		GameService.joinGame(game, player);
		JsonObject obj = new JsonParser().parse(gsonBuilder.toJson(game)).getAsJsonObject();
		gameRoomEvents.publish(obj);
	}	
   
    static GameRoom instance = null;
    public static GameRoom get() {
        if(instance == null) {
            instance = new GameRoom();
        }
        return instance;
    }
}
