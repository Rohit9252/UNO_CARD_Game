package com.game.uno.models;


import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

/**
 * Represents a card in the Uno game.
 */
public class Card {
    private Color color;
    private Value value;

    /**
     * Constructs a Card object with the given color and value.
     *
     * @param color the color of the card
     * @param value the value of the card
     */
    public Card(Color color, Value value) {
        this.color = color;
        this.value = value;
    }

    /**
     * Returns the color of the card.
     *
     * @return the color of the card
     */
    public Color getColor() {
        return color;
    }

    /**
     * Returns the value of the card.
     *
     * @return the value of the card
     */
    public Value getValue() {
        return value;
    }

    /**
     * Checks if the card is a special action card (Skip, Reverse, Draw Two).
     *
     * @return true if the card is a special action card, false otherwise
     */
    public boolean isSpecialActionCard() {
        return value == Value.SKIP || value == Value.REVERSE || value == Value.DRAW_TWO;
    }

    /**
     * Checks if the card is a wild card (Wild, Wild Draw Four).
     *
     * @return true if the card is a wild card, false otherwise
     */
    public boolean isWildCard() {
        return value == Value.WILD || value == Value.WILD_DRAW_FOUR;
    }

    /**
     * Performs the special action associated with the card in the given game.
     *
     * @param game the game in which the action is performed
     */
    public void performAction(Game game) {
        switch (value) {
            case SKIP:
                game.skipNextPlayer();
                break;
            case REVERSE:
                game.reverseDirection();
                break;
            case DRAW_TWO:
                game.drawCards(2);
                game.skipNextPlayer();
                break;
            case WILD:
                Color chosenColor = game.promptColorChoice();
                game.setCurrentColor(chosenColor);
                break;
            case WILD_DRAW_FOUR:
                Color chosenColor2 = game.promptColorChoice();
                game.setCurrentColor(chosenColor2);
                game.drawCards(4);
                game.skipNextPlayer();
                break;
        }
    }

    /**
     * Returns a string representation of the card, including its color and value.
     *
     * @return a string representation of the card
     */
    @Override
    public String toString() {
        return color + " " + value;
    }
}
