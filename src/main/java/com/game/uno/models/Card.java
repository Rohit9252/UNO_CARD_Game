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


    public boolean isSpecialActionCard() {
        return value == Value.SKIP || value == Value.REVERSE || value == Value.DRAW_TWO;
    }

    public boolean isWildCard() {
        return value == Value.WILD || value == Value.WILD_DRAW_FOUR;
    }

    public void performAction(Game game) {
        // Perform special actions associated with the card
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
                // Allow the player to choose a color
                Color chosenColor = game.promptColorChoice();
                game.setCurrentColor(chosenColor);
                break;
            case WILD_DRAW_FOUR:
                // Allow the player to choose a color
                Color chosenColor2 = game.promptColorChoice();
                game.setCurrentColor(chosenColor2);
                game.drawCards(4);
                game.skipNextPlayer();
                break;
        }
    }

    @Override
    public String toString() {
        return color + " " + value;
    }
}
