package org.ib.kanl;

import org.ib.kanl.dao.*;
import org.ib.kanl.pojo.*;
import org.ib.kanl.services.JoueurService;
import org.ib.kanl.services.MainService;
import org.ib.kanl.services.PartieService;
import org.ib.kanl.services.PiocheService;

import java.util.ArrayList;
import java.util.List;

public class App {

    public static void main(String[] args ) {
        CarteDAO carteDAO = new CarteDAO(HibernateEntityManager.getInstance());
        PiocheDAO piocheDAO = new PiocheDAO(HibernateEntityManager.getInstance());
        MainDAO mainDAO = new MainDAO(HibernateEntityManager.getInstance());
        JoueurDAO joueurDAO = new JoueurDAO(HibernateEntityManager.getInstance());
        PartieDAO partieDAO = new PartieDAO(HibernateEntityManager.getInstance());

        /********************** creation des joueurs **************************/
        Joueur dede = new Joueur();
        dede.setPseudo("dede");
        dede.setEmail("dede@outlook.fr");
        dede.setScore(0);
        dede.setMdp("deded1234");
        // SAVE joueur
        joueurDAO.create(dede);
        System.out.println("dede créé avec l'id : [" + dede.getIdJoueur() + "]");

        Joueur leti = new Joueur();
        leti.setPseudo("leti");
        leti.setEmail("lschoepff@outlook.fr");
        leti.setScore(1);
        leti.setMdp("password1234");
        // SAVE joueur
        joueurDAO.create(leti);
        System.out.println("leti créé avec l'id : [" + leti.getIdJoueur() + "]");

        Joueur bob = new Joueur();
        bob.setPseudo("bob");
        bob.setEmail("dede@outlook.fr");
        bob.setScore(0);
        bob.setMdp("deded1234");
        // SAVE joueur
        joueurDAO.create(bob);
        System.out.println("bob créé avec l'id : [" + bob.getIdJoueur() + "]");

        /********************** creation de la partie **************************/
        Partie partie = new Partie();
        // SAVE partie
        partieDAO.create(partie);
        // ajout des joueurs
        partie.setNbJoueur(3);
        partie.setJoueur(dede);
        partie.setJoueur(bob);
        partie.setJoueur(leti);
        // creation pioche, on associe la pioche avec la partie (recupération partie_id pour la table pioche)
        Pioche unePioche = new Pioche(partie);
        // creation pioche avec 20 cartes differentes pour test
        for(int i=1; i<=20; i++){
            Carte uneCarte = new Carte(i);
            // SAVE carte
            carteDAO.create(uneCarte);
            unePioche.getListCarte().add(uneCarte);
        }
        // ajout pioche à la partie
        partie.setPioche(unePioche);
        // SAVE pioche
        piocheDAO.create(unePioche);
        // création des mains des joueurs
        // une main est associée à un joueur
        dede.setMain(new Main(dede));
        leti.setMain(new Main(leti));
        bob.setMain(new Main(bob));
        // distribuer cartes => création  des mains des joueurs
        PartieService.distribuerCartes(partie);
        // SAVE mains
        mainDAO.create(dede.getMain());
        mainDAO.create(leti.getMain());
        mainDAO.create(bob.getMain());
        
        PartieService.jouer(partie);



        HibernateEntityManager.closeEntityManager();
    }
}