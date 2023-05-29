
import com.game.uno.enums.Color;
import com.game.uno.enums.Value;
import com.game.uno.models.Card;
import com.game.uno.models.Game;
import com.game.uno.models.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UnoGameTest {

    @Test
    public void testValidCardPlay() {
        // Create players and initialize the game
        Player player1 = new Player(1, "Player 1");
        Player player2 = new Player(2, "Player 2");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players);
        game.start();

        // Set up the initial game state for testing
        player1.receiveInitialCards(Arrays.asList(
                new Card(Color.RED, Value.FOUR),
                new Card(Color.YELLOW, Value.TWO),
                new Card(Color.BLUE, Value.ONE)
        ));
        game.setLastPlayedCard(new Card(Color.RED, Value.TWO));

        // Play a valid card
//        player1.playCard(player1.getHand().get(0), game);

        // Verify the updated game state
        assertEquals(new Card(Color.RED, Value.FOUR), game.getLastPlayedCard());
//        assertEquals(2, player1.getHandSize());
    }

    @Test
    public void testInvalidCardPlay() {
        // Create players and initialize the game
        Player player1 = new Player(1, "Player 1");
        Player player2 = new Player(2, "Player 2");
        List<Player> players = new ArrayList<>();
        players.add(player1);
        players.add(player2);
        Game game = new Game(players);
        game.start();

        // Set up the initial game state for testing
        player1.receiveInitialCards(Arrays.asList(
                new Card(Color.BLUE, Value.TWO),
                new Card(Color.RED, Value.FIVE),
                new Card(Color.YELLOW, Value.SIX)
        ));
        game.setLastPlayedCard(new Card(Color.GREEN, Value.EIGHT));

        // Attempt to play an invalid card
        player1.playCard(player1.getHand().get(1), game);

        // Verify the game state remains unchanged
        assertEquals(new Card(Color.GREEN, Value.EIGHT), game.getLastPlayedCard());
        assertEquals(3, player1.getHandSize());
    }



}
