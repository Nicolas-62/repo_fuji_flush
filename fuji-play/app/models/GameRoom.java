package models;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import models.adapters.CardAdapter;
import models.adapters.GameEventAdapter;
import models.adapters.GameRoomAdapter;
import models.adapters.GameTableAdapter;
import models.adapters.HandAdapter;
import models.adapters.PlayerAdapter;
import play.libs.F.ArchivedEventStream;
import play.libs.F.EventStream;
import services.GameService;
import services.UserService;

public class GameRoom {
	
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
    
    final ArchivedEventStream<JsonArray> gameRoomEvents = new ArchivedEventStream<JsonArray>(1);
    
    /**
     * For WebSocket, when a user join the room we return a continuous event stream
     * of ChatEvent
     */
    public EventStream<JsonArray> join() {  
    	System.out.println("welcome to roomStream");
        return gameRoomEvents.eventStream();
    }
	public void addGame(Game game) {
		User player = UserService.getByEmail(game.author.email);
		game = GameService.addGame(player, game.nbPlayerMissing);		
		GameService.joinGame(game, player);
		List<Game> games = GameService.findAll();
		JsonArray a = new JsonParser().parse(gameBuilder.toJson(games)).getAsJsonArray();
		gameRoomEvents.publish(a);
	}	
	public void joinGame(Game gameToJoin, String playerEmail) {
		User player = UserService.getByEmail(playerEmail);
		Game game = GameService.findByUUID(gameToJoin.uuid);
		GameService.joinGame(game, player);
		List<Game> games = GameService.findAll();
		JsonArray a = new JsonParser().parse(gameBuilder.toJson(games)).getAsJsonArray();
		gameRoomEvents.publish(a);
	}
    static GameRoom instance = null;
    public static GameRoom get() {
        if(instance == null) {
            instance = new GameRoom();
        }
        return instance;
    }
}
