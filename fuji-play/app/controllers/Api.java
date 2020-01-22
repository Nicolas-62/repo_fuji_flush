package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Card;
import models.Game;
import models.GameEvent;
import models.Hand;
import models.User;
import models.adapters.CardAdapter;
import models.adapters.GameEventAdapter;
import models.adapters.GameRoomAdapter;
import models.adapters.GameTableAdapter;
import models.adapters.HandAdapter;
import models.adapters.PlayerAdapter;
import play.mvc.Before;
import services.GameService;
import services.UserService;

public class Api extends TrackerController{
    private static final Gson gameRoomBuilder = new GsonBuilder()
            .registerTypeAdapter(Game.class, new GameRoomAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(Hand.class, new HandAdapter())
	            .registerTypeAdapter(User.class, new PlayerAdapter())
	            .registerTypeAdapter(Card.class, new CardAdapter())
	            .registerTypeAdapter(Card.class, new CardAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
            .create();

    private static final Gson gameTableBuilder = new GsonBuilder()
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
    @Before
    static void before() {
        response.setHeader("access-control-allow-origin", "*");
    }
    
	public static void games() {
		List<Game> games = GameService.findAll();
		renderJSON(games, gameRoomBuilder);
	} 
	public static void game(String uuid) {
		Game game = GameService.findByUUID(uuid);
		renderJSON(game, gameTableBuilder);
	}
	
	public static void addGame() {
		Gson gson = new Gson();
		Game game = gson.fromJson(request.params.get("body"), Game.class);
		
		User player = UserService.getByEmail(game.author.email);
		game = GameService.addGame(player, game.nbPlayerMissing);
		
		GameService.joinGame(game, player);
		renderJSON(game, gameRoomBuilder);
	}
}
