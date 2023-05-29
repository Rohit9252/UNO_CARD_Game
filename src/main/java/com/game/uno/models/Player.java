package com.game.uno.models;

import java.util.ArrayList;
import java.util.List;


// Player class representing a player in the game
public class Player {
    private int id;
    private String name;
    private List<Card> hand;


    /**
     * Constructs a new player with the specified ID and name.
     * The player's hand is initially empty.
     *
     * @param id   The ID of the player.
     * @param name The name of the player.
     */
    public Player(int id, String name) {
        this.id = id;
        this.name = name;
        this.hand = new ArrayList<>();
    }

    /**
     * Returns the ID of the player.
     *
     * @return The ID of the player.
     */
    public int getId() {
        return id;
    }
    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName() {
        return name;
    }
    /**
     * Returns the hand of cards held by the player.
     *
     * @return The list of cards in the player's hand.
     */
    public List<Card> getHand() {
        return hand;
    }
    /**
     * Receives the initial set of cards and adds them to the player's hand.
     *
     * @param cards The list of cards to be added to the player's hand.
     */
    public void receiveInitialCards(List<Card> cards) {
        hand.addAll(cards);
    }

    /**
     * Draws a card from the specified deck and adds it to the player's hand.
     *
     * @param deck The deck from which the card is drawn.
     * @return The card drawn from the deck.
     */
    public Card drawCard(Deck deck) {
        Card card = deck.drawCard();
        hand.add(card);
        return card;
    }

    /**
     * Plays the specified card in the game.
     * If the card is valid to play, it is removed from the player's hand,
     * its effect is applied to the game, and it becomes the last played card.
     *
     * @param card The card to be played.
     * @param game The game in which the card is played.
     */
    public void playCard(Card card, Game game) {
        if (hand.contains(card)) {
            if (card.isValidPlay(game)) {
                hand.remove(card);
                card.applyEffect(game);
                game.setLastPlayedCard(card);
            } else {
                System.out.println("Invalid card play!");
            }
        } else {
            System.out.println("Card not in hand!");
        }
    }

    /**
     * Checks if the player has any valid card to play based on the current card in play.
     *
     * @param currentCard The card currently in play.
     * @return true if the player has a valid card to play, false otherwise.
     */
    public boolean hasValidCardToPlay(Card currentCard) {
        for (Card card : hand) {
            if (card.isValidPlay(currentCard)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the size of the player's hand.
     *
     * @return The number of cards in the player's hand.
     */
    public int getHandSize() {
        return hand.size();
    }
}