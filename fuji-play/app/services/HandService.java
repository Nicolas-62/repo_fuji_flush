package services;

import models.Game;
import models.Hand;
import models.User;

public class HandService {

    public static Hand getByPlayer(User player) {
        return Hand.find("player = ?1", player).first();
    }

    public static Hand getById(Long id) {
        return Hand.findById(id);
    }

    public static Hand getByPlayerAndGame(User player, Game game) {
        return Hand.find("player = ?1 AND game =?2", player, game).first();
    }
}
