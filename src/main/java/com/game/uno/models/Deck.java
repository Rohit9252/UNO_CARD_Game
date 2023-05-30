package com.game.uno.models;

import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Represents a deck of Uno cards.
 */
public class Deck {
    private List<Card> cards;

    /**
     * Constructs a new deck of cards.
     * The deck is initialized with all the standard Uno cards and shuffled.
     */
    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

    /**
     * Initializes the deck with all the standard Uno cards.
     * The deck does not include wild cards (Wild, Wild Draw Four).
     */
    private void initializeDeck() {
        for (Color color : Color.values()) {
            if (color != Color.WILD) {
                for (Value value : Value.values()) {
                    if (value != Value.WILD && value != Value.WILD_DRAW_FOUR) {
                        if (value == Value.ZERO) {
                            cards.add(new Card(color, value));
                        } else {
                            cards.add(new Card(color, value));
                            cards.add(new Card(color, value));
                        }
                    }
                }
            }
        }
        // Add special action cards
        for (int i = 0; i < 2; i++) {
            cards.add(new Card(Color.WILD, Value.WILD));
            cards.add(new Card(Color.WILD, Value.WILD_DRAW_FOUR));
        }
    }

    /**
     * Shuffles the cards in the deck randomly.
     */
    public void shuffle() {
        Collections.shuffle(cards);
    }

    /**
     * Draws a card from the deck.
     *
     * @return the top card from the deck, or null if the deck is empty
     */
    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    /**
     * Replenishes the deck by adding the used cards back to the deck and shuffling the deck.
     *
     * @param usedCards the list of used cards to be added back to the deck
     */
    public void replenishDeck(List<Card> usedCards) {
        cards.addAll(usedCards);
        shuffle();
    }
}

