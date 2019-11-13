package org.ib.kanl.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
public class Joueur {
    // Variables de classe
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Integer id;
    @Column(name="pseudo")
    private String pseudo;
    @Column(name="email")
    private String email;
    @Column(name="mdp")
    private String mdp;
    @Column(name="score")
    private int score;
//    @OneToOne
//    @JoinColumn(name="valeurMain")
//    private Main mainJoueur;

    // Constructeurs
    public Joueur(String pseudo, String email, String mdp) {
        this.pseudo = pseudo;
        this.email = email;
        this.mdp = mdp;
    }
//    public Joueur(int id, String pseudo) {
//        this.id = id;
//        this.pseudo = pseudo;
//        this.mainJoueur=new Main();
//    }
    public Joueur(){
    }

    // Méthodes
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
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
//    public Main getMain() {
//        return this.mainJoueur;
//    }
//    public void setMain(Main main) {
//        this.mainJoueur = main;
//    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    // Retirer une carte de la main
//    public Carte retirerCarteMain(int index) {
//        index--;
//        return this.mainJoueur.remove(index);
//    }
//    // piocher une carte
//    public void piocherCarte(Carte c){
//        this.mainJoueur.ajouter(c);
//    }
    public void aGagne() {
        this.score=score++;
    }
}