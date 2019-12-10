package services;

import models.Card;
import models.Game;
import models.Hand;

import java.util.List;

public class GameService {

    public static void nextPlayer(Game game, Hand hand) {
        int indexOfHand = game.hands.indexOf(hand);
        indexOfHand++;
        if (indexOfHand >= game.hands.size()) {
            indexOfHand = 0;
        }
        game.currentPlayer = game.hands.get(indexOfHand).player;
        game.save();
    }

    public static void playCard(Hand hand, Card card) {
        hand.cardP = card;
        hand.cards.remove(card);
        hand.save();
    }

    public static void ruleFullTurn(Game game) {
        Hand handNextPlayer = HandService.getByPlayer(game.currentPlayer);
        if (handNextPlayer.cardP != null) {
            int cardValue = handNextPlayer.cardP.value;
            for (Hand hand : game.hands) {
                if (hand.cardP != null && hand.cardP.value.equals(cardValue)) {
                    discard(game, hand);
                }
                if (hand.cards.isEmpty() && hand.cardP == null) {
                    win(hand);
                    break;
                }
            }
        }
    }

    public static void win(Hand hand) {
        Game game = hand.game;
        game.currentPlayer = null;
        game.winner = hand.player;
        game.save();
    }

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
                draw(game.deck, hand);
            }
        }
    }

    private static int calculAsso(int[] asso, Hand hand) {
        return hand.cardP.value * asso[hand.cardP.value];
    }

    public static Game getOne() {
        return Game.find("").first();
    }

    public static void draw(List<Card> deck, Hand hand) {
        hand.cards.add(deck.get(0));
        deck.remove(0);
        hand.save();
    }

    public static void discard(Game game, Hand hand) {
        game.discard.add(hand.cardP);
        hand.cardP = null;
        game.save();
        hand.save();
    }

}
