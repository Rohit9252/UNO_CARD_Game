package com.game.uno.models;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a player in the Uno game.
 */
public class Player {
    private String name;
    private List<Card> hand;

    /**
     * Constructs a new player with the given name.
     *
     * @param name the name of the player
     */
    public Player(String name) {
        this.name = name;
        hand = new ArrayList<>();
    }

    /**
     * Receives the initial set of cards and adds them to the player's hand.
     *
     * @param cards the list of cards to be added to the player's hand
     */
    public void receiveInitialCards(List<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Draws a card from the specified deck and adds it to the player's hand.
     *
     * @param deck the deck from which the player draws a card
     */
    public void drawCard(Deck deck) {
        Card card = deck.drawCard();
        System.out.println("Drew card is: " + card);
        if (card != null) {
            hand.add(card);
        }
    }

    /**
     * Checks if the player has a valid card to play based on the current card on the table.
     *
     * @param currentCard the current card on the table
     * @return true if the player has a valid card to play, false otherwise
     */
    public boolean hasValidCardToPlay(Card currentCard) {
        for (Card card : hand) {
            if (card.getColor() == currentCard.getColor() || card.getValue() == currentCard.getValue()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Plays the specified card from the player's hand and performs the associated action in the game.
     *
     * @param card the card to be played
     * @param game the game instance in which the card is being played
     */
    public void playCard(Card card, Game game) {
        if (hand.remove(card)) {
            game.setCurrentColor(card.getColor());
            game.setLastPlayedCard(card);
            card.performAction(game);
        }
    }

    /**
     * Retrieves the player's hand.
     *
     * @return the list of cards in the player's hand
     */
    public List<Card> getHand() {
        return hand;
    }

    /**
     * Retrieves the size of the player's hand.
     *
     * @return the number of cards in the player's hand
     */
    public int getHandSize() {
        return hand.size();
    }

    /**
     * Retrieves the name of the player.
     *
     * @return the name of the player
     */
    public String getName() {
        return name;
    }
}
