package models;


import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

@Entity
public class Game extends IdModel {

	public User author;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date dateDebut;

	
    @OneToMany(mappedBy = "game")
    public List<Hand> hands;

    @OneToOne
    public User currentPlayer;

    @ManyToMany
    @JoinTable(name = "Game_Deck")
    public List<Card> deck;

    @ManyToMany
    @JoinTable(name = "Game_Discard")
    public List<Card> discard;

    @OneToOne
    public User winner;
}
