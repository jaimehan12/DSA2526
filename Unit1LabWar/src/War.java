import java.awt.Point;
import java.util.Stack;

public class War {
	boolean isOpen;
	int max;
	Stack<Card> player1;
	Stack<Card> player2;
	Stack<Card> player1Reserves = new Stack<Card>();
	Stack<Card> player2Reserves = new Stack<Card>();

	/**
	 * Start a round of war, distribute cards to each player
	 * 
	 * @param max
	 * @param isOpen
	 */
	public War(int max, boolean isOpen) {
		this.max = max;
		this.isOpen = isOpen;
		Stack<Card> deck = new Stack<>();
		player1 = new Stack<Card>();
		player2 = new Stack<Card>();
		player1Reserves = new Stack<Card>();
		player2Reserves = new Stack<Card>();
		// Build deck from 2 up to max
		for (int r = 2; r <= max; r++) {
			deck.push(new Card(r, "H"));
			deck.push(new Card(r, "D"));
			deck.push(new Card(r, "C"));
			deck.push(new Card(r, "S"));
		}

		System.out.println("Deck: " + deck);
		for (int i = 0; i < 1000; i++) {
			shuffle(deck);
		}

		while (!deck.isEmpty()) {
			player1.add(deck.pop());
			player2.add(deck.pop());
		}

	}

	/**
	 * Shuffle a (given) deck of cards
	 * @param deck
	 */
	public void shuffle(Stack<Card> deck) {
		Stack<Card> shuffle1 = new Stack<Card>();
		Stack<Card> shuffle2 = new Stack<Card>();

		// split deck randomly into two piles
		while (!deck.isEmpty()) {
			if ((int) (Math.random() * 2) == 1) {
				shuffle1.push(deck.pop());
			} else {
				shuffle2.push(deck.pop());
			}
		}

		// merge them back into deck randomly
		while (!shuffle1.isEmpty() || !shuffle2.isEmpty()) {
			if (shuffle1.isEmpty()) {
				deck.push(shuffle2.pop());
			} else if (shuffle2.isEmpty()) {
				deck.push(shuffle1.pop());
			} else {
				if ((int) (Math.random() * 2) == 1) {
					deck.push(shuffle1.pop());
				} else {
					deck.push(shuffle2.pop());
				}
			}
		}
	}

	public String displayBattle() {
		return "";
	}

	/**
	 * Given player, check if the player has cards in reserve Output indicates if
	 * game is over
	 * @param player
	 * @return
	 */
	public boolean refill(Stack<Card> player) {
		if (player.equals(player1)) {
			if (player1Reserves.isEmpty()) {
				return false;
			}
			while (!player1Reserves.isEmpty()) {
				player.push(player1Reserves.pop());
			}
			return true;
		}
		if (player2Reserves.isEmpty()) {
			return false;
		}
		while (!player2Reserves.isEmpty()) {
			player.push(player2Reserves.pop());
		}
		return true;

	}

	/**
	 * There can be a tie that exists
	 */

	public boolean war() {
		// Call Refill
		if (player1.isEmpty()) {
			if (!refill(player1)) {
				return false;
			}
		}
		if (player2.isEmpty()) {
			if (!refill(player2)) {
				return false;
			}
		}
		if (player1.peek().value == max && player2.peek().value == 2) {
			player2Reserves.push(player1.pop());
			player2Reserves.push(player2.pop());
		} else if (player2.peek().value == max && player1.peek().value == 2) {
			player1Reserves.push(player1.pop());
			player1Reserves.push(player2.pop());
		} else if (player2.peek().value < player1.peek().value) {
			player1Reserves.push(player1.pop());
			player1Reserves.push(player2.pop());
		} else if (player1.peek().value < player2.peek().value) {
			player2Reserves.push(player1.pop());
			player2Reserves.push(player2.pop());
		} else {
			if (!battle()) {
				return false;
			}
		}
		return true;
	}

	public boolean battle() {
	    Stack<Card> pile = new Stack<>();
	    // Start with the tied cards
	    pile.push(player1.pop());
	    pile.push(player2.pop());

	    boolean warOngoing = true;

	    while (warOngoing) {
	        // Each player adds 1 face-down card if possible
	        if (!player1.isEmpty()) pile.push(player1.pop());
	        if (!player2.isEmpty()) pile.push(player2.pop());

	        // Refill decks if empty
	        if (player1.isEmpty() && !refill(player1)) return false;
	        if (player2.isEmpty() && !refill(player2)) return false;

	        // Each player adds 1 face-up card
	        Card c1 = player1.pop();
	        Card c2 = player2.pop();
	        pile.push(c1);
	        pile.push(c2);

	        // Check who wins this battle
	        if (c1.value == 2 && c2.value == max) {
	            player1Reserves.addAll(pile);
	            warOngoing = false;
	            return true;
	        } else if (c2.value == 2 && c1.value == max) {
	            player2Reserves.addAll(pile);
	            warOngoing = false;
	            return true;
	        } else if (c1.value > c2.value) {
	            player1Reserves.addAll(pile);
	            warOngoing = false;
	            return true;
	        } else if (c2.value > c1.value) {
	            player2Reserves.addAll(pile);
	            warOngoing = false;
	            return true;
	        }
	    }

	    return true; // Battle resolved, game continues
	}
	public Point score() {
	    int p1Total = player1.size() + player1Reserves.size();
	    int p2Total = player2.size() + player2Reserves.size();
	    return new Point(p1Total, p2Total); // x = Player1, y = Player2
	}
	@Override
	public String toString() {
	    String p1 = "";
	    String p2 = "";

	    if (player1.isEmpty()) {
	        p1 = "";
	    } else {
	        p1 = "X" + player1.peek();
	    }

	    if (player2.isEmpty()) {
	        p2 = "";
	    } else {
	        p2 = "X" + player2.peek();
	    }

	    if (isOpen) {
	        return "Player1: " + player1 + "\n" +
	               "Player2: " + player2 + "\n" +
	               "Player1Reserves: " + player1Reserves + "\n" +
	               "Player2Reserves: " + player2Reserves + "\n";
	    } else {
	        return "Player1: " + p1 + "\n" +
	               "Player2: " + p2 + "\n";
	    }
	}

}