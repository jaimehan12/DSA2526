import java.awt.Point;
import java.util.Stack;

/**
 * The War class simulates the classic card game "War" between two players.
 * Each player has a deck and a reserve pile. Rounds are played until one player 
 * cannot continue, at which point the game ends. This version supports chained 
 * wars, manual or automatic play, and optional open/hidden card display.
 * Note: ACE is treated as the highest card with value (14)
 */
public class War {

    //indicates if a war is currently in progress.
    boolean war;

    // game should show full deck contents or only the top card. 
    boolean isOpen;

    // Maximum card value 
    int max;

    //Player 1's main deck.
    Stack<Card> player1;

    //Player 2's main deck. 
    Stack<Card> player2;

    //Player 1's reserve pile (cards won).
    Stack<Card> player1Reserves = new Stack<>();

    //Player 2's reserve pile 
    Stack<Card> player2Reserves = new Stack<>();

    /**
     * Constructs a war game with the maximum card value and display mode.
     * Initializes both players' decks by creating a standard 4-suit set of cards
     * from 2 up to maximum card value and shuffles the combined deck before dealing evenly.
     *
     * @param max    the highest card value in the deck (ACE = 14)
     * @param isOpen if {@code true}, all cards in the deck and reserves are shown; 
     *               if {@code false}, only top cards are shown
     */
    public War(int max, boolean isOpen) {
        this.max = max;
        this.isOpen = isOpen;
        Stack<Card> deck = new Stack<>();
        player1 = new Stack<>();
        player2 = new Stack<>();
        player1Reserves = new Stack<>();
        player2Reserves = new Stack<>();

        // Create full deck for all suits
        for (int r = 2; r <= max; r++) {
            deck.push(new Card(r, "H"));
            deck.push(new Card(r, "D"));
            deck.push(new Card(r, "C"));
            deck.push(new Card(r, "S"));
        }

        // Shuffle deck multiple times
        for (int i = 0; i < 1000; i++)
            shuffle(deck);

        // Deal deck alternately to players
        while (!deck.isEmpty()) {
            player1.add(deck.pop());
            if (!deck.isEmpty()) {
                player2.add(deck.pop());
            }
        }
    }

    /**
     * Shuffles a given deck by splitting it into two stacks and randomly interleaving them.
     *
     * @param deck the stack of cards to shuffle
     */
    private void shuffle(Stack<Card> deck) {
        Stack<Card> shuffle1 = new Stack<>();
        Stack<Card> shuffle2 = new Stack<>();

        while (!deck.isEmpty()) {
            if ((int) (Math.random() * 2) == 1)
                shuffle1.push(deck.pop());
            else
                shuffle2.push(deck.pop());
        }

        while (!shuffle1.isEmpty() || !shuffle2.isEmpty()) {
            if (shuffle1.isEmpty())
                deck.push(shuffle2.pop());
            else if (shuffle2.isEmpty())
                deck.push(shuffle1.pop());
            else {
                if ((int) (Math.random() * 2) == 1)
                    deck.push(shuffle1.pop());
                else
                    deck.push(shuffle2.pop());
            }
        }
    }

    /**
     * Refills a player's deck from their reserve pile when the main deck is empty.
     *
     * @param player the deck to refill (either player1 or player2)
     * @return true if the deck was refilled; false if reserves are empty
     */
    private boolean refill(Stack<Card> player) {
        if (player.equals(player1)) {
            if (player1Reserves.isEmpty())
                return false;
            while (!player1Reserves.isEmpty())
                player.push(player1Reserves.pop());
            return true;
        } else {
            if (player2Reserves.isEmpty())
                return false;
            while (!player2Reserves.isEmpty())
                player.push(player2Reserves.pop());
            return true;
        }
    }

    /**
     * Plays a single round of War. Draws one card from each player's deck.
     * If the cards are equal, initiates a war via battle method.
     *
     * @return a string message indicating the round result or "GameOver" if the game ends
     */
    public String playRound() {
        if (player1.isEmpty() && !refill(player1))
            return "GameOver";
        if (player2.isEmpty() && !refill(player2))
            return "GameOver";

        Card p1Card = player1.pop();
        Card p2Card = player2.pop();

        System.out.println("Player1 plays: " + p1Card);
        System.out.println("Player2 plays: " + p2Card);

        if (p1Card.value == p2Card.value) {
            String warResult = battle(p1Card, p2Card);
            if (warResult.equals("GameOver")) {
                return "GameOver";
            } else {
                System.out.print(warResult);
            }
        } else {
            if (compareCards(p1Card, p2Card) > 0) {
                player1Reserves.push(p1Card);
                player1Reserves.push(p2Card);
            } else {
                player2Reserves.push(p1Card);
                player2Reserves.push(p2Card);
            }
        }

        return "Round completed";
    }

    /**
     * Handles a "war" scenario when both players draw cards of equal value.
     * Each player places one face-down card (if available) and then draws a face-up card
     * to determine the winner. Chained wars are handled recursively.
     *
     * @param first1 the first card drawn by player1
     * @param first2 the first card drawn by player2
     * @return a {@code String} representing the war log, or "GameOver" if a player cannot continue
     */
    private String battle(Card first1, Card first2) {
        Stack<Card> pile = new Stack<>();
        pile.push(first1);
        pile.push(first2);
        war = true;
        //https://docs.oracle.com/javase/8/docs/api/java/lang/StringBuilder.html
        StringBuilder warLog = new StringBuilder();

        while (true) {
            warLog.append("WAR!\n");

            if (!player1.isEmpty())
                pile.push(player1.pop());
            if (!player2.isEmpty())
                pile.push(player2.pop());

            if ((player1.isEmpty() && !refill(player1)) || (player2.isEmpty() && !refill(player2))) {
                return "GameOver";
            }

            Card c1 = player1.pop();
            Card c2 = player2.pop();
            pile.push(c1);
            pile.push(c2);

            warLog.append("Player1 war card: ").append(c1)
                  .append(", Player2 war card: ").append(c2).append("\n");

            int cmp = compareCards(c1, c2);

            if (cmp > 0) {
                player1Reserves.addAll(pile);
                warLog.append("Player1 wins this war!\n");
                war = false;
                return warLog.toString();
            } else if (cmp < 0) {
                player2Reserves.addAll(pile);
                warLog.append("Player2 wins this war!\n");
                war = false;
                return warLog.toString();
            } else {
                warLog.append("Another WAR!\n");
            }
        }
    }

    /**
     * Compares two cards according to War rules, treating 2 as the lowest
     * and ACE as the highest (value). But 2 beats ACE
     *
     * @param c1 the first card
     * @param c2 the second card
     * @return a positive integer if c1 > c2, negative if c1 < c2, 0 if equal
     */
    private int compareCards(Card c1, Card c2) {
        if (max == 2)
            return Integer.compare(c1.value, c2.value);
        if (c1.value == 2 && c2.value == max)
            return 1;
        if (c2.value == 2 && c1.value == max)
            return -1;
        if (c1.value == 2 && c2.value != 2)
            return -1;
        if (c2.value == 2 && c1.value != 2)
            return 1;
        return Integer.compare(c1.value, c2.value);
    }

    /**
     * Returns the total score for both players, which is the sum of their 
     * main deck and reserve pile sizes.
     *
     * @return a {x,y} where {x} is Player1's total and {y} is Player2's total
     */
    public Point score() {
        int p1Total = player1.size() + player1Reserves.size();
        int p2Total = player2.size() + player2Reserves.size();
        return new Point(p1Total, p2Total);
    }

    /**
     * Returns a string representation of the current game state.
     * If is open is true, shows all cards in each deck and reserve pile.
     * Otherwise, only shows the top card of each deck (hidden cards shown as X).
     *
     * @return a string representing the current game state
     */
    @Override
    public String toString() {
        if (isOpen) {
            return "Player1 deck: " + player1 + "\n" + 
                   "Player2 deck: " + player2 + "\n" + 
                   "Player1 reserves: " + player1Reserves + "\n" + 
                   "Player2 reserves: " + player2Reserves + "\n";
        } else {
            String p1 = player1.isEmpty() ? "" : "X" + player1.peek(); //make sure stack is not empty
            String p2 = player2.isEmpty() ? "" : "X" + player2.peek();
            return "Player1: " + p1 + "\n" + "Player2: " + p2 + "\n";
        }
    }
}