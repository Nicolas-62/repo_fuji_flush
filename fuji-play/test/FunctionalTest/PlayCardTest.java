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
		// partie 1
		response = GET("/play?uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		assertNotNull(renderArgs("handPlayer"));
		Hand hand = (Hand) renderArgs("handPlayer");
		Card card = hand.cards.get(0);
		System.out.println("/card/play?handId="+hand.id+"&index=0&uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		response = GET("/card/play?handId="+hand.id+"&index=0&uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		response = GET("/play?uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		assertIsOk(response);
		hand = (Hand) renderArgs("handPlayer");
		assertEquals(card, hand.cardP);

	}
}
