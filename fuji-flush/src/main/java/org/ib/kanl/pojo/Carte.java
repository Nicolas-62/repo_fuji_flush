package org.ib.kanl.pojo;

public class Carte {
    // Variables de classes
    protected Integer valeur;

    // Constructeur
    public Carte(int valeur){
        this.valeur=valeur;
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
            // valeur
    public Integer getValeur( ) {
                return valeur;
            }
    public void setValeur( Integer valeur ) {
        this.valeur = valeur;
    }
}
