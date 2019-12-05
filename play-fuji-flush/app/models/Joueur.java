package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Joueur extends IdModel {
    // Variables de classe

    public String pseudo;
    public String email;
    public String mdp;
    public int score;

    @OneToOne(mappedBy = "joueur", cascade = CascadeType.ALL)
    private Main main;

    
    // Constructeurs

    public Joueur() {
    }
    public Joueur(String pseudo) {
        this.pseudo = pseudo;
    }
    
}