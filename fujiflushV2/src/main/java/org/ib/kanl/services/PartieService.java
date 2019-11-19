package org.ib.kanl.services;

import org.ib.kanl.pojo.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public class PartieService {

    JoueurService joueurService;
    PiocheService piocheService;
    MainService mainService;

    public PartieService( PiocheService piocheService, MainService mainservice, JoueurService joueurService ) {
        this.piocheService = piocheService;
        this.mainService=mainservice;
        this.joueurService=joueurService;
    }

    // METHODES

    public void initialisation(Partie partie){
        demanderNombreJoueur(partie);
        initialisationJoueurs(partie);
        piocheService.creationPioche(partie.getPioche());
        distribuerCartes(partie);
    }
    public void jouer(Partie partie){
        partie.setTapis(new Carte[partie.getNbJoueur()]);
        while ( !partieTerminee(partie) ) {
            for ( int i = 0 ; i < partie.getNbJoueur() ; i++ ) {
                verificationDebutTour(partie, i ); // vérifie si 1 carte est encore sur le tapis au début du tour du joueur
                if ( partieTerminee(partie ) ) {
                    i = partie.getNbJoueur();
                }
                else {
                    afficherMainJoueur(partie, i );       // Affichage main du joueur
                    afficherTapis(partie);// on affiche le tapis
                    jouerCarte(partie, i );               // le joueur choisit une carte et la pose sur le tapis
                    verificationFinTour(partie, i );      // compare la carte aux autres à la fin du tour du joueur
                }
            }
        }
        finDePartie(partie);
    }

    public void demanderNombreJoueur( Partie partie ) {
        Scanner scan = new Scanner( System.in );
        System.out.print( "Entrez le nombre de joueurs : " );
        partie.setNbJoueur( scan.nextInt( ) );
        scan.nextLine( ); // buffer
    } // demande, set et get le nombre de joueurs

    public void ajouterJoueurs( Partie partie, int numero ) {
        Scanner scan = new Scanner( System.in );
        System.out.print( "Entrez le pseudo du joueur " + ( numero + 1 ) + " : " );
        String s = scan.nextLine( );
        partie.getJoueurs( ).add( new Joueur( numero, s ) );
    } // ajoute des joueurs avec leur pseudo à la liste des joueurs

    public void initialisationJoueurs( Partie partie ) {
        for ( int i = 0 ; i < partie.getNbJoueur( ) ; i++ ) {
            ajouterJoueurs( partie, i );
        }
    } // ajoute le nombre de joueurs défini dans la liste de joueurs

    public void distribuerCartes( Partie partie ) {
        if ( partie.getNbJoueur( ) <= 6 ) {
            for ( int i = 0 ; i < 6 ; i++ )                // On donne 6 cartes aux joueurs
            {
                for ( int j = 0 ; j < partie.getNbJoueur( ) ; j++ ) {
                    Joueur currentJoueur = partie.getJoueurs( ).get( j );
                    Carte carte = this.piocheService.piocher( partie.getPioche( ) );
                    currentJoueur.getMainJoueur( ).getMain( ).add( carte );
                }
            }
        }
        else {
            for ( int i = 0 ; i < 5 ; i++ )                // On donne 5 cartes aux joueurs
            {
                for ( int j = 0 ; j < partie.getNbJoueur( ) ; j++ ) {
                    Joueur currentJoueur = partie.getJoueurs( ).get( j );
                    Carte carte = this.piocheService.piocher( partie.getPioche( ) );
                    currentJoueur.getMainJoueur( ).getMain( ).add( carte );
                }
            }
        } // Distribuer cartes aux joueurs
    } // Distribuer les cartes aux joueurs

    public void afficherMainJoueur( Partie partie, int index ) {
        Joueur j;
        j = partie.getJoueurs( ).get( index );
        System.out.println( j.getPseudo( ) + " voici votre main : " + this.mainService.detailMain( j.getMainJoueur()));
    } // affiche la main du joueur i dans l'Arraylist joueurs

    public boolean partieTerminee(Partie partie ) {
        boolean partieTerminee = false;
        for ( int j = 0 ; j < partie.getNbJoueur(); j++ ) {
            if ( ( partie.getJoueurs().get( j ).getMainJoueur().getMain( ).size( ) ) == 0 && partie.getTapis()[ j ] == null ) {
                partieTerminee = true;
            }
        }
        return partieTerminee;
    } // Booléen qui définit si la partie est terminée ou non

    public void jouerCarte( Partie partie, int i ) {
        Scanner scan = new Scanner( System.in );
        System.out.println( "Quelle carte souhaitez-vous jouer ? (Entrez la position dans la liste)" );
        int choix = scan.nextInt( );
        scan.nextLine( );
        Carte c = this.joueurService.retirerCarteMain(partie.getJoueurs().get( i ), choix );
        System.out.println( partie.getJoueurs().get( i ).getPseudo( ) + " a joué la carte : " + c.getValeur( ) );
        partie.getTapis()[ i ] = c;
    } // le joueur i va jouer une carte

    public void afficherTapis(Partie partie ) {
        System.out.println( "Voici le contenu du Tapis : " );
        for ( int i = 0 ; i < partie.getNbJoueur() ; i++ ) {
            Integer valeur;
            if ( partie.getTapis()[ i ] != null ) {
                valeur = partie.getTapis()[ i ].getValeur( );
                System.out.print( " | " + valeur + " | " );
            }
            else {
                System.out.print( " |   | " );
            }
        }
        System.out.println( );
    } // afficher le contenu du tapis

    public int valeur(Partie partie, int index ) {
        Carte c = partie.getTapis()[ index ];
        return c.getValeur( );
    } // obtenir la valeur de la carte du tapis

    public void verificationDebutTour(Partie partie, int i ) {
        if ( partie.getTapis()[ i ] != null ) {
            for ( int j = 0 ; j < partie.getNbJoueur() ; j++ ) {
                if ( partie.getTapis()[ i ] != null && partie.getTapis()[ j ] != null && j != i ) {
                    if ( valeur(partie, i ) == valeur(partie, j ) ) {
                        this.piocheService.defausser(partie.getPioche(), partie.getTapis()[ j ] );
                        partie.getTapis()[ j ] = null;
                        System.out.println( partie.getJoueurs().get( j ).getPseudo( ) + " se défausse d'une carte" );
                    }
                }
            }
            this.piocheService.defausser(partie.getPioche(), partie.getTapis()[ i ] );
            partie.getTapis()[ i ] = null;
            System.out.println( partie.getJoueurs().get( i ).getPseudo( ) + " se défausse d'une carte" );
        }
    } // vérifie si 1 carte est encore sur le tapis au début du tour du joueur

    public void verificationFinTour(Partie partie, int i ) {
        int[] valeurs = new int[ partie.getNbJoueur() ]; // on crée un tableau de taille nbJoueur
        for ( int compteur = 0 ; compteur < partie.getNbJoueur() ; compteur++ ) {
            if ( partie.getTapis()[ compteur ] != null ) {
                valeurs[ compteur ] = valeur(partie,  compteur );
            }
            else {
                valeurs[ compteur ] = 0;
            }
        } // on remplit le tableau avec les valeurs initiales des mains
        for ( int a = 0 ; a < partie.getNbJoueur() ; a++ ) {
            if ( partie.getTapis()[ a ] != null ) {
                for ( int b = a ; b < partie.getNbJoueur() ; b++ ) {
                    if ( partie.getTapis()[ b ] != null && b != a ) {
                        if ( valeur(partie, a ) == valeur(partie, b ) ) valeurs[ a ] = valeurs[ b ] = valeurs[ a ] + valeur(partie, a );
                    }
                }
            }
        } // on compare toutes les valeurs nominales et on les ajoute si elles sont égales puis on change les valeurs dans le tableau valeurs
        for ( int j = 0 ; j < partie.getNbJoueur() ; j++ ) {
            if ( valeurs[ j ] < valeurs[ i ] && valeurs[ j ] != 0 ) {
                this.piocheService.defausser(partie.getPioche(), partie.getTapis()[ j ] );
                partie.getTapis()[ j ] = null;
                Main currentMainJoueur=partie.getJoueurs().get( j ).getMainJoueur();
                Carte carte = this.piocheService.piocher(partie.getPioche());
                this.mainService.ajouter(currentMainJoueur, carte);
                System.out.println( partie.getJoueurs().get( j ).getPseudo( ) + " se défausse et pioche une carte" );
            }
        }  // chaque carte inférieure à la carte posée est défaussée et le joueur pioche une carte
    } // compare la carte aux autres à la fin du tour du joueur

    public void finDePartie(Partie partie) {
        for ( int i = 0 ; i < partie.getNbJoueur() ; i++ ) {
            if ( partie.getJoueurs().get( i ).getMainJoueur( ).getMain().size( ) == 0 && partie.getTapis()[ i ] == null ) {
                Joueur currentJoueur = partie.getJoueurs().get( i );
                this.joueurService.aGagne(currentJoueur);
                System.out.println( "Fin de la Partie ! C'est " + partie.getJoueurs().get( i ).getPseudo( ) + " qui a gagné la partie" );
            }
        }
        partie.setDateFin( new Date( System.currentTimeMillis( ) ));
    }
}
