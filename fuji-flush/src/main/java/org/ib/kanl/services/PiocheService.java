package org.ib.kanl.services;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Pioche;
import java.util.Collections;
import java.util.List;

public class PiocheService {

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
   public static void creationPioche(Pioche pioche) {
        /* int[] tab =  {2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,2,3,3,3,3,3,3,3,3,3,3,3,3,4,4,4,4,4,4,4,4,4,5,5,5,5,5,5,5,5,6,6,6,6,6,6,7,7,7,7,7,7,8,8,8,8,8,9,9,9,9,10,10,10,10,11,11,11,11,12,12,12,13,13,13,14,14,14,15,15,16,17,18,19,20};
        for ( int i = 0 ; i < 90 ; i++ ) {
            pioche.getListCarte().add( new Carte( tab[i] ) );
        }*/
    for ( int i = 1 ; i <= 16 ; i++ ) {
        pioche.getListCarte().add( new Carte( 2 ) );
    }
        for ( int i = 1 ; i <= 12 ; i++ ) {
            pioche.getListCarte().add( new Carte( 3 ) );
    }
        for ( int i = 1 ; i <= 9 ; i++ ) {
            pioche.getListCarte().add( new Carte( 4 ) );
    }
        for ( int i = 1 ; i <= 8 ; i++ ) {
            pioche.getListCarte().add( new Carte( 5 ) );
    }
        for ( int i = 1 ; i <= 6 ; i++ ) {
            pioche.getListCarte().add( new Carte( 6 ) );
    }
        for ( int i = 1 ; i <= 6 ; i++ ) {
            pioche.getListCarte().add( new Carte( 7 ) );
    }
        for ( int i = 1 ; i <= 5 ; i++ ) {
            pioche.getListCarte().add( new Carte( 8 ) );
    }
        for ( int i = 1 ; i <= 4 ; i++ ) {
            pioche.getListCarte().add( new Carte( 9 ) );
    }
        for ( int i = 1 ; i <= 4 ; i++ ) {
            pioche.getListCarte().add( new Carte( 10 ) );
    }
        for ( int i = 1 ; i <= 4 ; i++ ) {
            pioche.getListCarte().add( new Carte( 11 ) );
    }
        for ( int i = 1 ; i <= 3 ; i++ ) {
            pioche.getListCarte().add( new Carte( 12 ) );
    }
        for ( int i = 1 ; i <= 3 ; i++ ) {
            pioche.getListCarte().add( new Carte( 13 ) );
    }
        for ( int i = 1 ; i <= 3 ; i++ ) {
            pioche.getListCarte().add( new Carte( 14 ) );
    }
        for ( int i = 1 ; i <= 2 ; i++ ) {
            pioche.getListCarte().add( new Carte( 15 ) );
    }
       pioche.getListCarte().add( new Carte( 16 ) );
       pioche.getListCarte().add( new Carte( 17 ) );
       pioche.getListCarte().add( new Carte( 18 ) );
       pioche.getListCarte().add( new Carte( 19 ) );
       pioche.getListCarte().add( new Carte( 20 ) );
        // mélanger l'arrayliste initialement ordonnée
        Collections.shuffle(pioche.getListCarte());
    } // ajouter les 90 cartes de base à l'arraylist pioche et mélanger

    public static Carte piocher(Pioche pioche) {
        Carte c = pioche.getListCarte().get( 0 );
        pioche.getListCarte().remove(0);
        return c;
    } // retirer la première carte de la pioche et retourner la carte

    public static void defausser(Pioche pioche, Carte c ) {
        pioche.getListCarte().add(c);
    } // ajouter une carte à la fin de l'Arraylist pioche

    public Carte getCarte(Pioche pioche, int index) {
        return pioche.getListCarte().get( index );
    } // retirer une carte de la pioche

    public static void supprimerCarte(Pioche pioche, int index) {
        pioche.getListCarte().remove( index );
    } // retirer une carte de la pioche

    public static void ajouter(Pioche pioche, Carte c ) {
        pioche.getListCarte().add(c);
    } // ajouter une carte à la pioche
}

