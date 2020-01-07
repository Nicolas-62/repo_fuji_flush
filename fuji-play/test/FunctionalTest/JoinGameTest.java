package FunctionalTest;

import org.junit.BeforeClass;
import org.junit.Test;

import jobs.InitJob;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class JoinGameTest extends FunctionalTest{
	@Test
	public void testThatJoinGameWorks() {
		Response response = POST("/login?username=bob@g.com&password=test");
		response = GET("/gameRoom");
		assertIsOk(response);
		// partie 4
		response = GET("/game/join?uuid=db1725af-2fd4-4f9f-bf3f-83f0d1165ecd");
		response = GET("/gameRoom");
		assertIsOk(response);
	}
}
