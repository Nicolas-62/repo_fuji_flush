package services;

import models.User;
import org.mindrot.jbcrypt.BCrypt;
import play.Logger;

public class UserService {

    private static final String LOG_PREFIX = UserService.class.getSimpleName() + " |";

    private static final int BCRYPT_WORKLOAD = 12;

    public static User getByEmail(String email) {
        Logger.debug("%s getByEmail [%s]", LOG_PREFIX, email);
        return User.find("email = ?1", email).first();
    }

    public static String encodePassword(String password) {
        Logger.debug("%s encodePassword", LOG_PREFIX);
        String salt = BCrypt.gensalt(BCRYPT_WORKLOAD);
        return BCrypt.hashpw(password, salt);
    }

    public static boolean checkPassword(String password, String cryptedPassword) {
        Logger.debug("%s checkPassword", LOG_PREFIX);
        return BCrypt.checkpw(password, cryptedPassword);
    }

}
