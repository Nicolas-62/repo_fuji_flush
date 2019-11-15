package org.ib.kanl.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
public class Joueur< Arraylist > {
    // Variables de classe
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer idJoueur;
    private String pseudo;
    private String email;
    private String mdp;
    private int score;
    @OneToOne(mappedBy = "joueur")
    private Main main;

    // Constructeurs
    public Joueur(int idJoueur, String pseudo) {
        this.idJoueur = idJoueur;
        this.pseudo = pseudo;
        this.main = new Main();
    }

    public Joueur(){
        this.main = new Main();
    }

    // Méthodes
    public Integer getIdJoueur() {
        return idJoueur;
    }

    public void setIdJoueur(Integer id) {
        this.idJoueur = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMdp() {
        return mdp;
    }

    public void setMdp(String mdp) {
        this.mdp = mdp;
    }

    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }

    public Main getMain() {
        return this.main;
    }

    public void setMain(Main main) {
        this.main = main;
    }
}