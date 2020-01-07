package servicesTest;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import models.Card;
import models.Game;
import models.Hand;
import models.User;
import play.test.UnitTest;
import services.GameService;
import services.HandService;
import services.UserService;

public class ModifTest extends UnitTest{
	
	public static int NB_GAMES=9;
	public static String USER_EMAIL="bob@g.com";
	public static String USER_NiCKNAME="bob";
	
	@BeforeClass
	public static void beforeDeJunit() {
		InitJob job = new InitJob();
		NB_GAMES=9;
		AccessTest.NB_GAMES=9;
		try {
			job.doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}	
	/**
	 * test de GameService
	 */
	@Test
	public void addGame() {
		User user = UserService.getByEmail(USER_EMAIL);
		Game gameCreated = GameService.addGame(user, 8);
		List<Game> games = GameService.findAll();
		this.NB_GAMES++;
		AccessTest.NB_GAMES++;
		assertEquals(NB_GAMES, games.size());
		assertEquals(90, gameCreated.deck.size());
	}
	@Test
	public void joinGame() {
		// partie 4 manque des joueurs
		Game game = GameService.findAll().get(3);
		int nbJoueur = game.hands.size();
		User user = UserService.getByEmail(USER_EMAIL);
		GameService.joinGame(game, user);
		nbJoueur++;
		Game gameSaved = Game.findById(game.id);
		assertNotNull(gameSaved);
		assertNotNull(gameSaved.hands);
		assertEquals(user, gameSaved.hands.get(nbJoueur-1).player);
	}
	@Test
	public void lastPlayerJoinGame() {
		// partie 2 manque un joueur
		Game game = GameService.findAll().get(1);
		User user = UserService.getByEmail(USER_EMAIL);
		GameService.joinGame(game, user);
		Game gameSaved = Game.findById(game.id);
		assertNotNull(gameSaved);
		assertEquals(0, gameSaved.nbPlayerMissing.intValue());
		assertEquals(6, gameSaved.hands.get(0).cards.size());
	}	
	
	@Test
	public void nextPlayer() {
		// partie 1, joueur courant : boba_fett, joueur suivant a quitté la partie.
		Game game = GameService.getOne();		
		Hand handOfCurrentPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
		assertEquals(game.currentPlayer, handOfCurrentPlayer.player);	
		GameService.nextPlayer(game, handOfCurrentPlayer);
		// c'est normalement au tour du troisième joueur
		assertEquals(game.currentPlayer, game.hands.get(2).player);		
	}
	@Test
	public void playCard() {
		// partie 1, joueur courant joue une carte
		Game game = GameService.getOne();
		Hand handOfCurrentPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
		Card aCard = handOfCurrentPlayer.cards.get(0);
		GameService.playCard(handOfCurrentPlayer, aCard);
		assertEquals(handOfCurrentPlayer.cardP, aCard);
		
	}
	@Test
	public void ruleFullTurn() {
		// partie 1, joueur courant joue une carte
		Game game = GameService.getOne();
		Hand aHand = HandService.getByPlayerAndGame(game.currentPlayer, game);
		GameService.playCard(aHand, aHand.cards.get(0));
		// on simule un tour de plateau, le joueur suivant est donc celui qui vient de jouer, il defausse sa carte
		GameService.ruleFullTurn(game);
		assertNull(aHand.cardP);
		
	}
	@Test
	public void win() {
		// partie 7
		Game game = GameService.findAll().get(6);
		GameService.win(game.hands.get(0));
		assertEquals(game.winners.get(0), game.hands.get(0));
	}
	@Test
	public void leaveGame() {
		// partie 5 le joueur courant quitte la partie
		Game game = GameService.findAll().get(4);
		User userLeave = game.currentPlayer;
		GameService.leave(game, userLeave);
		assertEquals(0, HandService.getByPlayerAndGame(userLeave, game).cards.size());
	}
	@Test
	public void leaveGameOfThreePlayer() {
		// partie 6 quand on quitte une partie de 3 joueurs les deux autres sont gagants
		Game game = GameService.findAll().get(5);
		User userLeave = game.currentPlayer;
		GameService.leave(game, userLeave);
		assertEquals(2, game.winners.size());
	}	
}
