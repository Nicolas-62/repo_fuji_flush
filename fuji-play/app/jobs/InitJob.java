package jobs;

import models.Card;
import models.Game;
import models.Hand;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import services.GameService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@OnApplicationStart
public class InitJob extends Job {

    @Override
    public void doJob() throws Exception {
        Fixtures.deleteAllModels();
        Fixtures.loadModels("initial-data/initial-data.yml");

        List<Card> deck = new ArrayList<>();
        int[] repartition = {16, 12, 9, 8, 6, 6, 5, 4, 4, 4, 3, 3, 3, 2, 1, 1, 1, 1, 1};
        for (int i = 2; i < 21; i++) {
            for (int j = 0; j < repartition[i - 2]; j++) {
                Card card = new Card();
                card.value = i;
                deck.add(card);
                card.save();
            }
        }
        Collections.shuffle(deck);

        List<Hand> hands = Hand.findAll();
        for (int i = 0; i < 6; i++) {
            for (Hand hand : hands) {
                GameService.draw(deck, hand);
            }
        }

        Game game = GameService.getOne();
        game.deck = deck;
        game.save();
    }

}