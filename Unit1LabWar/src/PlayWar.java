import java.awt.Point;
import java.util.Scanner;

public class PlayWar {
	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in); // Scanner for user input

		// Prompt for highest card value (e.g., 14 for Ace)
		System.out.print("Enter the highest card value (2-14): ");
		int maxCard = sc.nextInt();

		// Prompt for whether to show full decks or just top card
		System.out.print("Show cards openly? (true/false): ");
		boolean isOpen = sc.nextBoolean();

		// Prompt for (press enter to continue each round)
		System.out.print("Press Enter to play each battle manually? (true/false): ");
		boolean manualMode = sc.nextBoolean();
		sc.nextLine(); 

		// Initialize War game with chosen settings
		War war = new War(maxCard, isOpen);

		// Print initial game state
		System.out.println("Initial State:");
		System.out.println(war);

		int round = 1;
		while (true) { // main game loop
			if (manualMode) {
				System.out.print("Press Enter to continue...");
				sc.nextLine(); // wait for user to press enter
			}

			System.out.println("\n--- Round " + round + " ---");

			// Play a round and track result
			String result = war.playRound();

			// Print the current state of decks/reserves
			System.out.println(war);

			// Game over conditions
			if (result.equals("GameOver") || round > 10000) { // also stop after 10,000 rounds to prevent infinite loops
				Point scores = war.score();

				//flag for whether a war was ongoing when game ended
				if (war.war == true) { 
					System.out.println("Game Ended During War.");
				}

				if (round > 10000) {
					System.out.println("Maximum Rounds Exceeded");
				}

				System.out.println("\nGame Over!");
				System.out.println("Final Scores: Player1 = " + scores.x + ", Player2 = " + scores.y);

				// Determine winner
				if (scores.x > scores.y)
					System.out.println("Player1 Wins!");
				else if (scores.y > scores.x)
					System.out.println("Player2 Wins!");
				else
					System.out.println("It's a tie!");

				break; // exit loop/game
			}

			else {
				// Round completed normally; show result and updated scores
				System.out.println(result);
				Point scores = war.score();
				System.out.println("Scores: Player1 = " + scores.x + ", Player2 = " + scores.y);
			}
			round++;
		}

		sc.close(); // close Scanner
	}
}