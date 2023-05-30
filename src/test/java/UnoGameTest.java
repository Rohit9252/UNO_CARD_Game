
import com.game.uno.enums.Color;
import com.game.uno.enums.Value;
import com.game.uno.models.Card;
import com.game.uno.models.Player;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Scanner;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.Collections;


import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UnoGameTest {

    @Test
    public void testPromptCardChoice_DrawCard() {
        // Set up the test scenario
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getHandSize()).thenReturn(2);
        when(currentPlayer.getHand()).thenReturn(Collections.emptyList());

        // Mock the user input
        String input = "draw\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method and verify the result
        Card cardChoice = promptCardChoice(currentPlayer);
        assertNull(cardChoice);
    }

    @Test
    public void testPromptCardChoice_ValidCardIndex() {
        // Set up the test scenario
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getHandSize()).thenReturn(3);
        Card card1 = new Card(Color.RED, Value.FIVE);
        Card card2 = new Card(Color.BLUE, Value.SEVEN);
        Card card3 = new Card(Color.YELLOW, Value.ONE);
        when(currentPlayer.getHand()).thenReturn(Arrays.asList(card1, card2, card3));

        // Mock the user input
        String input = "1\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method and verify the result
        Card cardChoice = promptCardChoice(currentPlayer);
        assertEquals(card2, cardChoice);
    }



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


    @Test
    public void testPromptCardChoice_InvalidCardIndex() {
        // Set up the test scenario
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getHandSize()).thenReturn(3);
        Card card1 = new Card(Color.RED, Value.FIVE);
        Card card2 = new Card(Color.BLUE, Value.SEVEN);
        Card card3 = new Card(Color.YELLOW, Value.ONE);
        when(currentPlayer.getHand()).thenReturn(Arrays.asList(card1, card2, card3));

        // Mock the user input
        String input = "5\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method and verify the result
        Card cardChoice = promptCardChoice(currentPlayer);
        assertNull(cardChoice);
    }

    @Test
    public void testPromptCardChoice_InvalidInput() {
        // Set up the test scenario
        Player currentPlayer = mock(Player.class);
        when(currentPlayer.getHandSize()).thenReturn(3);
        when(currentPlayer.getHand()).thenReturn(Collections.emptyList());

        // Mock the user input
        String input = "invalid\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        System.setIn(inputStream);

        // Call the method and verify the result
        Card cardChoice = promptCardChoice(currentPlayer);
        assertNull(cardChoice);
    }

    // Additional test methods for other scenarios

}

