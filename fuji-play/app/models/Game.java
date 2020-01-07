package models;


import javax.persistence.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends UUIDModel {

    public Game(){
		this.hands = new ArrayList<Hand>();
	}

	@OneToOne
	public User author;
	
	public Integer nbPlayerMissing;

    @OneToMany(mappedBy = "game", cascade = CascadeType.ALL)
    public List<Hand> hands;

    @OneToOne
    public User currentPlayer;

    @ManyToMany
    @JoinTable(name = "Game_Deck")
    public List<Card> deck;

    @ManyToMany
    @JoinTable(name = "Game_Discard")
    public List<Card> discard;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Hand> winners;

    public Boolean isFinished = false;
}
