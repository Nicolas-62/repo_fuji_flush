package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
@Entity
public class Carte extends IdModel{
    // Variables de classes

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
}
