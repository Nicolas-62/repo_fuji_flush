package controllers;

import play.*;
import play.mvc.*;
import services.PartieService;

import java.util.*;

import models.*;

@With(Secure.class)
public class Application extends Controller {

    public static void index() {   	
    	Game game = Game.find("").first();
    	User player = Security.connectedUser();
    	Hand handPlayer = Hand.find("player=?1", player).first();
        render(game, handPlayer, player);
    }
    
    public static void playCard(Long id, Integer cardP) {
    	Hand hand = Hand.findById(id);
    }

}