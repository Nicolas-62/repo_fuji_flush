package org.ib.kanl.pojo;

public class Joueur< Arraylist > {
    // Variables de classe
    protected Integer id;
    protected String pseudo;
    protected String mail;
    protected String mdp;
    protected Main mainJoueur;

    // Constructeurs
    public Joueur(String pseudo, String mail, String mdp){
        this.pseudo=pseudo;
        this.mail=mail;
        this.mdp=mdp;
        this.mainJoueur=new Main();
    }
    public Joueur(int id, String pseudo){
        this.id=id;
        this.pseudo=pseudo;
        this.mainJoueur=new Main();
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
        return this.mainJoueur;
    }
    public void setMain( Main main ) {
        this.mainJoueur = main;
    }

    public void DetailJoueur() {
        System.out.println("Le joueur " + this.pseudo + " possède : " + this.mainJoueur.detailMain());
    }
}
        // DEPOSER CARTE SUR LE TAPIS
// public Carte CarteAPoser(int valeur)
// {
//    for(int i = 0; i < mainJoueur.getSize(); i++)
//    {
//       if(valeur == mainJoueur.getCarte(i).valeur)
//       {
//          return this.mainJoueur.getCarte(i);
//       }
//       else
//       {
//          return new Carte(666);
//       }
//    }
// }
