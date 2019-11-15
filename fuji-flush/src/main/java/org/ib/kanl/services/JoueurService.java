package org.ib.kanl.services;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Joueur;


public class JoueurService {

    // Méthodes

    // Retirer une carte de la main selon sa position -1 (index)
    public static Carte retirerCarteMain(Joueur joueur, int index) {
        return joueur.getMain().getListCarte().remove(index-1);
    }
    // piocher une carte
    public static void aGagne( Joueur joueur) {
        joueur.setScore(joueur.getScore()+1);
    }
}
