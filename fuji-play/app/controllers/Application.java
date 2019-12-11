package controllers;

import models.Game;
import models.Hand;
import models.User;
import play.mvc.Controller;
import play.mvc.With;
import services.GameService;
import services.HandService;
import services.UserService;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;


@With(Secure.class)
public class Application extends Controller {

    public static void saloon() {
        render();
    }

    public static void ranking()
    {
        User user = Security.connectedUser();

        List<User> listRanking = UserService.getTopRank();

        Long nbUser = UserService.getNbUser();

        UserService.calculateRank();

        render(listRanking, nbUser);
    }


    public static void play() {
        User player = Security.connectedUser();
        Hand handPlayer = HandService.getByPlayer(player);
        Game game = handPlayer.game;
        render(game, player, handPlayer);
    }

    public static void playCard(Long id, Integer index) {
        Hand hand = HandService.getById(id);
        Game game = hand.game;

        User player = Security.connectedUser();
        if (player.equals(game.currentPlayer)) {

            GameService.playCard(hand, hand.cards.get(index));

            Hand currentHandPlayer = HandService.getByPlayer(game.currentPlayer);

            GameService.ruleCompareAndDiscard(game, currentHandPlayer);

            GameService.nextPlayer(game, hand);

            GameService.ruleFullTurn(game);

            play();
        } else {
            forbidden();
        }
    }
}
