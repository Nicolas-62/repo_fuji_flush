package org.ib.kanl.pojo;

public class Partie {
 // Variables de classe
    protected Integer id;
    protected int nbJoueur;
    protected String dateDebut;
    protected String dateFin;
    protected Joueur joueur;
    protected Pioche pioche;
    protected Tapis tapis;

    // Constructeurs
    public Partie () {}
    public Partie (int nbJoueur, String dateDebut){
        this.nbJoueur=nbJoueur;
        this.dateDebut=dateDebut;
    }
}
