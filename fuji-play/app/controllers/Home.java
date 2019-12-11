package controllers;

import models.User;
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

    public static void createAccount(String nickname, String email, String password, String passwordRepeat) {


        if(UserService.getByEmail(email) != null)
        {
            saloon();
        }
        else
        {
            if (Validation.hasErrors()) {
                register();
            }
            else
            {
                User newUser = new User();
                newUser.nickName = nickname;
                newUser.email = email;

                if (password.equals(passwordRepeat)) {
                    newUser.password = UserService.encodePassword(password);
                    UserService.addUser(newUser);

                    System.out.println("Cree !");
                    saloon();
                }
                else
                {
                    register();
                }
            }
        }
    }
}
