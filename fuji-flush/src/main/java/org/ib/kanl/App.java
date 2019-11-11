package org.ib.kanl;

import org.ib.kanl.pojo.*;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    public static Partie partie = new Partie();

    public static void main( String[] args ) {
        // s'enregister si nouveau joueur
        // se connecter si déjà inscrit
        // Menu : commencer une nouvelle partie
        //        en rejoindre une
        //        revoir une partie
        //        voir le classement

        // nouvelle partie en mode console
        partie.Partie();
    }
}