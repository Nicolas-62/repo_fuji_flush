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
    Integer getValeur() {
                return valeur;
            }
    public void setValeur( Integer valeur ) {
        this.valeur = valeur;
    }
}
