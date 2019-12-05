package services;

import models.Joueur;

public class PartieService {

	public static PartieService INSTANCE;


  private PartieService() {

  }

  public static PartieService get() {
      if (INSTANCE == null) {
          INSTANCE = new PartieService();
      }
      return INSTANCE;
  }
  public static void addJoueur(Joueur joueur) {
	  joueur.save();
  }
}
