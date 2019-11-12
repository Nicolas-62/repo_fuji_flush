package org.ib.kanl.pojo;

import javax.persistence.*;
import java.util.ArrayList;
@Entity
@Table(name="Main")
public class Main {
    // Variables de la classe Main
    @Id
    @Column(name="id")
    @GeneratedValue
    private Integer id;

    @Column (name="valeurMain")
    private ArrayList <Carte> main;

    // Constructeur de la classe Main

    public Main(){
        this.main=new ArrayList();
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
        // Getters et Setters
                // id
    public Integer getId( ) {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
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