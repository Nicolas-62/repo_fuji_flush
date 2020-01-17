package controllers;

import models.User;
import play.Logger;
import services.UserService;

public class Security extends Secure.Security {

    private static final String LOG_PREFIX = "Security |";

    static boolean authenticate(String email, String password) {
        User user = UserService.getByEmail(email);
        if (user != null) {
            if (UserService.checkPassword(password, user.password)) {
                Logger.info("%s authenticate : connexion de l'utilisateur [%s]", LOG_PREFIX, email);
                return true;
            } else {
                Logger.warn("%s authenticate : erreur de connexion de l'utilisateur [%s]", LOG_PREFIX, email);
                return false;
            }
        } else {
            Logger.warn("%s authenticate : tentative de connexion de l'utilisateur inconnu [%s]", LOG_PREFIX, email);
            return false;
        }
    }

    public static User connectedUser() {
        User connectedUser = (User) renderArgs.get("connectedUser");
        if (connectedUser == null) {
            connectedUser = UserService.getByEmail(connected());
            renderArgs.put("connectedUser", connectedUser);
        }
        return connectedUser;
    }

    public static void logout() throws Throwable {
        Home.index();
    }
}
