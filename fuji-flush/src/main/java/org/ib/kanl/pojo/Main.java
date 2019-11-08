package org.ib.kanl.pojo;

public class Main {
    // Variables de la classe Main
    protected Integer id;
    protected Carte carte;
    protected Joueur joueur;

    // Constructeur de la classe Main
    public Main(){}

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
                // id
    public Integer getId( ) {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
    }
                 // carte
    public Carte getCarte( ) {
        return carte;
    }
    public void setCarte( Carte carte ) {
        this.carte = carte;
    }
                // joueur
    public Joueur getJoueur( ) {
        return joueur;
    }
    public void setJoueur( Joueur joueur ) {
        this.joueur = joueur;
    }
}
