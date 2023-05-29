package com.game.uno.models;

import com.game.uno.enums.Color;
import com.game.uno.enums.Value;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
// Game class representing the game itself
public class Game {
    private List<Player> players;
    private Deck deck;
    private Card lastPlayedCard;
    private int currentPlayerIndex;
    private boolean reverseDirection;

    /**
     * Constructor for the Game class.
     * @param players The list of players in the game
     */

    public Game(List<Player> players) {
        this.players = players;
        this.deck = new Deck();
        this.lastPlayedCard = null;
        this.currentPlayerIndex = 0;
        this.reverseDirection = false;
    }

    /**
     * Starts the game by distributing initial cards to players, determining the first player, and initiating the game loop.
     * The game loop continues until a player wins.
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
                Card drawnCard = currentPlayer.drawCard(deck);
                System.out.println("You drew: " + drawnCard);
                if (drawnCard.isValidPlay(lastPlayedCard)) {
                    System.out.println("Playing the drawn card: " + drawnCard);
                    currentPlayer.playCard(drawnCard, this);
                }
            } else {
                boolean validCardPlayed = false;
                while (!validCardPlayed) {
                    Card selectedCard = promptCardChoice(currentPlayer);
                    if (selectedCard == null) {
                        System.out.println("Drawing a card...");
                        Card drawnCard = currentPlayer.drawCard(deck);
                        System.out.println("You drew: " + drawnCard);
                        if (drawnCard.isValidPlay(lastPlayedCard)) {
                            System.out.println("Playing the drawn card: " + drawnCard);
                            currentPlayer.playCard(drawnCard, this);
                            validCardPlayed = true;
                        }
                    } else {
                        currentPlayer.playCard(selectedCard, this);
                        validCardPlayed = true;
                    }
                }
            }

            if (currentPlayer.getHandSize() == 0) {
                gameEnded = true;
                System.out.println("\n" + currentPlayer.getName() + " wins!");
            }

            nextPlayer();
        }
    }


    /**
     * Prompts the current player to choose a card to play from their hand or choose to draw a card.
     *
     * @param currentPlayer the current player making the choice
     * @return the selected card to play, or null if the player chooses to draw a card
     */
    private Card promptCardChoice(Player currentPlayer) {
        System.out.println("Choose a card to play (enter card index or 'draw' to draw a card): ");
        Scanner scanner = new Scanner(System.in);
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
     * Skips the turn of the next player in the game, based on the current direction of play.
     * The player index is incremented or decremented based on the direction of play.
     */
    public void skipNextPlayer() {
        int increment = reverseDirection ? -1 : 1;
        currentPlayerIndex = (currentPlayerIndex + increment + players.size()) % players.size();
    }

    /**
     * Prompts the current player to choose a color for a wild card.
     * The method reads user input to determine the chosen color, validates it, and updates the last played card accordingly.
     * If the input is invalid, the method recursively prompts the player until a valid color choice is made.
     */
    public void promptColorChoice() {
        // Prompt the current player to choose a color for the wild card
        System.out.println("Choose a color (red, blue, green, yellow): ");
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();

        if (input.equalsIgnoreCase("red") || input.equalsIgnoreCase("blue") ||
                input.equalsIgnoreCase("green") || input.equalsIgnoreCase("yellow")) {
            lastPlayedCard = new Card(Color.valueOf(input.toUpperCase()), lastPlayedCard.getValue());
        } else {
            System.out.println("Invalid color choice!");
            promptColorChoice();
        }
    }


    /**
     * Advances the game to the next player's turn based on the current direction of play.
     * The player index is incremented or decremented based on the direction of play.
     */
    public void nextPlayer() {
        int increment = reverseDirection ? -1 : 1;
        currentPlayerIndex = (currentPlayerIndex + increment + players.size()) % players.size();
    }


    /**
     * Sets the last played card to the specified card.
     *
     * @param card the card to set as the last played card
     */
    public void setLastPlayedCard(Card card) {
        lastPlayedCard = card;
    }

    /**
     * Retrieves the last played card in the game.
     *
     * @return the last played card
     */
    public Card getLastPlayedCard() {
        return lastPlayedCard;
    }


    /**
     * Draws the specified number of cards from the deck for the next player in turn.
     * The cards are drawn from the deck and added to the next player's hand.
     *
     * @param numCards the number of cards to draw
     */
    public void drawCards(int numCards) {
        for (int i = 0; i < numCards; i++) {
            Player nextPlayer = players.get((currentPlayerIndex + 1) % players.size());
            nextPlayer.drawCard(deck);
        }
    }
}
