package controllers;

import java.util.List;

import models.Game;
import models.Hand;
import models.User;
import play.mvc.Controller;
import play.mvc.With;
import services.GameService;
import services.HandService;
import services.UserService;


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
	     * Creation d'une partie par un joueur donné puis il rejoint la partie
     * @param nbJoueur : nombre de joueurs voulu dans la partie
     */
    public static void addGame(int nbPlayer) {
    	User player = Security.connectedUser();
    	Game game = new Game();
    	game.author = player;
    	game.nbPlayerMissing = nbPlayer;
    	GameService.addGame(game);  
    	GameService.joinGame(game, player);
    	saloon();
    	
    }
	/**
	 * Le joueur connecté rejoint la partie
	 * @param gameId : id de la partie
	 */
    public static void joinGame(Long gameId) {
    	User player = Security.connectedUser();
    	Game game = GameService.getById(gameId);
    	GameService.joinGame(game, player);
    	if(game.nbPlayerMissing == 0) {
    		play(gameId);
    	}else {
    		saloon();
    	}
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

    public static void ranking()
    {
        User user = Security.connectedUser();
        List<User> listRanking = UserService.getTopRank();
        Long nbUser = UserService.getNbUser();
        UserService.calculateRank();
        render(listRanking, nbUser);
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
