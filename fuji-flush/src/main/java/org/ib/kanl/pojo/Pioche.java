package org.ib.kanl.pojo;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Pioche {
    // variables de classe
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
    @GenericGenerator(name = "native", strategy = "native")
    private Integer idPioche;
    @OneToMany
    private List<Carte> listCarte;

    @OneToOne
    @JoinColumn(name = "partie_id", referencedColumnName = "idPartie")
    private Partie partie;

    // constructeur de la pioche
    public Pioche() {
        this.listCarte = new ArrayList();
    }
    public Pioche(Partie partie) {
        this.partie = partie;
        this.listCarte = new ArrayList();
    }

    public Integer getId( ) {
        return idPioche;
    }
    public void setId( Integer id ) {
        this.idPioche = idPioche;
    }

    public List< Carte > getListCarte( ) {
        return listCarte;
    }

    public void setListCarte( List < Carte > listCarte ) {
        this.listCarte = listCarte;
    }
    /*public String piocheToString() {
        String str = this.pioche.get( 0 ).getValeur( ).toString();
        for(int i = 1; i < this.pioche.size(); i++)
        {
            str += "," + this.pioche.get(i).getValeur().toString();
        }
        return str;
    }
    public void piocheToArray(String s) {
        this.pioche.clear();
        String[] tab = s.split(",");

        for(int i = 0; i < tab.length; i++)
        {
            this.pioche.add(new Carte(Integer.parseInt(tab[i])));
        }
    }
}*/
}


