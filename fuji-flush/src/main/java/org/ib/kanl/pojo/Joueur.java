package org.ib.kanl.pojo;

public class Joueur< Arraylist > {
    // Variables de classe
    protected Integer id;
    protected String pseudo;
    protected String mail;
    protected String mdp;
    protected Main main;

    // Constructeurs
    public Joueur(String pseudo, String mmail, String mdp){
        this.pseudo=pseudo;
        this.email=email;
        this.mdp=mdp;
    }
    public Joueur(){}

    // Méthodes
        // Getters et setters
            // id
    public Integer getId( ) {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
    }
            // pseudo
    public String getPseudo( ) {
        return pseudo;
    }
    public void setPseudo( String pseudo ) {
        this.pseudo = pseudo;
    }
            // email
    public String getEmail( ) {
        return mail;
    }
    public void setEmail( String email ) {
        this.mail = email;
    }
            // mdp
    public String getMdp( ) {
        return mdp;
    }
    public void setMdp( String mdp ) {
        this.mdp = mdp;
    }
            // main
    public Main getMain( ) {
        return this.main;
    }
    public void setMain( Main main ) {
        this.main = main;
    }
}
