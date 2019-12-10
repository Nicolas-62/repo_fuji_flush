package models;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.List;

@Entity
public class Hand extends IdModel{

    @OneToOne
    public Game game;

    @OneToOne
    public User player;

    @OneToMany(cascade = CascadeType.ALL)
    public List<Card> cards;

    @OneToOne
    public Card cardP;


}
