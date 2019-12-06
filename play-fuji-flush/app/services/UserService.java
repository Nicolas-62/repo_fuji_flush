package services;

import models.User;

public class UserService {
	public static boolean checkUser(String email, String password) {
		return User.count("email = ?1 AND password = ?2", email, password) == 1;
	}

	public static boolean checkPassword(String password, String password2) {
		// TODO Auto-generated method stub
		return false;
	}

	public static User getByEmail(String email) {
		return User.find("email = ?1", email).first();
	}
}
