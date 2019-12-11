package services;

import java.util.List;

import models.Card;

public class CardService {
	
	public static List<Card> findAll(){
		return Card.findAll();
	}

}
