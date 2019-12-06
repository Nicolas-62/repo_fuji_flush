package models;

import java.util.List;
import javax.persistence.*;
import javax.persistence.Entity;

@Entity
public class Game extends IdModel{
	
	@OneToMany(mappedBy = "game")
	List<Hand> hands;
	
	@OneToOne
	public User currentPlayer;
}
