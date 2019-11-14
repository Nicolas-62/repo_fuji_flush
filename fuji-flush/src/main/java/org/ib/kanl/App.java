package org.ib.kanl;

import org.ib.kanl.dao.*;
import org.ib.kanl.pojo.*;
import org.ib.kanl.services.JoueurService;
import org.ib.kanl.services.MainService;
import org.ib.kanl.services.PartieService;
import org.ib.kanl.services.PiocheService;

public class App {

    public static void main(String[] args ) {
        CarteDAO carteDAO = new CarteDAO(HibernateEntityManager.getInstance());
        PiocheDAO piocheDAO = new PiocheDAO(HibernateEntityManager.getInstance());
        MainDAO mainDAO = new MainDAO(HibernateEntityManager.getInstance());
        JoueurDAO joueurDAO = new JoueurDAO(HibernateEntityManager.getInstance());
        PartieDAO partieDAO = new PartieDAO(HibernateEntityManager.getInstance());

        MainService mainService = new MainService();
        JoueurService joueurService = new JoueurService();
        PiocheService piocheService = new PiocheService();
        PartieService partieService = new PartieService(piocheService, mainService, joueurService);

        Partie partie = new Partie();

            //partieService.initialisation(partie);
            //partieService.jouer(partie);

        /*Joueur joueur = joueurDAO.get(1);
        System.out.println("Son pseudo : " + joueur.getPseudo());
        System.out.println("Son email : " + joueur.getEmail());
        System.out.println("Son mdp : " + joueur.getMdp());
        System.out.println("Son score : " + joueur.getScore());*/

        try{
        Joueur moi = new Joueur();
        moi.setPseudo("Letu");
        moi.setEmail("lschoepff@outlook.fr");
        moi.setScore(1);
        moi.setMdp("password1234");

        joueurDAO.create(moi);
        System.out.println("Moi créé avec l'id : [" + moi.getIdJoueur() + "]");
        }
        catch(Exception e){
            System.out.println("soucis" );
        }

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