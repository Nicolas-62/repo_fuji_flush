import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import models.Card;
import models.Game;
import models.Hand;
import models.User;
import play.test.UnitTest;
import services.CardService;
import services.GameService;
import services.HandService;
import services.UserService;

public class FujiTest extends UnitTest{
	
	public int NB_GAMES=5;
	public String USER_EMAIL="bob@g.com";
	public String USER_NiCKNAME="bob";
	
	@BeforeClass
	public void beforeDeJunit() {
		InitJob job = new InitJob();
		try {
			job.doJob();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	/**
	 * test de CardService
	 */
	@Test
	public void findAllCards() {
		List<Card> cards = CardService.findAll();
		assertEquals(90, cards.size());
	}
	/**
	 * test de GameService
	 */
	@Test
	public void findAllGames() {
		List<Game> games = GameService.findAll();
		assertEquals(NB_GAMES, games.size());
	}
	@Test
	public void findByUUID() {
		
	}
	@Test
	public void findGamesFinishedWherePlayerWas() {
		User user = UserService.getByEmail(USER_EMAIL);
		List<Game> games = GameService.findFinishedGamesByPlayer(user);
		assertEquals(1, games.size());
	}
	@Test
	public void addGame() {
		User user = UserService.getByEmail(USER_EMAIL);
		Game gameCreated = GameService.addGame(user, 8);
		List<Game> games = GameService.findAll();
		this.NB_GAMES++;
		assertEquals(NB_GAMES, games.size());
		assertEquals(90, gameCreated.deck.size());
	}
	@Test
	public void joinGame() {
		// partie 4 manque des joueurs
		Game game = GameService.findAll().get(3);
		User user = UserService.getByEmail(USER_EMAIL);
		GameService.joinGame(game, user);
		Game gameSaved = Game.findById(game.id);
		assertNotNull(gameSaved);
		assertNotNull(gameSaved.hands);
		assertEquals(user, gameSaved.hands.get(1).player);
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
		Game game = GameService.findAll().get(0);
		Hand firstHand = game.hands.get(0);
		assertEquals(game.currentPlayer, firstHand.player);	
		GameService.nextPlayer(game, firstHand);
		assertEquals(game.currentPlayer, game.hands.get(2).player);		
	}
	@Test
	public void playCard() {
		Game game = GameService.findAll().get(0);
		Hand aHand = game.hands.get(0);
		Card aCard = aHand.cards.get(0);
		GameService.playCard(aHand, aCard);
		assertEquals(aHand.cardP, aCard);
		
	}
	@Test
	public void ruleFullTurn() {
		Game game = GameService.getOne();
		Hand aHand = HandService.getByPlayerAndGame(game.currentPlayer, game);
		GameService.playCard(aHand, aHand.cards.get(0));
		GameService.ruleFullTurn(game);
		assertNull(aHand.cardP);
		
	}
	@Test
	public void win() {
		Game game = GameService.getOne();
		GameService.win(game.hands.get(0));
		assertEquals(game.winners.get(0), game.hands.get(0));
	}
	@Test
	public void leaveGame() {		
		Game game = GameService.findAll().get(4);
		User userLeave = game.currentPlayer;
		GameService.leave(game, userLeave);
		assertEquals(0, HandService.getByPlayerAndGame(userLeave, game).cards.size());
	}
	@Test
	public void leaveGameOfThreePlayer() {
		Game game = GameService.getOne();
		User userLeave = game.currentPlayer;
		GameService.leave(game, userLeave);
		assertEquals(2, game.winners.size());
	}
	/**
	 * test de UserService
	 */
	@Test
	public void getByEmail() {
		String email = USER_EMAIL;
		User user = UserService.getByEmail(email);
		assertNotNull(user);;
		assertEquals(0, user.score);
		assertEquals(USER_NiCKNAME, user.nickName);
	}
	
}
