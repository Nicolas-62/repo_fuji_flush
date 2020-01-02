
import java.util.List;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import models.Card;
import models.Game;
import models.Hand;
import models.User;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class ApplicationTest extends FunctionalTest {

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
	@Test
	public void testThatIndexPageWorks() {
		Response response = GET("/");
		assertIsOk(response);
		assertContentType("text/html", response);
		assertCharset(play.Play.defaultWebEncoding, response);
	}

	@Test
	public void testThatWrongLoginDoesntWorks() {
		Response response = POST("/login?username=bob@g.com&password=tests");
		assertStatus(302, response);
		response = GET("/gameRoom");
		assertStatus(302, response);
		assertNull(renderArgs("games"));
	}

	@Test
	public void testThatLoginAndGameRoomPageWorks() {
		Response response = POST("/login?username=bob@g.com&password=test");
		assertStatus(302, response);
		response = GET("/gameRoom");
		assertIsOk(response);
		assertNotNull(renderArgs("player"));
		User bob = (User) renderArgs("player");
		assertEquals("bob", bob.nickName);
		assertNotNull(renderArgs("games"));
	}

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

	@Test
	public void testThatJoinGameWorks() {
		Response response = POST("/login?username=bob@g.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		response = GET("/game/join?uuid=db1725af-2fd4-4f9f-bf3f-83f0d1165ecd");
		response = GET("/gameRoom");
		assertIsOk(response);
	}

	@Test
	public void testThatPlayGameWorks() {
		Response response = POST("/login?username=boba.fett@coaxys.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
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

	@Test
	public void testThatPlayCardWorks() {
		Response response = POST("/login?username=boba.fett@coaxys.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		response = GET("/play?uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		assertNotNull(renderArgs("handPlayer"));
		Hand hand = (Hand) renderArgs("handPlayer");
		Card card = hand.cards.get(0);
		response = GET("/card/play?handId="+hand.id+"&index=0&uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		response = GET("/play?uuid=97976caa-ee88-48c3-9223-2e823ba8d7b9");
		assertIsOk(response);
		hand = (Hand) renderArgs("handPlayer");
		assertEquals(card, hand.cardP);

	}

}
