package org.ib.kanl.services;

import org.ib.kanl.pojo.Carte;
import org.ib.kanl.pojo.Main;

public class MainService {

    public MainService(){

    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
    public static void ajouter(Main main, Carte c) {
        main.getListCarte().add(c);
    }
    public static Carte getCarte(Main main, int i)
    {
        return main.getListCarte().get(i);
    }
    public static Carte remove(Main main, int index) {
        return main.getListCarte().remove(index);
    }
    public static String detailMain(Main main) {
        String str = "\t";
        for (int i =0; i < main.getListCarte().size(); i++)
        {
            str += " | "+getCarte(main, i).getValeur() + " | ";
        }
        return str;
    }
}
