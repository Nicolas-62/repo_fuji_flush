package jobs;

import models.Card;
import models.Game;
import models.Hand;
import play.jobs.Job;
import play.jobs.OnApplicationStart;
import play.test.Fixtures;
import services.GameService;
import services.HandService;
import services.UserService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@OnApplicationStart
public class InitJob extends Job {
	
	/**
	 * Création des deck et des mains de chaque partie déjà présente en bdd
	 */  
    @Override
    public void doJob() throws Exception {
    	
    	Fixtures.deleteAllModels();
    	Fixtures.loadModels("initial-data/initial-data.yml");
    	// cc
        List<Card> cards = new ArrayList<Card>();
        int[] repartition = {16, 12, 9, 8, 6, 6, 5, 4, 4, 4, 3, 3, 3, 2, 1, 1, 1, 1, 1};
        for (int i = 2; i < 21; i++) {
            for (int j = 0; j < repartition[i - 2]; j++) {
                Card card = new Card();
                card.value = i;
//                deck.add(card);
                card.save();
                cards.add(card);
            }
        }    	
        List<Game> games = GameService.findAll();
        for(Game game : games) {
            //création du deck et des cartes qu'il contient puis mélange
            List<Card> deck = new ArrayList<>();
//            List<Card> cards = CardService.findAll();
            for (Card aCard : cards) {
            	deck.add(aCard);
            }
            Collections.shuffle(deck); 
        	// on le fait pas pour la partie 2 et 4 cf test unitaire
        	if(game != games.get(1) && game != games.get(3)) {
        		//création des mains des joueurs à partir du deck créé
        		List<Hand> hands = HandService.getAllByGame(game);
        		for (int i = 0; i < 6; i++) {
        			for (Hand hand : hands) {
        				GameService.draw(deck, hand);
        			}
        		}     
        	}
             game.deck = deck;
             game.save();
        }
        Game lastGameFinished = games.get(2);
        lastGameFinished.winners.add(HandService.getByPlayerAndGame(UserService.getByEmail("bob@g.com"), games.get(2)));
        lastGameFinished.save();
    }

}

