package domain;

public class Card {
	
	// Suit constants
	public static final int CLUBS = 1;
	public static final int DIAMONDS = 2;
	public static final int HEARTS = 3;
	public static final int SPADES = 4;
	
	// Denomination constants
	public static final int DEUCE = 1;
	public static final int TREY = 2;
	public static final int FOUR = 3;
	public static final int FIVE = 4;
	public static final int SIX = 5;
	public static final int SEVEN = 6;
	public static final int EIGHT = 7;
	public static final int NINE = 8;
	public static final int TEN = 9;
	public static final int JACK = 10;
	public static final int QUEEN = 11;
	public static final int KING = 12;
	public static final int ACE = 13;
	
	private int suit = 0;
	private int denomination = 0;
	
	public Card (int suit, int denomination) {
		if (suit < Card.CLUBS || suit > Card.SPADES) {
			throw new RuntimeException("Suit value " + suit + " is out of range.");
		}
		if (denomination < Card.DEUCE || denomination > Card.ACE) {
			throw new RuntimeException("Denomination value " + denomination + " is out of range.");
		}
		
		this.suit = suit;
		this.denomination = denomination;
	}
	
	public String toString() {
		String tempStr = "";
		switch (denomination) {
			case DEUCE:
				tempStr += "Deuce";
				break;
			case TREY:
				tempStr += "Trey";
				break;
			case FOUR:
				tempStr += "Four";
				break;
			case FIVE:
				tempStr += "Five";
				break;
			case SIX:
				tempStr += "Six";
				break;
			case SEVEN:
				tempStr += "Seven";
				break;
			case EIGHT:
				tempStr += "Eight";
				break;
			case NINE:
				tempStr += "Nine";
				break;
			case TEN:
				tempStr += "Ten";
				break;
			case JACK:
				tempStr += "Jack";
				break;
			case QUEEN:
				tempStr += "Queen";
				break;
			case KING:
				tempStr += "King";
				break;
			case ACE:
				tempStr += "Ace";
				break;
			default:
				throw new RuntimeException("Bad denomination value (" + denomination + ") in toString.");
		}
		switch (suit) {
			case CLUBS:
				tempStr += " of clubs";
				break;
			case DIAMONDS:
				tempStr += " of diamonds";
				break;
			case HEARTS:
				tempStr += " of hearts";
				break;
			case SPADES:
				tempStr += " of spades";
				break;
			default:
				throw new RuntimeException("Bad suit value (" + suit + ") in toString.");
		}
		return tempStr;
	}
	
	/*
	 * shortString() returns the two character string for a hand, like Ac or Kd.
	 */
	public String shortString() {
		String tempStr = "";
		switch (denomination) {
			case DEUCE:
				tempStr += "2";
				break;
			case TREY:
				tempStr += "3";
				break;
			case FOUR:
				tempStr += "4";
				break;
			case FIVE:
				tempStr += "5";
				break;
			case SIX:
				tempStr += "6";
				break;
			case SEVEN:
				tempStr += "7";
				break;
			case EIGHT:
				tempStr += "8";
				break;
			case NINE:
				tempStr += "9";
				break;
			case TEN:
				tempStr += "T";
				break;
			case JACK:
				tempStr += "J";
				break;
			case QUEEN:
				tempStr += "Q";
				break;
			case KING:
				tempStr += "K";
				break;
			case ACE:
				tempStr += "A";
				break;
			default:
				throw new RuntimeException("Bad denomination value (" + denomination + ") in shortString.");
		}
		switch (suit) {
			case CLUBS:
				tempStr += "c";
				break;
			case DIAMONDS:
				tempStr += "d";
				break;
			case HEARTS:
				tempStr += "h";
				break;
			case SPADES:
				tempStr += "s";
				break;
			default:
				throw new RuntimeException("Bad suit value (" + suit + ") in shortString.");
		}
		return tempStr;
	}

	/*
	 * shortDenominationString() returns the 1 character string for a card's denomination, like A, K, 2.
	 */
	public String shortDenominationString() {
		switch (denomination) {
			case DEUCE:
				return "2";
			case TREY:
				return "3";
			case FOUR:
				return "4";
			case FIVE:
				return "5";
			case SIX:
				return "6";
			case SEVEN:
				return "7";
			case EIGHT:
				return "8";
			case NINE:
				return "9";
			case TEN:
				return "T";
			case JACK:
				return "J";
			case QUEEN:
				return "Q";
			case KING:
				return "K";
			case ACE:
				return "A";
			default:
				throw new RuntimeException("Bad denomination value (" + denomination + ") in shortString.");
		}
	}
	
	public int getSuit() {
		return suit;
	}

	public int getDenomination() {
		return denomination;
	}

	public int compare(Card c1, Card c2) {
		if (c1.getSuit() < c2.getSuit()) {
			return -1;
		}
		if (c1.getSuit() > c2.getSuit()) {
			return 1;
		}
		//Suits are the same, compare the denominations.
		if (c1.getDenomination() == c2.getDenomination()) {
			return 0;
		}
		if (c1.getDenomination() < c2.getDenomination()) {
			return -1;
		} else {
			return 1;
		}
	}


}
