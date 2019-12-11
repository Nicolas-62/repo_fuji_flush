package controllers;

import models.Game;
import models.Hand;
import models.User;
import play.mvc.Controller;
import play.mvc.With;
import play.templates.GroovyInlineTags;
import services.GameService;
import services.HandService;

@With(Secure.class)
public class Application extends Controller {

	/**
	 * Demande authentification du joueur, récupère le joueur, sa main, le jeu associé à sa main.
	 * @return game : jeu
	 * @return player : joueur
	 * @return  handPlayer : min du joueur 
	 */
    public static void index() {
        User player = Security.connectedUser();
        Hand handPlayer = HandService.getByPlayer(player);
        Game game = handPlayer.game;
        render(game, player, handPlayer);
    }
    /**
     * Met à jour le jeu en fonction de la carte jouée
     * @param id : handPlayer.id, id de la main d'un joueur donné
     * @param index : index de la carte présente dans la main du joueur
     */
    public static void playCard(Long id, Integer index) {
    	// on récupère la main du joueur donné
        Hand hand = HandService.getById(id);
        // on récupère le jeu associé
        Game game = hand.game;
        
        // on vérifie que la requête vient bien du joueur qui doit jouer
        User player = Security.connectedUser();
        if (player.equals(game.currentPlayer)) {
        	
            GameService.playCard(hand, hand.cards.get(index));         
            Hand currentHandPlayer = HandService.getByPlayer(game.currentPlayer);
            GameService.ruleCompareAndDiscard(game, currentHandPlayer);

            GameService.nextPlayer(game, hand);

            GameService.ruleFullTurn(game);

            index();
        } else {
            forbidden();
        }
    }


}