package com.game.uno.models;

import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Deck class representing the deck of cards
public class Deck {
    private List<Card> cards;

    /**
     * Constructs a new deck of cards.
     * The deck is initialized with the standard set of UNO cards and shuffled.
     */
    public Deck() {
        this.cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }
    /**
     * Initializes the deck with the standard set of UNO cards.
     * The deck is populated with numbered cards, special action cards, and wild cards.
     */
    private void initializeDeck() {
        // Add all the numbered cards to the deck
        for (Color color : Color.values()) {
            if (color != Color.WILD) {
                for (Value value : Value.values()) {
                    if (value != Value.WILD && value != Value.WILD_DRAW_FOUR) {
                        cards.add(new Card(color, value));
                        cards.add(new Card(color, value));
                    }
                }
            }
        }

        // Add special action cards to the deck
        for (Color color : Color.values()) {
            if (color != Color.WILD) {
                cards.add(new Card(color, Value.SKIP));
                cards.add(new Card(color, Value.REVERSE));
                cards.add(new Card(color, Value.DRAW_TWO));
            }
        }

        // Add wild cards to the deck
        cards.add(new Card(Color.WILD, Value.WILD));
        cards.add(new Card(Color.WILD, Value.WILD));
    }

    /**
     * Shuffles the deck of cards.
     * Randomizes the order of cards in the deck.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     * If the deck is empty, it will be replenished with a new set of cards and shuffled.
     *
     * @return The card drawn from the deck.
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            replenish();
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Replenishes the deck with a new set of cards and shuffles it.
     * This method is called when the deck runs out of cards during gameplay.
     */
    private void replenish() {
        initializeDeck();
        shuffle();
    }
}
