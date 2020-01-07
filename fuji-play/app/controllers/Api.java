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
import models.adaptaters.HandAdapter;
import models.adaptaters.PlayerAdapter;
import play.mvc.Before;
import services.GameService;

public class Api extends TrackerController{
    private static final Gson gson = new GsonBuilder()
            .registerTypeAdapter(Game.class, new GameAdapter())
            .registerTypeAdapter(Hand.class, new HandAdapter())
            .registerTypeAdapter(Card.class, new CardAdapter())
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
}