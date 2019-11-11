package org.ib.kanl.pojo;

import java.util.ArrayList;

public class Main {
    // Variables de la classe Main
    private Integer id;
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
}