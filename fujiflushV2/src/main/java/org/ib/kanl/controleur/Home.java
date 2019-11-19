package org.ib.kanl.controleur;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.ib.kanl.HibernateEntityManager;
import org.ib.kanl.dao.JoueurDAO;
import org.ib.kanl.pojo.Joueur;

import java.io.IOException;
import java.io.PrintWriter;

// tuto Jee : https://openclassrooms.com/fr/courses/626954-creez-votre-application-web-avec-java-ee/619584-la-servlet#/id/r-2131707
public class Home extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final String ATT_JOUEUR_DAO = "joueurDAO";

    private JoueurDAO joueurDAO;

    public void init() throws ServletException {
        /* Récupération d'une instance de notre DAO Utilisateur */
        this.joueurDAO = ( (JoueurDAO) getServletContext().getAttribute( ATT_JOUEUR_DAO ) );
    }


    /*
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	// test connexion bdd, création d'un joueur et affichage console
        Joueur dede = new Joueur();
        dede.setPseudo("dede");
        dede.setEmail("dede@outlook.fr");
        dede.setScore(0);
        dede.setMdp("deded1234");
        // SAVE joueur
        joueurDAO.create(dede);       
        System.out.println("dede créé avec l'id : [" + dede.getIdJoueur() + "]");
        
        //  puis affichage page web
        this.getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);

    }

}
