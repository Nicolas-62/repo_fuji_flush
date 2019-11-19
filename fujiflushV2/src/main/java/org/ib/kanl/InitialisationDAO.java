package org.ib.kanl;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

import org.ib.kanl.dao.CarteDAO;
import org.ib.kanl.dao.JoueurDAO;
import org.ib.kanl.dao.MainDAO;
import org.ib.kanl.dao.PartieDAO;
import org.ib.kanl.dao.PiocheDAO;

// modele classe tiré de https://openclassrooms.com/fr/courses/626954-creez-votre-application-web-avec-java-ee/624784-le-modele-dao#/id/r-624734
public class InitialisationDAO implements ServletContextListener {
	private static final String ATT_PARTIE_DAO = "partieDAO";
	private static final String ATT_PIOCHE_DAO = "piocheDAO";
	private static final String ATT_JOUEUR_DAO = "joueurDAO";
	private static final String ATT_MAIN_DAO = "mainDAO";
	private static final String ATT_CARTE_DAO = "carteDAO";

	private PartieDAO partieDAO;
	private PiocheDAO piocheDAO;
	private JoueurDAO joueurDAO;
	private MainDAO mainDAO;
	private CarteDAO carteDAO;

	@Override
	public void contextInitialized(ServletContextEvent event) {
		/* Récupération du ServletContext lors du chargement de l'application */
		ServletContext servletContext = event.getServletContext();
		/* Instanciation de notre DAOFactory */
		this.partieDAO = new PartieDAO(HibernateEntityManager.getInstance());
		this.piocheDAO = new PiocheDAO(HibernateEntityManager.getInstance());
		this.joueurDAO = new JoueurDAO(HibernateEntityManager.getInstance());
		this.mainDAO = new MainDAO(HibernateEntityManager.getInstance());
		this.carteDAO = new CarteDAO(HibernateEntityManager.getInstance());
		/* Enregistrement dans un attribut ayant pour portée toute l'application */
		servletContext.setAttribute(ATT_PARTIE_DAO, this.partieDAO);
		servletContext.setAttribute(ATT_PIOCHE_DAO, this.piocheDAO);
		servletContext.setAttribute(ATT_JOUEUR_DAO, this.joueurDAO);
		servletContext.setAttribute(ATT_MAIN_DAO, this.mainDAO);
		servletContext.setAttribute(ATT_CARTE_DAO, this.carteDAO);
	}

	@Override
	public void contextDestroyed(ServletContextEvent event) {
		/* Rien à réaliser lors de la fermeture de l'application... */
	}
}
