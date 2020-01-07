package FunctionalTest;

import org.junit.Test;

import models.Card;
import models.Hand;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class PlayCardTest extends FunctionalTest {
	@Test
	public void testThatPlayCardWorks() {
		Response response = POST("/login?username=boba.fett@coaxys.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		// partie 9
		response = GET("/play?uuid=794b2d71-64a2-4a10-94f0-0f1a69a637f5");
		assertNotNull(renderArgs("handPlayer"));
		Hand hand = (Hand) renderArgs("handPlayer");
		
		Card card = hand.cards.get(0);
		response = GET("/card/play?handId="+hand.id+"&index=0&uuid=794b2d71-64a2-4a10-94f0-0f1a69a637f5");
		response = GET("/play?uuid=794b2d71-64a2-4a10-94f0-0f1a69a637f5");
		assertIsOk(response);
		hand = (Hand) renderArgs("handPlayer");
		assertEquals(card, hand.cardP);

	}
}
