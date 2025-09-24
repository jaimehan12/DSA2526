/**
 * Represents a standard playing card with a numeric value, suit, and rank character.
 * Values range from 2 to 14, where 11 = Jack, 12 = Queen, 13 = King, and 14 = Ace.
 * Suits are represented using symbols: ♠ (Spades), ♥ (Hearts), ♦ (Diamonds), ♣ (Clubs).
 * Rank is a single character representing the card's face: 'A', 'K', 'Q', 'J', 'T', '2'–'9'.
 */
public class Card {

    int value;

    String suit;

    char rank;

    /**
     * Constructs a Card with the specified value and suit code.
     * @param value Numeric value of the card (2–14)
     * @param suit  Suit code: "H" = Hearts, "D" = Diamonds, "C" = Clubs, "S" = Spades
     */
    public Card(int value, String suit) {
        this.value = value;

        switch (suit) {
            case "H" -> this.suit = "♥";
            case "D" -> this.suit = "♦";
            case "C" -> this.suit = "♣";
            case "S" -> this.suit = "♠";
            default -> this.suit = "?"; // fallback for invalid suit
        }

        switch (value) {
            case 10 -> rank = 'T';
            case 11 -> rank = 'J';
            case 12 -> rank = 'Q';
            case 13 -> rank = 'K';
            case 14 -> rank = 'A';
            default -> rank = (char) ('0' + value); // 2–9
        }
    }

    /** Returns the string representation of the card, e.g., "Q♥" */
    @Override
    public String toString() {
        return "" + rank + suit;
    }


    public int getValue() {
        return value;
    }

    public String getSuit() {
        return suit;
    }

    public char getRank() {
        return rank;
    }
}