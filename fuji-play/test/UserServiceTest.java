//import static functional.builder.FujiAfterLoginBuilder.afterGoodLogin;

import java.util.List;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import models.User;
import play.mvc.Http.Response;
import play.test.Fixtures;
import play.test.UnitTest;
import services.UserService;

// exemple de classe par Nicolas Giard
public class UserServiceTest extends UnitTest {
//
//    @Before
//    public void beforeDeJunit() {
//        Fixtures.deleteAllModels();
//        Fixtures.loadModels("initial-data/initial-data.yml");
//    }
//
//    @Test
//    public void findAllOrderByScore_01() {
//        // Given
//
//        // When
//        List<User> users = UserService.findAllOrderByScore();
//
//        // Then
//        assertEquals(6, users.size());
//        assertEquals("han.solo@coaxys.com", users.get(0).email);
//        assertEquals("boba.fett@coaxys.com", users.get(1).email);
//    }
//
//    @Test
//    public void getByEmail_01() {
//        // Given
//        String email = "han.solo@coaxys.com";
//
//        // When
//        User user = UserService.getByEmail(email);
//
//        // Then
//        assertNotNull(user);
//        assertEquals(1, user.rank);
//    }
//
//    @Test
//    public void getByEmail_02() {
//        // Given
//        String email = null;
//
//        // When
//        User user = UserService.getByEmail(email);
//
//        // Then
//        assertNull(user);
//    }
//
//    @Test
//    public void getByEmail_03() {
//        // Given
//	   String email = "";
//
//       // When
//       User user = UserService.getByEmail(email);
//
//       // Then
//       assertNull(user);
//   }
//
//   @Test
//   public void getByEmail_04() {
//       // Given
//       String email = "nexiste.pas@coaxys.com";
//
//       // When
//       User user = UserService.getByEmail(email);
//
//       // Then
//       assertNull(user);
//   }
}