package models;

import javax.persistence.*;

@Entity
public class Hand extends IdModel{
	
	@OneToOne
	public Game game;
	@OneToOne
	public User player;
	
	public Integer card1;
	public Integer card2;
	public Integer card3;
	public Integer card4;
	public Integer card5;
	public Integer card6;
	public Integer cardP;
	

}
