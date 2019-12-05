package models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
@Entity
public class Main extends IdModel{


    @OneToMany
    public List<Carte> listCarte;

    @OneToOne
    @JoinColumn(name = "idJoueur", referencedColumnName = "id")
    public Joueur joueur;


    // Constructeurs de la classe Main
    public Main() {
        this.listCarte = new ArrayList();
    }
    public Main(Joueur joueur) {
        this.joueur = joueur;
        this.listCarte = new ArrayList();
    }



}