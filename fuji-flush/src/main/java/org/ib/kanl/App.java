package org.ib.kanl;

import org.ib.kanl.dao.JoueurDAO;
import org.ib.kanl.pojo.*;
import org.ib.kanl.services.JoueurService;
import org.ib.kanl.services.MainService;
import org.ib.kanl.services.PartieService;
import org.ib.kanl.services.PiocheService;

public class App {

    public static MainService mainService = new MainService();
    public static JoueurService joueurService = new JoueurService();
    public static PiocheService piocheService = new PiocheService();
    public static PartieService partieService = new PartieService(piocheService, mainService, joueurService);

     public static Partie partie = new Partie();

    public static void main(String[] args ) {

            partieService.initialisation(partie);
            partieService.jouer(partie);



        // Test DAO
//        JoueurDAO joueurDAO = new JoueurDAO(HibernateEntityManager.getInstance());

        /*Joueur joueur = joueurDAO.get(1);
        System.out.println("Son pseudo : " + joueur.getPseudo());
        System.out.println("Son email : " + joueur.getEmail());
        System.out.println("Son mdp : " + joueur.getMdp());
        System.out.println("Son score : " + joueur.getScore());*/

//        try{
//        Joueur moi = new Joueur("Leti", "lschoepff@outlook.fr", "password");
//        joueurDAO.create(moi);
//        System.out.println("Moi créé avec l'id : [" + moi.getId() + "]");
//        }
//        catch(Exception e){
//            System.out.println("soucis" );
//        }

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

//        HibernateEntityManager.closeEntityManager();

    }
}