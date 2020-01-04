package services;

import models.Game;
import models.GameEvent;

import java.util.Date;
import java.util.List;

public class GameEventService {

            // Ajouter un game Event en base
    public static void addGameEvent(Game game, String message) {
        GameEvent gameEvent = new GameEvent();
        gameEvent.game = game;
        gameEvent.date = new Date();
        gameEvent.message = message;
        gameEvent.save();
        game.save();
    }

            // Récupérer tous les gameEvents d'une partie
    public static List<GameEvent> findAllByGame(Game game) {
            return GameEvent.find("game = ?1", game).fetch();
    }
}
