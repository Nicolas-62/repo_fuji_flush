package FunctionalTest;

import java.util.List;

import org.junit.After;
import org.junit.BeforeClass;
import org.junit.Test;

import controllers.Secure;
import jobs.InitJob;
import models.Card;
import models.Game;
import models.Hand;
import models.User;
import play.mvc.Http.Response;
import play.test.FunctionalTest;

public class LoginTest extends FunctionalTest {

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
}
