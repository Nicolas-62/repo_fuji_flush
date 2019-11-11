package org.ib.kanl.pojo;
import java.util.ArrayList;
import java.util.Collections;
public class Pioche< Arraylist > {
    // variables de classe
    private Integer id;
    private ArrayList pioche;
    // constructeur de la pioche
    public Pioche (){
        pioche=new ArrayList();
        this.pioche = pioche;
    }
    //----------------------------------------------------------Methodes-----------------------------------------------------------------------
    public void creationPioche(){
        for (int i = 1; i <= 16; i++) { this.pioche.add(new Carte (2)); }
        for (int i = 1; i <= 12; i++) { this.pioche.add(new Carte (3)); }
        for (int i = 1; i <= 9; i++)  { this.pioche.add(new Carte (4)); }
        for (int i = 1; i <= 8; i++)  { this.pioche.add(new Carte (5)); }
        for (int i = 1; i <= 6; i++)  { this.pioche.add(new Carte (6)); }
        for (int i = 1; i <= 6; i++)  { this.pioche.add(new Carte (7)); }
        for (int i = 1; i <= 5; i++)  { this.pioche.add(new Carte (8)); }
        for (int i = 1; i <= 4; i++)  { this.pioche.add(new Carte (9)); }
        for (int i = 1; i <= 4; i++)  { this.pioche.add(new Carte (10)); }
        for (int i = 1; i <= 4; i++)  { this.pioche.add(new Carte (11)); }
        for (int i = 1; i <= 3; i++)  { this.pioche.add(new Carte (12)); }
        for (int i = 1; i <= 3; i++)  { this.pioche.add(new Carte (13)); }
        for (int i = 1; i <= 3; i++)  { this.pioche.add(new Carte (14)); }
        for (int i = 1; i <= 2; i++)  { this.pioche.add(new Carte (15)); }
        this.pioche.add(new Carte (16));
        this.pioche.add(new Carte (17));
        this.pioche.add(new Carte (18));
        this.pioche.add(new Carte (19));
        this.pioche.add(new Carte (20));
        // mélanger l'arrayliste initialement ordonnée
        Collections.shuffle(this.pioche);
        } // ajouter les 90 cartes de base à l'arraylist pioche et mélanger
    public Carte get(int i ) {
        return (Carte) this.pioche.get(i);
    } // récupérer une carte de la pioche
    public Carte tirerCartePioche() {
        Carte c;
        c = (Carte) pioche.get(0);
        pioche.remove(0);
        return c;
    } // retirer la première carte de la pioche et retourner la carte
    public void defausser(Carte c){
        this.pioche.add(c);
    } // ajouter une carte à la fin de l'Arraylist pioche
}

