package services;

import models.Joueur;

public class JoueurService {

	public static boolean checkJoueur(String pseudo, String mdp) {
		return Joueur.count("pseudo = ?1 AND mdp = ?2", pseudo, mdp) == 1;
	}

	public static boolean checkPassword(String mdp, String mdp2) {
		// TODO Auto-generated method stub
		return false;
	}

	public static Joueur getByEmail(String email) {
		return Joueur.find("email = ?1", email).first();
	}

}
