package controllers;
import java.util.List;

import org.apache.log4j.Level;

import models.Game;
import models.Hand;
import models.User;
import play.Logger;
import play.mvc.Controller;
import play.mvc.With;
import play.Logger;
import services.GameService;
import services.HandService;
import services.UserService;


@With(Secure.class)
public class Application extends Controller {
	public static final Level VERBOSE = Level.forName("VERBOSE", 550);

    public static void index() {
        User player = Security.connectedUser();
        Logger.info("%-10s get index", player.nickName);
        render();
    }
    public static void gameRoom() {
    	User player = Security.connectedUser(); 
    	List<Game> games = GameService.getAll();// findAll()
    	render(games, player);
    }
    /**
     * Un joueur quiite une partie
     * @param gameId : id de la partie quittée
     */
    public static void leave(long gameId){ // UUID sur gameId
        Game game=GameService.getById(gameId);
        User player = Security.connectedUser();
        GameService.leave(game, player);
        gameRoom();
    }
    public static void records(){
        User player = Security.connectedUser();

        List<Game> games =GameService.findAllByPlayer(player);
        render(games,player);


    }
    /**
     * Un joueur crée une partie puis la rejoint.
     * @param nbPlayer : nombre de joueurs voulus dans la partie
     */
    public static void addGame(int nbPlayer) {
    	User player = Security.connectedUser();
    	Game game = new Game();
    	game.currentPlayer = player;
    	game.nbPlayerMissing = nbPlayer;
    	GameService.addGame(game); 
    	Logger.info("%s create game id : %d", player.nickName, game.id);
    	GameService.joinGame(game, player);
    	gameRoom();
    	
    }
	/**
	 * Le joueur connecté rejoint la partie
	 * @param gameId : id de la partie
	 */
    public static void joinGame(Long gameId) { // UUID
    	User player = Security.connectedUser();
    	Game game = GameService.getById(gameId);
    	GameService.joinGame(game, player);
       	Logger.info("%s join game id : %d", player.nickName, game.id);
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
        notFoundIfNull(game);
        Hand handPlayer = HandService.getByPlayerAndGame(player, game);
        render(game, player, handPlayer);
    }

    public static void ranking()
    {
        List<User> listRanking = UserService.getTopRank();
        Long nbUser = UserService.getNbUser();
        UserService.calculateRank();
        render(listRanking, nbUser);
    }

    /**
     * Un joueur joue une carte, met à jour le jeu en fonction de la carte jouée
     * @param handId : handPlayer.id, id de la main d'un joueur donné
     * @param index : index de la carte présente dans la main du joueur
     * @param gameId : id de la partie en cours 
     */
    public static void playCard(Long handId, Integer index, Long gameId) {

        // on vérifie que la requête vient bien du joueur qui doit jouer
        User player = Security.connectedUser();
        Game game = GameService.getById(gameId);
        Hand hand = HandService.getById(handId);

        if (!player.equals(game.currentPlayer)) {
            forbidden();
        } else {
            GameService.playCard(hand, hand.cards.get(index), game);
            Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
            GameService.ruleCompareAndDiscard(game, currentHandPlayer);
            GameService.nextPlayer(game, hand);
            GameService.ruleFullTurn(game);
            play(game.id);
        }
    }

}
