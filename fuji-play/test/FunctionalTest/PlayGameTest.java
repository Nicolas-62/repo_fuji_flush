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
		// partie 8
		response = GET("/play?uuid=db65c27f-b58c-4946-879d-3c58dc7b23ff");
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
