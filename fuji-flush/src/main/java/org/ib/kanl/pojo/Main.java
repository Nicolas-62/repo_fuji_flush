package org.ib.kanl.pojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Main {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer idMain;

    @OneToMany
    private List<Carte> listCarte;

    @OneToOne
    @JoinColumn(name = "joueur_id", referencedColumnName = "idJoueur")
    private Joueur joueur;


    // Constructeurs de la classe Main
    public Main() {
        this.listCarte = new ArrayList();
    }
    public Main(Joueur joueur) {
        this.joueur = joueur;
        this.listCarte = new ArrayList();
    }


   // Getters et Setters
    public Joueur getJoueur() {
        return joueur;
    }
    public void setJoueur(Joueur joueur) {
        this.joueur = joueur;
    }
    public Integer getIdMain() {
        return idMain;
    }

    public void setIdMain(Integer idMain) {
        this.idMain = idMain;
    }

    public List < Carte > getListCarte( ) {
        return this.listCarte;
    }

    public void setListCarte( List < Carte > listCarte ) {
        this.listCarte = listCarte;
    }

    /*public String mainToString(){
        String str = this.main.get( 0 ).getValeur( ).toString();
        for(int i = 1; i < this.main.size(); i++)
        {
            str += "," + this.main.get(i).getValeur().toString();
        }
        return str;
    }
    public static void mainToArray(String s) {
        this.main.clear();
        String[] tab = s.split(",");

        for(int i = 0; i < tab.length; i++)
        {
            this.main.add(new Carte(Integer.parseInt(tab[i])));
        }
    }*/
}