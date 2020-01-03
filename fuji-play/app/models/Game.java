package models;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Game extends IdModel {
	
	public Game(){
		this.hands = new ArrayList<Hand>();
	}

    @Column(length = 40)
    public String uuid;

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
