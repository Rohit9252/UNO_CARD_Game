# Uno Game

The Uno Game is a simple implementation of the popular card game Uno. It allows two players to play against each other and follow the basic rules of the game.

## Features

- Distribution of initial cards to players
- Choosing a starting card
- Playing valid cards from the player's hand
- Drawing cards when no valid cards are available
- Performing special actions associated with certain cards (skip, reverse, draw two, wild, wild draw four)
- Tracking the current color of the game
- Reversing the order of play
- Ending the game when a player has no more cards

## Requirements

To run the Uno Game, you need the following:

- Java Development Kit (JDK) version 8 or later
- Command line or an IDE (Integrated Development Environment) to compile and run Java code

## How to Run

1. Clone or download the repository to your local machine.
2. Open a command line or an IDE and navigate to the project directory.
3. Compile the Java source files using the following command:


4. Run the compiled program using the following command:
    ```bash
    javac -d bin src/*.java
    ```



5. Follow the instructions displayed in the console to play the game.

## Customization

You can customize the game by making changes to the code. Here are some possible modifications:

- Implementing a different card game using the provided `Card` class as a starting point
- Modifying the number of players by adding or removing `Player` objects in the `main` method of `Main.java`
- Adding additional rules or special action cards to the game


## Acknowledgments

- The Uno card game rules and concepts were created by Merle Robbins and have been popularized by Mattel.
- The code in this project is a simplified implementation of the Uno game rules and does not include all possible variations or edge cases.
