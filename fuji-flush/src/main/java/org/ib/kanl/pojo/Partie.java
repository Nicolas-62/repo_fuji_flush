package org.ib.kanl.pojo;

import java.util.ArrayList;
import java.util.Scanner;

public class Partie {
    // Variables de classe
    private String dateDebut;
    private ArrayList<Joueur> joueurs;
    private Pioche pioche;
    private Carte[] tapis;
    private Scanner scan = new Scanner(System.in);
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        joueurs = new ArrayList<>();
        pioche = new Pioche();
        pioche.creationPioche();
        this.tapis = tapis;
        this.dateDebut = dateDebut;
    }

    // METHODES PRINCIPALES
    public void Partie() {
        nbJoueur();
        initialisationJoueurs();
        distribuerCartes();
            tapis = new Carte[nbJoueur];
            while (!partieTerminee()) {
                for (int i = 0; i < nbJoueur; i++) {
                    verificationDebutTour(i);
                    afficherMainJoueur(i);       // Affichage main du joueur
                    afficherTapis();// on affiche le tapis
                    jouerCarte(i);               // le joueur choisit une carte et la pose sur le tapis
                    verificationFinTour(i);
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
        boolean partieTerminee = false;
        for (int j = 0; j < nbJoueur; j++) {
            if ((joueurs.get(j).getMain().getSize()) == 0 && tapis[j]==null) {
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
    }
    public void verificationFinTour(int i) {
        int valeuri=valeur(i);
        for (int j = 0; j < nbJoueur; j++) {
            if (tapis[i] != null && tapis[j] != null && j!=i) {
                int valeurj=valeur(j);
                if (valeur(j) == valeur(i)) {
                    valeuri = valeuri + valeur(j);
                    valeurj = valeurj+valeur(i);
                }
                for (int k=0; k<nbJoueur; k++){
                    if (tapis[i] != null && tapis[j] != null && tapis[k] !=null) {
                        if (valeur(i) == valeur(k)) {
                            valeuri = valeuri + valeur(k);
                        }
                        if (valeur(j) == valeur(k)) {
                            valeurj = valeurj + valeur(k);
                        }
                    }
                }
                if (valeurj<valeuri) {
                    pioche.defausser(tapis[j]);
                    tapis[j] = null;
                    joueurs.get(j).piocherCarte(pioche.tirerCartePioche());
                    System.out.println(joueurs.get(j).getPseudo() + " se défausse et pioche une carte");
                }
            }
        }
    }
    public void finDePartie(){
        for (int i = 0; i < nbJoueur; i++) {
            if (joueurs.get(i).getMain().getSize() == 0 && tapis[i] == null) {
                joueurs.get(i).aGagne();
                System.out.println("Fin de la Partie ! C'est " + joueurs.get(i).getPseudo() + " qui a gagné la partie");
            }
        }
    }
}