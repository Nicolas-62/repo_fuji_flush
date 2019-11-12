package org.ib.kanl.pojo;

public class Carte {
    // Variables de classes
    private Integer valeur;

    // Constructeur
    Carte(int valeur){
        this.valeur=valeur;
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
            // valeur
    public Integer getValeur() {
                return this.valeur;
            }
    public void setValeur( Integer valeur ) {
        this.valeur = valeur;
    }
}
