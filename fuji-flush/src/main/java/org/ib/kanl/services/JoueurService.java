package org.ib.kanl.services;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Joueur;


public class JoueurService {
    public JoueurService() {

    }

    // Méthodes

    // Retirer une carte de la main selon sa position -1 (index)
    public Carte retirerCarteMain(Joueur joueur, int index) {
        return joueur.getMainJoueur().getMain().remove(index-1);
    }
    // piocher une carte
    public void aGagne( Joueur joueur) {
        joueur.setScore(joueur.getScore()+1);
    }
}
