package models;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

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
    
    private Carte[] tapis;
    private int nbJoueur;

    // Constructeurs
    public Partie() {
        DateFormat format = new SimpleDateFormat("dd-MM-YYYY HH:mm:ss");
        this.dateDebut = new Date(System.currentTimeMillis());
        this.joueurs = new ArrayList();
    }
}