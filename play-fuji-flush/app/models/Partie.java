package models;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;

@Entity
public class Partie extends IdModel{
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateDebut;
	
	@Temporal(TemporalType.TIMESTAMP)
    private Date dateFin;
	
    @OneToMany
    private List<Joueur> joueurs;
    // cascade laissé mais non fonctionnel
    @OneToOne(mappedBy = "partie", cascade = CascadeType.ALL)
    private Pioche pioche;
    
    @Transient // annotation pour que JPA ignore cet attribut en bdd
    private Carte[] tapis;
    @Transient
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        this.dateDebut = new Date(System.currentTimeMillis());
        this.joueurs = new ArrayList();
    }
}