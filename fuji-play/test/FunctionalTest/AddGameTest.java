package FunctionalTest;

import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import models.Game;
import models.User;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class AddGameTest extends FunctionalTest{
	
	@Test
	public void testThatAddGameWorks() {
		Response response = POST("/login?username=bob@g.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		response = POST("/game/add?nbPlayer=8");
		response = GET("/gameRoom");
		assertIsOk(response);
		assertNotNull(renderArgs("player"));
		User bob = (User) renderArgs("player");
		assertNotNull(renderArgs("games"));
		List<Game> games = (List<Game>) renderArgs("games");
		Game game = games.get(games.size() - 1);
		assertNotNull(game);
		assertEquals(game.author, bob);
		assertEquals(game.currentPlayer, bob);
		assertEquals(7, game.nbPlayerMissing.intValue());

	}
}
