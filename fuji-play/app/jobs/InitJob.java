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
	
	/**
	 * Création des cartes du jeu en bdd
	 */
    
    
    @Override
    public void doJob() throws Exception {
        Fixtures.deleteAllModels();
        Fixtures.loadModels("initial-data/initial-data.yml");
        
        //création du deck et des cartes qu'il contient puis mélange
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
        
       //création des mains des joueurs à partir du deck créé
       List<Hand> hands = Hand.findAll();
        for (int i = 0; i < 6; i++) {
            for (Hand hand : hands) {
                GameService.draw(deck, hand);
            }
        }
        // on recupère la partie ajoutée en bdd via script
        // on lui ajoute le deck et on sauvegarde le tout (game et game_deck)
        Game game = GameService.getOne();
        game.deck = deck;
        game.save();
    }

}
