package org.ib.kanl.controllers;

import javax.validation.Valid;

import org.ib.kanl.dao.EntityManagerDAO;
import org.ib.kanl.dao.JoueurDAO;
import org.ib.kanl.model.Joueur;
import org.ib.kanl.repository.JoueurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


@Controller
public class LoginController {

	@Autowired
	JoueurRepository joueurRepo;
	
	EntityManagerDAO entityManagerDAO;
	
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public String pagelogin(Model model) {
		// test utilisation DAO
		JoueurDAO joueurDAO = new JoueurDAO(entityManagerDAO.getInstance());	
		Joueur unJoueur = new Joueur();
		unJoueur.setEmail("kawtar@gmail.com");
		unJoueur.setMdp("azerty");
		unJoueur.setPseudo("kawtar");
		joueurDAO.create(unJoueur);
		
		
		model.addAttribute("message", "Bienvenue nico !");
//		Joueur unJoueur = new Joueur();
//		unJoueur.setEmail("nl@gmail.com");
//		unJoueur.setMdp("azerty");
//		unJoueur.setPseudo("nico");
//		joueurRepo.save(unJoueur);
		System.out.println("joueur créé !");
				
		return "login";
		
		
	}	
	@RequestMapping(value = { "/login" }, method = RequestMethod.POST)
	public String login(@Valid Joueur joueur, BindingResult results, Model model) {
		model.addAttribute("message", "Bienvenue nico !");
		Joueur unJoueur = new Joueur();
		unJoueur.setMdp("azerty");
		unJoueur.setPseudo("nico");
		joueurRepo.save(unJoueur);
		System.out.println("joueur créé !");
		System.out.println("joueur : " + unJoueur.toString());
			
		
		return "home";
		
		
	}
	
}
