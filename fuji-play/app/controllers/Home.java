package controllers;

import models.User;
import play.data.validation.Email;
import play.data.validation.Error;
import play.data.validation.Required;
import play.data.validation.Validation;
import play.mvc.Controller;
import services.UserService;

import static controllers.Application.saloon;


public class Home extends Controller {

    public static void index() {
        render();
    }

    public static void register() {
        render();
    }

    public static void createAccount(@Required String nickname, @Required @Email String email, @Required String password, @Required String passwordRepeat) {


        Validation.equals("password", password, "passwordRepeat", passwordRepeat);
        Validation.isTrue("email", !UserService.existByEmail(email));


        if (Validation.hasErrors()) {
            for (Error error : Validation.errors()) {
                System.out.println(error.getKey() + " : " + error.getMessageKey() + " : " + error.message());
            }
            params.flash();
            validation.keep();
            register();
        }


        User newUser = new User();
        newUser.nickName = nickname;
        newUser.email = email;

        newUser.password = UserService.encodePassword(password);
        UserService.addUser(newUser);

        System.out.println("Cree !");
        saloon();

    }
}
