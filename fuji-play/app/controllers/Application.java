package controllers;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import models.Game;
import models.GameEvent;
import models.Hand;
import models.User;
import org.apache.commons.lang.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.Level;
import play.mvc.Controller;
import play.mvc.With;
import services.GameEventService;
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
		Game game = GameService.findByUUID(uuid);
		User player = Security.connectedUser();
		GameService.leave(game, player);
				// Game Event pour quitter une partie
		GameEventService.addGameEvent(game, player.nickName+" left the game !");
		gameRoom();
	}

	/**
	 * Voir toutes ses parties terminées ainsi que le détail des logs quand on clique sur l'oeil
	 *
	 * @param selectedRecordUuid : uuid de la partie sélectionnée
	 */
	public static void records(String selectedRecordUuid) {
		User player = Security.connectedUser();
		List<Game> games = GameService.findFinishedGamesByPlayer(player);

		// Savoir si un joueur a gagné une partie
		HashMap <Game, Boolean> gameResults = new HashMap<Game, Boolean>();
		for(int i = 0; i < games.size(); i++)
		{
			gameResults.put(games.get(i), HandService.getByPlayerAndGame(player, games.get(i)).hasWon);
		}

		// Savoir si un joueur a quitté une partie
		HashMap <Game, Boolean> gameLeavers = new HashMap<Game, Boolean>();
		for(int i = 0; i < games.size(); i++)
		{
			gameLeavers.put(games.get(i), HandService.getByPlayerAndGame(player, games.get(i)).hasLeft);
		}

		// Savoir si un joueur a gagné une partie car quelqu'un a quitté
		HashMap <Game, Boolean> gameLeaveWinners = new HashMap<Game, Boolean>();
		for(int i = 0; i < games.size(); i++)
		{
			gameLeaveWinners.put(games.get(i), HandService.getByPlayerAndGame(player, games.get(i)).hasLeaveWon);
		}

		Game game = null;
		List<GameEvent> gameEvents = new ArrayList<>();
		if(StringUtils.isNotBlank(selectedRecordUuid)) {
			game = GameService.findByUUID(selectedRecordUuid);
			notFoundIfNull(game);
			gameEvents = GameEventService.findAllByGame(game);
		}

		render(games, player, game, gameEvents, gameResults, gameLeavers, gameLeaveWinners);
	}

	/**
	 * Un joueur crée une partie puis la rejoint.
	 *
	 * @param nbPlayer : nombre de joueurs voulus dans la partie
	 */
	public static void addGame(int nbPlayer) {
		User player = Security.connectedUser();
		Game game = GameService.addGame(player, nbPlayer);

		// GameEvent qui gère le fait de créer une partie
		GameEventService.addGameEvent(game, player.nickName+" created the game !");

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
		Game game = GameService.findByUUID(uuid);
		GameService.joinGame(game, player);

		// GameEvent qui gère le fait de rejoindre une partie
		GameEventService.addGameEvent(game, player.nickName+" joined the game !");

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
		Game game = GameService.findByUUID(uuid);
		notFoundIfNull(game);
		Hand handPlayer = HandService.getByPlayerAndGame(player, game);
        List<GameEvent> gameEvents = GameEventService.findAllByGame(game);
		render(game, player, gameEvents, handPlayer);
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
	public static void playCard(Long handId, Integer index, String uuid) {

		// on vérifie que la requête vient bien du joueur qui doit jouer
		User player = Security.connectedUser();
		Game game = GameService.findByUUID(uuid);
		Hand hand = HandService.getById(handId);

		if (!player.equals(game.currentPlayer)) {
			forbidden();
		} else {
			logger.log(GAME, String.format("%-15s played %d", player.nickName, hand.cards.get(index).value));
			GameService.playCard(hand, hand.cards.get(index));

			// GameEvent qui gère le fait de jouer une carte
			GameEventService.addGameEvent(game, player.nickName+" played card "+hand.cardP.value);

			Hand currentHandPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
			GameService.ruleCompareAndDiscard(game, currentHandPlayer);
			GameService.nextPlayer(game, hand);
			GameService.ruleFullTurn(game);
			play(game.uuid);
		}
	}

}
