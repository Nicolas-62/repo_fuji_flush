package models;


import javax.persistence.*;

import com.google.gson.annotations.Expose;

import java.util.Date;
import java.util.List;

@Entity
public class Game extends IdModel {
	@OneToOne
	public User author;
	
	public int nbPlayerMissing;
	
	@Temporal(TemporalType.TIMESTAMP)
	public Date dateStart;

	
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
