package org.ib.kanl.pojo;

import java.util.ArrayList;

public class Tapis {
    // variables de classe
    protected Integer id;
    protected Carte carte;
    protected ArrayList <Carte> tapis;

    // constructeur du tapis
    public Tapis()
    {
        this.tapis = new ArrayList();
    }

    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
    public void AfficherTapis() {
        String contenuTapis ="";
        System.out.println ("Voici le contenu du Tapis : ");
        for (int i = 0; i < tapis.size(); i++)
        {
            if(this.tapis.get(i) != null)
            {
                contenuTapis += this.tapis.get(i).getValeur();
            }
        }
        System.out.println(contenuTapis);
    }

    public Carte getTapisJoueur(int numeroJoueur) {
        return tapis.get(numeroJoueur);
    }
}
