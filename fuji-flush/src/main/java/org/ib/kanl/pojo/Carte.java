package org.ib.kanl.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Carte {
    // Variables de classes
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer idCarte;
    private Integer valeur;

    // Constructeur
    public Carte() {}
    public Carte( int valeur ){
        this.valeur=valeur;
    }

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
