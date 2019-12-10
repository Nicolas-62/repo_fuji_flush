package models;


import javax.persistence.*;
import java.util.List;

@Entity
public class Game extends IdModel {

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
