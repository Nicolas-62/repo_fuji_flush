package org.ib.kanl.pojo;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Partie {
    // Variables de classe
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer idPartie;
    private Date dateDebut;
    private Date dateFin;
    @OneToMany
    private List<Joueur> joueurs;
    @OneToOne(mappedBy = "partie")
    private Pioche pioche;
    private Carte[] tapis;
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        this.dateDebut = new Date(System.currentTimeMillis());
        this.pioche = new Pioche();
        this.joueurs = new ArrayList();
    }

    // getters

    public Date getDateDebut( ) {
        return dateDebut;
    }

    public Date getDateFin( ) {
        return dateFin;
    }

    public List < Joueur > getJoueurs( ) {
        return joueurs;
    }

    public Pioche getPioche( ) {
        return pioche;
    }

    public Carte[] getTapis( ) {
        return tapis;
    }

    public int getNbJoueur( ) {
        return nbJoueur;
    }

    // setters

    public void setIdPartie( Integer idPartie ) {
        this.idPartie = idPartie;
    }

    public void setDateDebut( Date dateDebut ) {
        this.dateDebut = dateDebut;
    }

    public void setDateFin( Date dateFin ) {
        this.dateFin = dateFin;
    }

    public void setJoueurs( List < Joueur > joueurs ) {
        this.joueurs = joueurs;
    }
    public void setJoueur( Joueur joueur ) {
        this.joueurs.add(joueur);
    }

    public void setPioche( Pioche pioche ) {
        this.pioche = pioche;
    }

    public void setTapis( Carte[] tapis ) {
        this.tapis = tapis;
    }

    public void setNbJoueur( int nbJoueur ) {
        this.nbJoueur = nbJoueur;
    }

    /*public String tapisToString(){
        String str = this.tapis[0].getValeur( ).toString();
        for(int i = 1; i < this.tapis.length; i++)
        {
            str += "," + this.tapis[i].getValeur().toString();
        }
        return str;
    }
    public void tapisToArray(String s){
        for (int i=0; i<this.tapis.length; i++){
            tapis[i]=null;
        }
        String[] tab = s.split(",");
        for(int i = 0; i < tab.length; i++)
        {
            this.tapis[i]=new Carte(Integer.parseInt(tab[i]));
        }
    }
    public String dateToString(Date d){
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        return format.format(d);
    }*/
}