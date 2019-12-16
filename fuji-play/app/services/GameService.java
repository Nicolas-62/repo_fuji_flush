package services;

import java.util.Collections;
import java.util.List;

import models.Card;
import models.Game;
import models.Hand;
import models.User;


public class GameService {
	/**
	 * Crée un nouveau jeu, son deck et ses mains
	 */
	public static void addGame(Game game) {
		// création du deck
    	game.deck = CardService.findAll();
        Collections.shuffle(game.deck);
        game.save();

	}
	/**
	 * Récupère toute les parties
	 * @return Une liste de parties
	 */
	public static  List<Game>getAll(){
		   return Game.findAll();
	}
    /**
	 * Un joueur veut rejoindre une partie, on lui crée une main,
	 * si tout les joueurs sont présents on rempli les mains
	 * @param game : jeu
	 * @param player : joueur
	 */
	public static void joinGame(Game game, User player) {      
        Hand hand = new Hand();
        hand.player = player;
        hand.game = game;
        game.hands.add(hand);
        game.nbPlayerMissing--;
        if(game.nbPlayerMissing == 0) {
            for (int i = 0; i < 6; i++) {
                for (Hand aHand : game.hands) {
                    GameService.draw(game.deck, aHand);
                }
            }     	
        }
        game.save();
	}
	
	/**
	 * Met à jour le currentPlayer de la partie en cours,
	 * on récupère la main du prochain joueur, on l'ignore si ce joueur a abandonné
	 * @param game : partie en cours
	 * @param hand : main du joueur qui vient de jouer
	 */
    public static void nextPlayer(Game game, Hand hand) {
    	int indexOfHand = game.hands.indexOf(hand);
        int indexOfNextHand;
        if (indexOfHand == game.hands.size()-1) {
            indexOfNextHand=0;
        }else {
            indexOfNextHand = indexOfHand+1;
        }
        Hand nextHand = game.hands.get(indexOfNextHand);
        if(nextHand.hasLeft) {
            if(indexOfNextHand == game.hands.size()-1) {
                nextHand = game.hands.get(0);
            }else {
                nextHand = game.hands.get(indexOfNextHand+1);
            }
        }
        game.currentPlayer = nextHand.player;
        game.save();
    }
    /**
     * Déplace une carte de la main vers cardP ( cardP = card Player = carte jouée)
     * @param hand : main du joueur
     * @param card : carte jouée
     */
    public static void playCard(Hand hand, Card card, Game game) {
    	GameService.getById(game.id);
        hand.cardP = card;
        hand.cards.remove(card);
        hand.save();
    }

    /**
     * Vérifie si le prochain joueur a une carte jouée, si oui il peut
     * la défausser sans repiocher, les joueurs ayant la même carte également.
     * Si c'est sa dernière carte il a gagné la partie
     * @param game : partie en cours
     */
    public static void ruleFullTurn(Game game) {
        Hand handNextPlayer = HandService.getByPlayerAndGame(game.currentPlayer, game);
        if (handNextPlayer.cardP != null) {

            int cardValue = handNextPlayer.cardP.value;
            for (Hand hand : game.hands) {
                if (hand.cardP != null && hand.cardP.value.equals(cardValue)) {
                    discard(game, hand);
                }
                if (hand.cards.isEmpty() && hand.cardP == null && !hand.hasLeft) {
                    win(hand);
                    break;
                }
            }
        }
    }
    /**
     * Sauvegarde la partie avec son vainqueur.
     * @param hand : la main du joueur qui a gagné
     */
    public static void win(Hand hand) {
        Game game = hand.game;
        game.currentPlayer = null;
        game.winner = hand.player;
        game.winner.score+=3;
        game.winner.save();
        game.save();
    }
    /**
     * Un joueur quitte une partie
     * @param game : partie en cours
     * @param player : joueur qui veut quitter la partie
     */
    public static void leave(Game game,User player){
        Hand hand= HandService.getByPlayerAndGame(player,game);
        hand.cards.clear();
        hand.hasLeft =true;
        hand.save();
        if(game.currentPlayer.equals(player)) {
        	GameService.nextPlayer(game, hand);
        }

    }


    /**
     * Compare les valeurs des cartes jouées, prend en compte les associations,
     * défausse les cartes les plus faibles, ajoute des cartes aux mains défaussées
     * @param game : le jeu en cours
     * @param currentHand : la main du joueur qui joue
     */ 
    public static void ruleCompareAndDiscard(Game game, Hand currentHand) {
        int[] asso = new int[21];

        // Stocker le nombre de fois que chaque valeur est joué
        for (Hand hand : game.hands) {
            if (hand.cardP != null) {
                asso[hand.cardP.value] = asso[hand.cardP.value] + 1;
            }
        }
        // Compare les valeurs des associations
        for (Hand hand : game.hands) {
        	
            if (hand.cardP != null && calculAsso(asso, currentHand) > calculAsso(asso, hand)) {
                discard(game, hand);
                if(!hand.hasLeft) {
                	draw(game.deck, hand);
                }
            }
        }
    }

    /**
     * Calcule la somme des points des cartes identiques jouées
     * @param asso : stocke pour chaque val de carte du jeu le
     * nombre de fois ou elle est présente (jouée) sur le tapis
     * @param hand : une main d'un jeu
     * @return somme des points des cartes identiques
     */
    private static int calculAsso(int[] asso, Hand hand) {
        return hand.cardP.value * asso[hand.cardP.value];
    }
    /**
     * Récupère la partie définie en bdd (voir initial-data.yml)
     * @return partie jouée
     */
    public static Game getOne() {
        return Game.find("").first();
    }
    /**
     * Ajoute une carte dans une main à partir du deck, sauvegarde la main
     * @param deck
     * @param hand
     */
    public static void draw(List<Card> deck, Hand hand) {
        hand.cards.add(deck.get(0));
        deck.remove(0);
        hand.save();
    }
    /**
     * Déplace la carte jouée d'une main donnée dans la défausse
     * @param game : partie en cours
     * @param hand : main d'un joueur
     */
    public static void discard(Game game, Hand hand) {
        game.discard.add(hand.cardP);
        hand.cardP = null;
        game.save();
        hand.save();
    }
	public static Game getById(Long gameId) {
		return Game.findById(gameId);
	}
}
