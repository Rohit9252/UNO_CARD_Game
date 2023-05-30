package com.game.uno;

import com.game.uno.models.Game;
import com.game.uno.models.Player;

import java.util.ArrayList;
import java.util.List;

public class Main {

    /**
     * The entry point of the UNO game application.
     */
    public static void main(String[] args) {
        Player player1 = new Player( "Player 1");
        Player player2 = new Player( "Player 2");


        // Create a list of players
        List<Player> players = List.of(player1, player2);



        // Create a new game with the list of players
        Game game = new Game(players);

        // Start the game
        game.start();
    }
}