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

        Joueur letu = new Joueur();
        letu.setPseudo("Letu");
        letu.setEmail("lschoepff@outlook.fr");
        letu.setScore(1);
        letu.setMdp("password1234");
        // SAVE joueur
        joueurDAO.create(letu);
        System.out.println("Letu créé avec l'id : [" + letu.getIdJoueur() + "]");

        Joueur roger = new Joueur();
        roger.setPseudo("roger");
        roger.setEmail("dede@outlook.fr");
        roger.setScore(0);
        roger.setMdp("deded1234");
        // SAVE joueur
        joueurDAO.create(roger);
        System.out.println("roger créé avec l'id : [" + roger.getIdJoueur() + "]");

        /********************** creation de la partie **************************/
        Partie partie = new Partie();
        // ajout des joueurs
        partie.setNbJoueur(3);
        partie.setJoueur(dede);
        partie.setJoueur(roger);
        partie.setJoueur(letu);
        // creation pioche avec 20 cartes differentes pour test
        Pioche unePioche = new Pioche();
        for(int i=1; i<=20; i++){
            Carte uneCarte = new Carte(i);
            // SAVE carte
            carteDAO.create(uneCarte);
           unePioche.getListCarte().add(uneCarte);
        }
        // SAVE pioche
        piocheDAO.create(unePioche);
        // ajout pioche au jeu
        partie.setPioche(unePioche);
        // distribuer cartes => création  des mains des joueurs
        PartieService.distribuerCartes(partie);
        // SAVE mains
        mainDAO.create(dede.getMain());
        mainDAO.create(letu.getMain());
        mainDAO.create(roger.getMain());

        //PartieService.jouer(partie);

        /*moi.setNom("CESAR");
        //moi.setPrenom("Jules");
        eleveDAO.update(moi);
        System.out.println("Moi mis à jour !");

        Eleve monAutreMoi = eleveDAO.get(moi.getId());
        System.out.println("Mon nom : " + monAutreMoi.getNom());
        System.out.println("Mon prénom : " + monAutreMoi.getPrenom());

        eleveDAO.delete(moi);
        System.out.println("Moi supprimé !");

        List <Eleve> eleves = eleveDAO.find();
        System.out.println("Nb eleve dans l'école : " + eleves.size());

         */

        HibernateEntityManager.closeEntityManager();
    }
}