package org.ib.kanl;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Joueur;
import org.ib.kanl.pojo.Pioche;
import org.ib.kanl.pojo.Tapis;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App {
    public static int nbJoueurs;
    public static Scanner scan = new Scanner( System.in );
    public static ArrayList < Joueur > joueurs;
    public static Pioche pioche = new Pioche( );
    public static Tapis tapis = new Tapis( );

    public static void main( String[] args ) {
        InitialisationJoueurs( );            // Initialiser les joueurs
        pioche.creationPioche( );               // Créer la pioche
        distribuerCartes( nbJoueurs );         // Distribuer les cartes aux joueurs
        AfficherJoueurs( );                // Test des joueurs
        boolean partieTerminee = false;
        while ( !partieTerminee ) {
        }
    }

    // joueurs
    // Ajouter
    public static void AjouterJoueurs( int numero ) {
        System.out.print( "Entrez le pseudo du joueur " + ( numero + 1 ) + " : " );
        String s = scan.nextLine( );
        joueurs.add( new Joueur( numero, s ) );
    }

    // Initialiser
    public static void InitialisationJoueurs( ) {
        joueurs = new ArrayList <>( );
        System.out.print( "Entrez le nombre de joueurs : " );
        nbJoueurs = scan.nextInt( );
        scan.nextLine( ); // buffer
        for ( int i = 0 ; i < nbJoueurs ; i++ ) {
            AjouterJoueurs( i );
        }
    }

    // Afficher
    public static void AfficherJoueurs( ) {
        for ( Joueur j : joueurs ) {
            j.DetailJoueur( );
        }
    }

    // Distribuer cartes aux joueurs
    public static void distribuerCartes( int nbJ ) {
        if ( nbJ <= 6 ) {
            for ( int i = 0 ; i < 6 ; i++ )                // On donne 6 cartes aux joueurs
            {
                for ( int j = 0 ; j < nbJ ; j++ ) {
                    joueurs.get( j ).getMain( ).ajouter( tirerCartePioche( ) );
                }
            }
        }
        else {
            for ( int i = 0 ; i < 5 ; i++ )                // On donne 5 cartes aux joueurs
            {
                for ( int j = 0 ; j < nbJ ; j++ ) {
                    joueurs.get( j ).getMain( ).ajouter( tirerCartePioche( ) );
                }
            }
        }
    }
    public static Carte tirerCartePioche() {
        Carte c;
        c = pioche.get(0);
        pioche.remove(0);
        return c;
    }
}



