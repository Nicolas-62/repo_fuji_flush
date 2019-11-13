package org.ib.kanl.pojo;

import org.hibernate.annotations.Columns;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name="PartieJoueur")
public class Main {
    // Variables de la classe Main

    /*@MapsId
    @JoinColumns({
            @JoinColumn(name="PartieJoueur_Joueur_id", referencedColumnName="idJoueur"),
            @JoinColumn(name="PartieJoueur_Partie_id", referencedColumnName="idPartie")})
    private Integer idJoueur, idPartie;*/
//    @Id
//    @Column(name="idMain")
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
    private Integer idMain;


    private List<Carte> main;

    // Constructeur de la classe Main

    public Main(){
        this.main=new ArrayList();
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
                // id

    public Integer getIdMain() {
        return idMain;
    }

    public void setIdMain(Integer idMain) {
        this.idMain = idMain;
    }

    public void ajouter(Carte c) {
        this.main.add(c);
    }
    public Carte getCarte(int i)
    {
        return this.main.get(i);
    }
    public String detailMain() {
        String str = "\t";
        for (int i =0; i < main.size (); i++)
        {
            str += " | "+getCarte(i).getValeur() + " | ";
        }
        return str;
    }
    public int getSize()
    {
        return this.main.size();
    }
    public Carte remove(int index) {
        return this.main.remove(index);
    }
    public String mainToString(){
        String str = this.main.get( 0 ).getValeur( ).toString();
        for(int i = 1; i < this.main.size(); i++)
        {
            str += "," + this.main.get(i).getValeur().toString();
        }
        return str;
    }
    public void mainToArray(String s) {
        this.main.clear();
        String[] tab = s.split(",");

        for(int i = 0; i < tab.length; i++)
        {
            this.main.add(new Carte(Integer.parseInt(tab[i])));
        }
    }
}