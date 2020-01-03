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

public class FujiNonModifiedDataTest extends UnitTest{
	
	public int NB_GAMES=5;
	public String USER_EMAIL="bob@g.com";
	public String USER_NiCKNAME="bob";
	
	@BeforeClass
	public static void beforeDeJunit() {
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
		List<Game> games = GameService.findGamesFinishedWherePlayerWas(user);		
		assertEquals(1, games.size());
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
