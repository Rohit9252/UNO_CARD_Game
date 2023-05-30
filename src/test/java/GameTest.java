import com.game.uno.enums.Color;
import com.game.uno.enums.Value;
import com.game.uno.models.Card;
import com.game.uno.models.Game;
import com.game.uno.models.Player;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GameTest {
    private List<Player> players;
    private Game game;

    @BeforeEach
    void setup() {
        // Create a new game with two players before each test
        players = new ArrayList<>();
        players.add(new Player("Alice"));
        players.add(new Player("Bob"));
        game = new Game(players);
    }



    @Test
    void testSkipNextPlayer() {
        // Skip the next player
        game.skipNextPlayer();

        // Assert that the current player index has been updated
        assertEquals(1, game.getCurrentPlayerIndex());

        // Assert that the next player has been skipped
        assertEquals("Bob", players.get(game.getCurrentPlayerIndex()).getName());
    }



    @Test
    void testDrawCards() {
        // Draw 2 cards for the next player
        game.drawCards(2);

        // Assert that the next player has received the cards
        assertEquals(2, players.get((game.getCurrentPlayerIndex() + 1) % players.size()).getHandSize());
    }

    @Test
    void testPromptColorChoice() {
        // Prompt for color choice
        Color chosenColor = game.promptColorChoice();

        // Assert that the chosen color is not null
        assertNotNull(chosenColor);
    }



}
