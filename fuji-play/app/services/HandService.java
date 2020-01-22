package services;

import java.util.List;

import models.Game;
import models.Hand;
import models.User;

public class HandService {

//    public static Hand getByPlayerAndGame(User player, Game game) {
//        return Hand.find("player = ?1 and game = ?2", player,game).first();
//    }

    public static Hand getById(Long id) {
        return Hand.findById(id);
    }

    public static Hand getByPlayerAndGame(User player, Game game) {
        return Hand.find("player = ?1 AND game =?2", player, game).first();
    }
    public static  List<Hand> FindAllByGame(Game game){
    	return Hand.find("game = ?1", game).fetch();
    }

	public static Hand findByUUID(String uuid) {
		// TODO Auto-generated method stub
		return Hand.find("uuid= ?1", uuid).first();
	}
}
