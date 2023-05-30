package com.game.uno.models;

import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
// Deck class representing the deck of cards
public class Deck {
    private List<Card> cards;

    public Deck() {
        cards = new ArrayList<>();
        initializeDeck();
        shuffle();
    }

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

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card drawCard() {
        if (cards.isEmpty()) {
            return null;
        }
        return cards.remove(cards.size() - 1);
    }

    public void replenishDeck(List<Card> usedCards) {
        cards.addAll(usedCards);
        shuffle();
    }
}
