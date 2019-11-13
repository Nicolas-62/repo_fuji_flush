package org.ib.kanl.services;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Main;

public class MainService {

    public MainService(){

    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
    public void ajouter(Main main, Carte c) {
        main.getMain().add(c);
    }
    public Carte getCarte(Main main, int i)
    {
        return main.getMain().get(i);
    }
    public Carte remove(Main main, int index) {
        return main.getMain().remove(index);
    }
    public String detailMain(Main main) {
        String str = "\t";
        for (int i =0; i < main.getMain().size(); i++)
        {
            str += " | "+getCarte(main, i).getValeur() + " | ";
        }
        return str;
    }
}
