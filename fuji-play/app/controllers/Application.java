package controllers;

import java.util.Collections;
import java.util.List;

import models.Card;
import models.Game;
import models.Hand;
import models.User;
import play.mvc.Controller;
import play.mvc.With;
import services.CardService;
import services.GameService;
import services.HandService;


@With(Secure.class)
public class Application extends Controller {

    public static void index() {
        User player = Security.connectedUser();
        render();
    }
    public static void saloon() {
    	User player = Security.connectedUser(); 
    	List<Game> games = GameService.getAll();
    	render(games, player);
    }
    /**
     * Creation d'une partie par un joueur donné
     * @param nbJoueur : nombre de joueurs voulu dans la partie
     */
    public static void addGame(int nbJoueur) {
    	User player = Security.connectedUser();
    	Game game = new Game();
    	game.author = player;
    	game.deck = CardService.findAll();
    	Collections.shuffle(game.deck);   	
    	GameService.addGame(game);    	
    	joinGame(game.id);
    	
    }
	/**
	 * Le joueur connecté rejoint la partie, on crée sa main
	 * @param gameId : id de la partie
	 */
    public static void joinGame(Long gameId) {
    	User player = Security.connectedUser();
    	Game game = GameService.getById(gameId);
    	GameService.joinGame(game, player);
    	play(gameId);
    }
	/**
	 * Le joueur connecté ayant déjà rejoint la partie peut jouer
	 * @param gameId : id de la partie
	 */
    public static void play(Long gameId) {
    	User player = Security.connectedUser();
        Game game = GameService.getById(gameId);
        Hand handPlayer = HandService.getByPlayer(player);
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

            play(game.id);
        } else {
            forbidden();
        }
    }
}
