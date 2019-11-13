package org.ib.kanl.pojo;
import java.util.ArrayList;
import java.util.List;

//@Entity
//@Table(name="Pioche")
public class Pioche {
    // variables de classe
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO, generator = "native")
//    @GenericGenerator(name = "native", strategy = "native")
//    @Column( name = "id" )
    private Integer id;
//    @Column( name = "valeurPioche" )
    private List<Carte> listCarte;

    // constructeur de la pioche
    public Pioche( ) {
        listCarte = new ArrayList( );
    }

    public Integer getId( ) {
        return id;
    }
    public void setId( Integer id ) {
        this.id = id;
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


