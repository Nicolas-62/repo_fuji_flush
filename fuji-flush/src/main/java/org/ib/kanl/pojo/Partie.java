package org.ib.kanl.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Scanner;

@Entity
@Table(name="Partie")
public class Partie {
    // Variables de classe
    @Column(name="dateDebut")
    private String dateDebut;
    private ArrayList<Joueur> joueurs;
    @Column (name="pioche")
    private Pioche pioche;
    @Column (name="tapis")
    private Carte[] tapis;
    private Scanner scan = new Scanner(System.in);
    @Column(name="nbJoueur")
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        joueurs = new ArrayList<>();
        pioche = new Pioche();
        pioche.creationPioche();
        this.tapis = tapis;
        this.dateDebut = dateDebut;
    }

    // METHODE PRINCIPALE
    public void Partie() {
        nbJoueur();
        initialisationJoueurs();
        distribuerCartes();
            tapis = new Carte[nbJoueur];
            while (!partieTerminee()) {
                for (int i = 0; i < nbJoueur; i++) {
                    verificationDebutTour(i); // vérifie si 1 carte est encore sur le tapis au début du tour du joueur
                    if (partieTerminee()) {
                        i=nbJoueur;
                    }
                    else {
                        afficherMainJoueur( i );       // Affichage main du joueur
                        afficherTapis( );// on affiche le tapis
                        jouerCarte( i );               // le joueur choisit une carte et la pose sur le tapis
                        verificationFinTour( i );      // compare la carte aux autres à la fin du tour du joueur
                    }
                }
            }
            finDePartie();
    }
    // METHODES SECONDAIRES
    public int nbJoueur(){
        System.out.print("Entrez le nombre de joueurs : ");
        nbJoueur = scan.nextInt();
        scan.nextLine(); // buffer
        return nbJoueur;
    }
    public void initialisationJoueurs() {
        for (int i = 0; i < nbJoueur; i++) {
            ajouterJoueurs(i);
        }
    } // définir le nombre de joueurs en début de partie
    public void distribuerCartes() {
        if (nbJoueur <= 6) {
            for (int i = 0; i < 6; i++)                // On donne 6 cartes aux joueurs
            {
                for (int j = 0; j < nbJoueur; j++) {
                    joueurs.get(j).getMain().ajouter(pioche.tirerCartePioche());
                }
            }
        } else {
            for (int i = 0; i < 5; i++)                // On donne 5 cartes aux joueurs
            {
                for (int j = 0; j < nbJoueur; j++) {
                    joueurs.get(j).getMain().ajouter(pioche.tirerCartePioche());
                }
            }
        } // Distribuer cartes aux joueurs
    } // Distribuer les cartes aux joueurs
    public void ajouterJoueurs(int numero) {
        System.out.print("Entrez le pseudo du joueur " + (numero + 1) + " : ");
        String s = scan.nextLine();
        joueurs.add(new Joueur(numero, s));
    } // ajouter des joueurs avec leur pseudo
    public void afficherMainJoueur(int index) {
        Joueur j;
        j = joueurs.get(index);
        System.out.println(j.getPseudo() + " voici votre main : " + j.getMain().detailMain());
    } // affiche la main du joueur i dans l'Arraylist joueurs
    public boolean partieTerminee() {
        boolean partieTerminee=false;
        for (int j=0; j<nbJoueur; j++) {
            if ((joueurs.get(j).getMain().getSize()) == 0 && tapis[j] == null) {
                partieTerminee = true;
            }
        }
        return partieTerminee;
    } // Booléen qui définit si la partie est terminée ou non
    public void jouerCarte(int i) {
        System.out.println("Quelle carte souhaitez-vous jouer ? (Entrez la position dans la liste)");
        int choix=scan.nextInt();
        scan.nextLine();
        Carte c =joueurs.get(i).retirerCarteMain(choix);
        System.out.println(joueurs.get(i).getPseudo()+ " a joué la carte : "+c.getValeur());
        tapis[i]=c;
    } // le joueur i va jouer une carte
    public void afficherTapis() {
        System.out.println ("Voici le contenu du Tapis : ");
        for (int i=0; i<nbJoueur; i++) {
            Integer valeur;
            if (tapis[i]!=null) {
                valeur = tapis[i].getValeur();
                System.out.print(" | " + valeur + " | ");
            }
            else {
                System.out.print(" |   | ");
            }
        }
        System.out.println();
    } // afficher le contenu du tapis
    public int valeur(int index) {
            Carte c = tapis[index];
            return c.getValeur();
    } // obtenir la valeur de la carte du tapis
    public void verificationDebutTour(int i){
        if (tapis[i] != null) {
            for (int j = 0; j < nbJoueur; j++) {
                if (tapis[i] != null && tapis[j] != null && j!=i) {
                    if (valeur(i) == valeur(j)) {
                        pioche.defausser(tapis[j]);
                        tapis[j] = null;
                        System.out.println(joueurs.get(j).getPseudo()+ " se défausse d'une carte");
                    }
                }
            }
            pioche.defausser(tapis[i]);
            tapis[i] = null;
            System.out.println(joueurs.get(i).getPseudo() + " se défausse d'une carte");
        }
    } // vérifie si 1 carte est encore sur le tapis au début du tour du joueur
    public void verificationFinTour(int i) {
        int[] valeurs = new int[nbJoueur]; // on crée un tableau de taille nbJoueur
        for (int compteur = 0; compteur < nbJoueur; compteur++) {
            if (tapis[compteur] != null) {
                valeurs[compteur] = valeur(compteur);
            } else {
                valeurs[compteur] = 0;
            }
        } // on remplit le tableau avec les valeurs initiales des mains
        for (int a = 0; a < nbJoueur; a++) {
            if (tapis[a]!=null) {
                for (int b = a; b < nbJoueur; b++) {
                    if (tapis[b] != null && b!=a) {
                        if (valeur(a) == valeur(b)) valeurs[a] = valeurs[b] = valeurs[a] + valeur(a);
                    }
                }
            }
        } // on compare toutes les valeurs nominales et on les ajoute si elles sont égales puis on change les valeurs dans le tableau valeurs
        for (int j = 0; j < nbJoueur; j++) {
            if (valeurs[j] < valeurs[i] && valeurs[j]!=0) {
                pioche.defausser(tapis[j]);
                tapis[j] = null;
                joueurs.get(j).piocherCarte(pioche.tirerCartePioche());
                System.out.println(joueurs.get(j).getPseudo() + " se défausse et pioche une carte");
            }
        }  // chaque carte inférieure à la carte posée est défaussée et le joueur pioche une carte
    } // compare la carte aux autres à la fin du tour du joueur
    public void finDePartie(){
        for (int i = 0; i < nbJoueur; i++) {
            if (joueurs.get(i).getMain().getSize() == 0 && tapis[i] == null) {
                joueurs.get(i).aGagne();
                System.out.println("Fin de la Partie ! C'est " + joueurs.get(i).getPseudo() + " qui a gagné la partie");
            }
        }
    }
}