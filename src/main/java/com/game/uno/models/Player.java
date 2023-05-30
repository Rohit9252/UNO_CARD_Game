package com.game.uno.models;

import java.util.ArrayList;
import java.util.List;


// Player class representing a player in the game
public class Player {
    private String name;
    private List<Card> hand;

    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    public void receiveInitialCards(List<Card> cards) {
        hand.addAll(cards);
    }

    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        System.out.println("Drew  card is : " + card );
        if (card != null) {
            hand.add(card);
        }
    }

    public boolean hasValidCardToPlay(Card currentCard) {
        for (Card card : hand) {
            if (card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue()) {
                return true;
            }
        }
        return false;
    }

    public void playCard(Card card, Game game) {
        if (hand.remove(card)) {
            game.setCurrentColor(card.getColor());
            game.setLastPlayedCard(card);
            card.performAction(game);
        }
    }

    public List<Card> getHand() {
        return hand;
    }

    public int getHandSize() {
        return hand.size();
    }

    public String getName() {
        return name;
    }
}