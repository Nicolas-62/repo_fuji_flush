package org.ib.kanl;

import org.ib.kanl.dao.JoueurDAO;
import org.ib.kanl.pojo.*;

public class App {
    //public static Partie partie = new Partie();

    public static void main( String[] args ) {
        // s'enregister si nouveau joueur
        // se connecter si déjà inscrit
        // Menu : commencer une nouvelle partie
        //        en rejoindre une
        //        revoir une partie
        //        voir le classement

        // Test DAO
        JoueurDAO joueurDAO = new JoueurDAO(HibernateEntityManager.getInstance());

        /*Joueur joueur = joueurDAO.get(1);
        System.out.println("Son pseudo : " + joueur.getPseudo());
        System.out.println("Son email : " + joueur.getEmail());
        System.out.println("Son mdp : " + joueur.getMdp());
        System.out.println("Son score : " + joueur.getScore());*/

        try{Joueur moi = new Joueur();
        moi.setPseudo("Leti");
        moi.setEmail( "lschoepff@outlook.fr" );
        moi.setMdp( "password" );
        joueurDAO.create(moi);
        System.out.println("Moi créé avec l'id : [" + moi.getId() + "]");

        Joueur toi = new Joueur();
        toi.setPseudo("al");
        toi.setEmail( "alex@outlook.fr" );
        toi.setMdp( "password1234" );
        joueurDAO.create(toi);
        System.out.println("Moi créé avec l'id : [" + toi.getId() + "]");}
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



        // nouvelle partie en mode console
        //partie.jouerPartie();
    }
}