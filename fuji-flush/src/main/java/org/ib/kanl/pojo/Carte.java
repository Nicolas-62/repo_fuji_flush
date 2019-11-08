package org.ib.kanl.pojo;

public class Carte {
    // Variables de classes
    protected Integer id;
    protected Integer valeur;

    // Constructeur
    public Carte(int id, int valeur){
        this.id=id;
        this.valeur=valeur;
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
            // id
    public Integer getId( ) {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
    }
            // valeur
    public Integer getValeur( ) {
                return valeur;
            }
    public void setValeur( Integer valeur ) {
        this.valeur = valeur;
    }
}
