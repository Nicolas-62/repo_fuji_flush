package services;

import models.Hand;
import models.User;

public class HandService {

    public static Hand getByPlayer(User player) {
        return Hand.find("player = ?1", player).first();
    }

    public static Hand getById(Long id) {
        return Hand.findById(id);
    }
}
