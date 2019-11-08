package org.ib.kanl.pojo;

public class Joueur< Arraylist > {
    // Variables de classe
    protected Integer id;
    protected String pseudo;
    protected String email;
    protected String password;
    protected Main main;

    // Constructeurs
    public Joueur(String pseudo, String email, String password){
        this.pseudo=pseudo;
        this.email=email;
        this.password=password;
    }
    public Joueur(int id, String pseudo, String email, String password){
        this.id=id;
        this.pseudo=pseudo;
        this.email=email;
        this.password=password;
    }
    public Joueur(){}

    // Méthodes
}
