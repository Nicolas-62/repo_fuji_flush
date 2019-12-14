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
    public static void gameRoom() {
    	User player = Security.connectedUser(); 
    	List<Game> games = GameService.getAll();
    	render(games, player);
    }

    public static void leave(long gameId){
        Game game=GameService.getById(gameId);
        User player = Security.connectedUser();
        GameService.leave(game, player);
        gameRoom();
    }
    /**
	     * Creation d'une partie par un joueur donné puis il rejoint la partie
     * @param nbPlayer : nombre de joueurs voulu dans la partie
     */
    public static void addGame(int nbPlayer) {
    	User player = Security.connectedUser();
    	Game game = new Game();
    	game.currentPlayer = player;
    	game.nbPlayerMissing = nbPlayer;
    	GameService.addGame(game);  
    	GameService.joinGame(game, player);
    	gameRoom();
    	
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
    		gameRoom();
    	}
    }
	/**
	 * Le joueur connecté ayant déjà rejoint la partie peut jouer
	 * @param gameId : id de la partie
	 */
    public static void play(Long gameId) {

    	User player = Security.connectedUser();
        Game game = GameService.getById(gameId);
        Hand handPlayer = HandService.getByPlayerAndGame(player, game);
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
    public static void playCard(Long id, Integer index, Long gameId) {

        // on vérifie que la requête vient bien du joueur qui doit jouer
        User player = Security.connectedUser();
        Game game = GameService.getById(gameId);
        // on récupère la main du joueur donné
        Hand hand = HandService.getByPlayerAndGame(player, game);

        if (player.equals(game.currentPlayer)) {
        	
            GameService.playCard(hand, hand.cards.get(index));         
            Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
            GameService.ruleCompareAndDiscard(game, currentHandPlayer);

            GameService.nextPlayer(game, hand);

            GameService.ruleFullTurn(game);

            play(game.id);
        } else {
            forbidden();
        }
    }
}
