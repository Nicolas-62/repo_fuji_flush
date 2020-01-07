package servicesTest;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import models.Card;
import models.Game;
import models.User;
import play.test.UnitTest;
import services.CardService;
import services.GameService;
import services.UserService;

public class AccessTest extends UnitTest{
	
	public static int NB_GAMES=9;
	public static String USER_EMAIL="bob@g.com";
	public static String USER_NiCKNAME="bob";

	/**
	 * test de CardService
	 */
	@Test
	public void findAllCards() {
		List<Card> cards = CardService.findAll();
		assertEquals(90, cards.size());
	}
	/**
	 * test de UserService
	 */
	@Test
	public void getByEmail() {
		String email = USER_EMAIL;
		User user = UserService.getByEmail(email);
		assertNotNull(user);
		assertEquals(0, user.score);
		assertEquals(USER_NiCKNAME, user.nickName);
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
		Game game = GameService.findByUUID("97976caa-ee88-48c3-9223-2e823ba8d7b9");
		assertNotNull(game);
		assertEquals("Boba", game.author.nickName);
	}
	@Test
	public void findGamesFinishedWherePlayerWas() {
		User user = UserService.getByEmail(USER_EMAIL);
		List<Game> games = GameService.findFinishedGamesByPlayer(user);
		assertEquals(1, games.size());
	}	
}
