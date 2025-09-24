import java.awt.Point;
import java.util.Scanner;

public class PlayWar {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Ask for deck size (highest card)
        System.out.print("Enter the highest card value (2-14): ");
        int maxCard = sc.nextInt();

        // Open or closed mode
        System.out.print("Show cards openly? (true/false): ");
        boolean isOpen = sc.nextBoolean();

        // Automatic or manual progression
        System.out.print("Press Enter to play each battle manually? (true/false): ");
        boolean manualMode = sc.nextBoolean();
        sc.nextLine(); // consume leftover newline

        War war = new War(maxCard, isOpen);

        System.out.println("Initial State:");
        System.out.println(war);

        // Play the game
        int round = 1;
        while (true) {
            System.out.println("\n--- Round " + round + " ---");

            if (manualMode) {
                System.out.print("Press Enter to continue...");
                sc.nextLine();
            }

            boolean ongoing = war.war();
            System.out.println(war);
            Point scores = war.score();
            System.out.println("Scores: Player1 = " + scores.x + ", Player2 = " + scores.y);

            if (!ongoing) {
                System.out.println("\nGame Over!");
                if (scores.x > scores.y) {
                    System.out.println("Player1 Wins!");
                } else if (scores.y > scores.x) {
                    System.out.println("Player2 Wins!");
                } else {
                    System.out.println("It's a tie!");
                }
                break;
            }
            round++;
        }

        sc.close();
    }
}