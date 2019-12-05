package models;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pioche extends IdModel{

    @OneToMany
    private List<Carte> listCarte;

    @OneToOne
    @JoinColumn(name = "idPartie", referencedColumnName = "id")
    private Partie partie;

    // constructeur de la pioche
    public Pioche() {
        this.listCarte = new ArrayList();
    }
    public Pioche(Partie partie) {
        this.partie = partie;
        this.listCarte = new ArrayList();
    }

}


