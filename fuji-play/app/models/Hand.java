package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Hand extends IdModel{
	
	
	public Hand(){
		this.cards = new ArrayList<Card>();
	}

    @OneToOne
    public Game game;

    @OneToOne(optional = true)
    public User player;

    /**
     * ManyToMany et pas OneToMany sinon contrainte d'unicité sur les carte
     */
    @ManyToMany(cascade = CascadeType.ALL)
    public List<Card> cards;

    @OneToOne
    public Card cardP;

    public boolean abandon=false;


}
