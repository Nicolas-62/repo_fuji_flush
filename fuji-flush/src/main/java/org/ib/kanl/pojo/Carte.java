package org.ib.kanl.pojo;

public class Carte {
    // Variables de classes
    private Integer idCarte;
    private Integer valeur;

    // Constructeur
    public Carte( int valeur ){
        this.valeur=valeur;
    }

    public Carte() {}

        // Getters et Setters

    public Integer getValeur() {
                return this.valeur;
            }

    public void setValeur( Integer valeur ) {
        this.valeur = valeur;
    }

    public Integer getIdCarte( ) {
        return idCarte;
    }

    public void setIdCarte( Integer idCarte ) {
        this.idCarte = idCarte;
    }
}
