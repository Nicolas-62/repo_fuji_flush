package org.ib.kanl.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Entity
@Table(name="Partie")
public class Partie {
    // Variables de classe
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    @Column (name="id")
    private Integer id;
    @Column(name="dateDebut")
    private Date dateDebut;
    @Column(name="dateFin")
    private Date dateFin;
    @ManyToMany
    @JoinTable(name = "PartieJoueur",
            joinColumns = @JoinColumn(name = "Joueur_id"),
            inverseJoinColumns = @JoinColumn(name = "Partie_id"))
    private List<Joueur> joueurs;
    @OneToOne
    @JoinColumn(name="idPartie")
    private Pioche pioche;
    @Column (name="tapis")
    private Carte[] tapis;
    @Column(name="nbJoueur")
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        joueurs = new ArrayList<>();
        pioche = new Pioche();
        pioche.creationPioche();
    }

    // METHODE PRINCIPALE
    public void jouerPartie() {
        Scanner scan = new Scanner(System.in);
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        this.dateDebut = new Date(System.currentTimeMillis());
        nbJoueur();
        initialisationJoueurs();
        distribuerCartes();
        System.out.println(pioche.toString());
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
        Scanner scan = new Scanner (System.in);
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
        Scanner scan = new Scanner (System.in);
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
        Scanner scan = new Scanner(System.in);
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
        this.dateFin = new Date(System.currentTimeMillis());
    }
    public String tapisToString(){
        String str = this.tapis[0].getValeur( ).toString();
        for(int i = 1; i < this.tapis.length; i++)
        {
            str += "," + this.tapis[i].getValeur().toString();
        }
        return str;
    }
    public void tapisToArray(String s){
        for (int i=0; i<this.tapis.length; i++){
            tapis[i]=null;
        }
        String[] tab = s.split(",");
        for(int i = 0; i < tab.length; i++)
        {
            this.tapis[i]=new Carte(Integer.parseInt(tab[i]));
        }
    }
    public String dateToString(Date d){
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        return format.format(d);
    }
}