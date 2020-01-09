package controllers;

import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import models.Card;
import models.Game;
import models.Hand;
import models.User;
import models.adaptaters.CardAdapter;
import models.adaptaters.GameAdapter;
import models.adaptaters.GameForRoomAdapter;
import models.adaptaters.HandAdapter;
import models.adaptaters.PlayerAdapter;
import play.mvc.Before;
import services.GameService;
import services.UserService;

public class Api extends TrackerController{
    private static final Gson gson = new GsonBuilder()
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
    
    private static final Gson gameForRomGson = new GsonBuilder()
            .registerTypeAdapter(Game.class, new GameForRoomAdapter())
            .registerTypeAdapter(User.class, new PlayerAdapter())
    		.create();

    @Before
    static void before() {
        response.setHeader("access-control-allow-origin", "*");
    }
    
	public static void gameRoom() {
		List<Game> games = GameService.findAll();
		renderJSON(games, gson);
	} 
	
	public static void addGame() {
		Gson gson = new Gson();
		Game game = gson.fromJson(request.params.get("body"), Game.class);
		
		User player = UserService.getByEmail(game.author.email);
		game = GameService.addGame(player, game.nbPlayerMissing);
		
		GameService.joinGame(game, player);
		renderJSON(game, new GameForRoomAdapter(), new PlayerAdapter());
	}
}
