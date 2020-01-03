package controllers;
import java.util.List;

import models.Game;
import models.Hand;
import models.User;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import play.mvc.Controller;
import play.mvc.With;
import services.GameService;
import services.HandService;
import services.UserService;


@With(Secure.class)
public class Application extends Controller {

	final static Level GAME = Level.forName("GAME", 550);
	final static Logger logger = LogManager.getLogger();

	public static void gameRoom() {
		User player = Security.connectedUser();
		List<Game> games = GameService.findAll();
		render(games, player);
	}

	/**
	 * Un joueur quitte une partie
	 *
	 * @param uuid : id de la partie quittée
	 */
	public static void leave(String uuid) {
		Game game = GameService.getByUUID(uuid);
		User player = Security.connectedUser();
		GameService.leave(game, player);
		gameRoom();
	}

	public static void records() {
		User player = Security.connectedUser();
		List<Game> games = GameService.findGamesFinishedWherePlayerWas(player);
		render(games, player);
	}

	/**
	 * Un joueur crée une partie puis la rejoint.
	 *
	 * @param nbPlayer : nombre de joueurs voulus dans la partie
	 */
	public static void addGame(int nbPlayer) {
		User player = Security.connectedUser();
		Game game = GameService.addGame(player, nbPlayer);
		logger.log(GAME, String.format("%-10s created game", player.nickName));
		// Logger.info("%s create game id : %d", player.nickName, game.id);

		GameService.joinGame(game, player);
		logger.log(GAME, String.format("%-10s joined game", player.nickName));
		gameRoom();

	}

	/**
	 * Le joueur connecté rejoint la partie
	 *
	 * @param uuid : id de la partie
	 */
	public static void joinGame(String uuid) {
		User player = Security.connectedUser();
		Game game = GameService.getByUUID(uuid);
		GameService.joinGame(game, player);
		logger.log(GAME, String.format("%-10s joined game", player.nickName));
		// Logger.info("%s join game id : %d", player.nickName, game.id);
		if (game.nbPlayerMissing == 0) {
			play(uuid);
		} else {
			gameRoom();
		}
	}

	/**
	 * Le joueur connecté ayant déjà rejoint la partie peut jouer
	 *
	 * @param uuid : id de la partie
	 */
	public static void play(String uuid) {

		User player = Security.connectedUser();
		Game game = GameService.getByUUID(uuid);
		notFoundIfNull(game);
		logger.log(GAME, "game started !");
		Hand handPlayer = HandService.getByPlayerAndGame(player, game);
		render(game, player, handPlayer);
	}

	public static void ranking() {
		List<User> listRanking = UserService.getTopRank();
		Long nbUser = UserService.getNbUser();
		UserService.calculateRank();
		render(listRanking, nbUser);
	}

	/**
	 * Un joueur joue une carte, met à jour le jeu en fonction de la carte jouée
	 *
	 * @param handId : handPlayer.id, id de la main d'un joueur donné
	 * @param index  : index de la carte présente dans la main du joueur
	 * @param uuid : id de la partie en cours
	 */
	public static void playCard(Long handId, Integer index, String GameUuid) {

		// on vérifie que la requête vient bien du joueur qui doit jouer
		User player = Security.connectedUser();
		Game game = GameService.getByUUID(GameUuid);
		Hand hand = HandService.getById(handId);

		if (!player.equals(game.currentPlayer)) {
			forbidden();
		} else {
			logger.log(GAME, String.format("%-15s play %d", player.nickName, hand.cards.get(index).value));
			GameService.playCard(hand, hand.cards.get(index));
			Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
			GameService.ruleCompareAndDiscard(game, currentHandPlayer);
			GameService.nextPlayer(game, hand);
			GameService.ruleFullTurn(game);
			play(game.uuid);
		}
	}

}
