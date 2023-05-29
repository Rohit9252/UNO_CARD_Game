package com.game.uno.models;


import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

//Card class representing a single card
public class Card {
    private Color color;
    private Value value;

 // This is a constructor for the Card class that takes in a Color and a Value as parameters and sets
 // the corresponding instance variables of the Card object to those values.
    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    public Color getColor() {
        return color;
    }

    public Value getValue() {
        return value;
    }

    /**
     * Checks if the card is a valid play based on the current card in play.
     *
     * @param currentCard the current card in play
     * @return true if the card is a valid play, false otherwise
     */
    public boolean isValidPlay(Card currentCard) {
        return color == currentCard.getColor() || value == currentCard.getValue();
    }

    /**
     * Checks if the card is a valid play based on the current game state.
     *
     * @param game the current game instance
     * @return true if the card is a valid play, false otherwise
     */
    public boolean isValidPlay(Game game) {
        Card currentCard = game.getLastPlayedCard();
        return color == currentCard.getColor() || value == currentCard.getValue();
    }


    /**
     * Applies any special actions associated with the card to the game.
     *
     * @param game the current game instance
     */
    public void applyEffect(Game game) {
        // Apply any special actions associated with the card
        if (value == Value.SKIP || value == Value.REVERSE || value == Value.DRAW_TWO) {
            game.skipNextPlayer();
        } else if (value == Value.WILD) {
            game.promptColorChoice();
        } else if (value == Value.WILD_DRAW_FOUR) {
            game.promptColorChoice();
            game.drawCards(4);
            game.skipNextPlayer();
        }
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
