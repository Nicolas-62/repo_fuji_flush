package org.ib.kanl.pojo;


import javax.persistence.*;

@Entity
@Table(name ="Joueur")
public class Joueur< Arraylist > {
    // Variables de classe
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;
    @Column(name="pseudo")
    private String pseudo;
    @Column(name="mail")
    private String mail;
    @Column(name="mdp")
    private String mdp;
    private Main mainJoueur;
    @Column(name="score")
    private int score;

    // Constructeurs
    public Joueur(String pseudo, String mail, String mdp) {
        this.pseudo = pseudo;
        this.mail = mail;
        this.mdp = mdp;
        this.mainJoueur = new Main();
        this.score=score;
    }
    public Joueur(int id, String pseudo) {
        this.id = id;
        this.pseudo = pseudo;
        this.mainJoueur = new Main();
        this.score=score;
    }
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
        return mail;
    }
    public void setEmail(String email) {
        this.mail = email;
    }
    public String getMdp() {
        return mdp;
    }
    public void setMdp(String mdp) {
        this.mdp = mdp;
    }
    public Main getMain() {
        return this.mainJoueur;
    }
    public void setMain(Main main) {
        this.mainJoueur = main;
    }
    public int getScore() {
        return score;
    }
    public void setScore(int score) {
        this.score = score;
    }
    public void DetailJoueur() {
        System.out.println("Le joueur " + this.pseudo + " possède : " + this.mainJoueur.detailMain());
    }
    // Retirer une carte de la main
    public Carte retirerCarteMain(int index) {
        index--;
        return this.mainJoueur.remove(index);
    }
    // piocher une carte
    public void piocherCarte(Carte c){
        this.mainJoueur.ajouter(c);
    }
    public void aGagne() {
        this.score=score++;
    }
}