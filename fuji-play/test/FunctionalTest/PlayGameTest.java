package FunctionalTest;

import org.junit.Test;

import models.Game;
import models.Hand;
import models.User;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class PlayGameTest extends FunctionalTest{
	@Test
	public void testThatPlayGameWorks() {
		Response response = POST("/login?username=boba.fett@coaxys.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		// partie 5
		response = GET("/play?uuid=6bfbb28e-556d-4cc7-aeed-5d65bf046bc2");
		assertNotNull(renderArgs("player"));
		User boba = (User) renderArgs("player");
		assertNotNull(renderArgs("game"));
		Game game = (Game) renderArgs("game");
		assertNotNull(game);
		assertEquals(game.author, boba);
		assertEquals(game.currentPlayer, boba);
		assertNotNull(renderArgs("handPlayer"));
		Hand hand = (Hand) renderArgs("handPlayer");
		assertEquals(6, hand.cards.size());

	}
}
