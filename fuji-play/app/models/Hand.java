package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;

@Entity
public class Hand extends UUIDModel{


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
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    public List<Card> cards;

    @OneToOne
    public Card cardP;

    public boolean hasLeft =false;

    public boolean hasWon =false;

    public boolean hasLeaveWon=false;
}
