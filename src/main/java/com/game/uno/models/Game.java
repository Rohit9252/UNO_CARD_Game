package com.game.uno.models;

import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.List;
import java.util.Scanner;

/**
 * Represents the Uno game.
 */
public class Game {
    private List<Player> players;
    private Deck deck;
    private Card lastPlayedCard;
    private Color currentColor;
    private boolean reverseDirection;
    private int currentPlayerIndex;

    /**
     * Constructs a new game with the given list of players.
     *
     * @param players the list of players participating in the game
     */
    public Game(List<Player> players) {
        this.players = players;
        deck = new Deck();
    }

    /**
     * Starts the game by distributing initial cards to players, determining the first player, and beginning the game loop.
     */
    public void start() {
        // Distribute initial cards to players
        for (Player player : players) {
            List<Card> initialCards = new ArrayList<>();
            for (int i = 0; i < 7; i++) {
                initialCards.add(deck.drawCard());
            }
            player.receiveInitialCards(initialCards);
        }

        // Determine the first player
        currentPlayerIndex = 0;
        Player firstPlayer = players.get(currentPlayerIndex);

        // Choose a starting card
        lastPlayedCard = deck.drawCard();
        while (lastPlayedCard.getValue() == Value.WILD || lastPlayedCard.getValue() == Value.WILD_DRAW_FOUR) {
            // If the starting card is a special action card, draw another one
            lastPlayedCard = deck.drawCard();
        }

        System.out.println("Game starts!");
        System.out.println("First player: " + firstPlayer.getName());
        System.out.println("Starting card: " + lastPlayedCard);

        // Start the game loop
        boolean gameEnded = false;
        while (!gameEnded) {
            Player currentPlayer = players.get(currentPlayerIndex);

            System.out.println("\n" + currentPlayer.getName() + "'s turn");
            System.out.println("Current card: " + lastPlayedCard);
            System.out.println("Your hand: " + currentPlayer.getHand());

            if (!currentPlayer.hasValidCardToPlay(lastPlayedCard)) {
                System.out.println("No valid cards to play. Drawing a card...");
                currentPlayer.drawCard(deck);
                if (currentPlayer.hasValidCardToPlay(lastPlayedCard)) {
                    System.out.println("You drew a card that can be played.");
                } else {
                    System.out.println("You drew a card that cannot be played. Turn ends.");
                }
                nextPlayer();
            } else {
                Card selectedCard = promptCardChoice(currentPlayer);
                if (selectedCard == null) {
                    System.out.println("Drawing a card...");
                    currentPlayer.drawCard(deck);

                    if (currentPlayer.hasValidCardToPlay(lastPlayedCard)) {
                        System.out.println("You drew a card that can be played.");
                    } else {
                        System.out.println("You drew a card that cannot be played. Turn ends.");
                    }
                    nextPlayer();
                } else {
                    currentPlayer.playCard(selectedCard, this);
                    nextPlayer();
                }
            }

            if (currentPlayer.getHandSize() == 0) {
                gameEnded = true;
                System.out.println("\n" + currentPlayer.getName() + " wins!");
            }
        }
    }

    /**
     * Moves the game to the next player based on the current direction.
     */
    public void nextPlayer() {
        int numPlayers = players.size();
        if (reverseDirection) {
            currentPlayerIndex = (currentPlayerIndex - 1 + numPlayers) % numPlayers;
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % numPlayers;
        }
    }

    /**
     * Prompts the current player to choose a card to play from their hand or choose to draw a card.
     *
     * @param currentPlayer the current player making the choice
     * @return the selected card to play, or null if the player chooses to draw a card
     */
    private Card promptCardChoice(Player currentPlayer) {
        Scanner scanner = new Scanner(System.in);
        List<Card> hand = currentPlayer.getHand();

        System.out.println("Select a card to play (enter the index):");
        for (int i = 0; i < hand.size(); i++) {
            System.out.println(i + ": " + hand.get(i));
        }
        System.out.println("Choose a card to play (enter card index or 'draw' to draw a card): ");
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("draw")) {
            return null;
        }

        try {
            int cardIndex = Integer.parseInt(input);
            if (cardIndex >= 0 && cardIndex < currentPlayer.getHandSize()) {
                return currentPlayer.getHand().get(cardIndex);
            } else {
                System.out.println("Invalid card index!");
            }
        } catch (NumberFormatException e) {
            System.out.println("Invalid input!");
        }

        return null;
    }

    /**
     * Skips the next player's turn.
     */
    public void skipNextPlayer() {
        nextPlayer();
        System.out.println("Next player skipped.");
    }

    /**
     * Reverses the direction of play.
     */
    public void reverseDirection() {
//        reverseDirection = !reverseDirection;
//        System.out.println("Direction reversed: " + reverseDirection);
        reverseDirection = !reverseDirection;
        System.out.println("Direction reversed: " + reverseDirection);

        // Update the current player index based on the new direction
        reverseDirection = !reverseDirection;
        if (reverseDirection) {
            currentPlayerIndex = (currentPlayerIndex - 1 + players.size()) % players.size();
        } else {
            currentPlayerIndex = (currentPlayerIndex + 1) % players.size();
        }
        System.out.println("Direction reversed: " + reverseDirection);
    }

    /**
     * Makes the next player draw a specified number of cards from the deck.
     *
     * @param numCards the number of cards to be drawn
     */
    public void drawCards(int numCards) {
        Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
        System.out.println("Next player draws " + numCards + " card(s).");
        for (int i = 0; i < numCards; i++) {
            nextPlayer.drawCard(deck);
        }
    }

    /**
     * Prompts the current player to choose a color.
     *
     * @return the chosen color
     */
    public Color promptColorChoice() {
        Color[] colors = {Color.RED, Color.BLUE, Color.GREEN, Color.YELLOW};
        int randomIndex = (int) (Math.random() * colors.length);
        return colors[randomIndex];
    }

    /**
     * Sets the current color of the game.
     *
     * @param color the color to be set
     */
    public void setCurrentColor(Color color) {
        currentColor = color;
    }

    /**
     * Sets the last played card in the game.
     *
     * @param card the last played card
     */
    public void setLastPlayedCard(Card card) {
        lastPlayedCard = card;
    }

    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }

    public int getCurrentPlayerIndex() {
        return currentPlayerIndex;
    }

    public boolean isReverseDirection() {
        return reverseDirection;
    }

    public Player getCurrentPlayer() {
        return players.get(currentPlayerIndex);
    }




}


