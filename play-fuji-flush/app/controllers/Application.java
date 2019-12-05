package controllers;

import play.*;
import play.mvc.*;
import services.PartieService;

import java.util.*;

import models.*;

public class Application extends Controller {

    public static void index() {
    	Joueur nico = new Joueur();
    	nico.email="nlourdel62000@gmail.com";
    	nico.mdp="azerty";
    	nico.pseudo="nico";
    	PartieService.get().addJoueur(nico);
    	
        render();
    }

}