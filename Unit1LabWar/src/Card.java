public class Card {
    int value;   // 2–14 (where 11=J, 12=Q, 13=K, 14=A)
    String suit; // ♠♥♦♣
    char rank;   // A, K, Q, J, T, 2–9

    /**
     * Create a card with value and suit.
     * @param value 2–14
     * @param suit  H, D, C, S (Hearts, Diamonds, Clubs, Spades)
     */
    public Card(int value, String suit) {
        this.value = value;
        
        switch (suit) {
            case "H": this.suit = "♥"; break;
            case "D": this.suit = "♦"; break;
            case "C": this.suit = "♣"; break;
            case "S": this.suit = "♠"; break;
            default:  this.suit = "?"; // fallback
        }

        switch (value) {
            case 10: rank = 'T'; break;
            case 11: rank = 'J'; break;
            case 12: rank = 'Q'; break;
            case 13: rank = 'K'; break;
            case 14: rank = 'A'; break;
            default: rank = (char) ('0' + value); // 2-9
        }
    }

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