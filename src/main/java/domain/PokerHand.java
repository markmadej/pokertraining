package domain;

import java.util.Arrays;

/*
 * This class represents a poker hand.
 * 
 * It contains an arbitrary number of cards (usually will be 7)
 * and it contains functions that allow us to evaluate the hand,
 * as well as compare two different poker hands to determine a 
 * winner.
 */
public class PokerHand {
	
	public static final int HIGH_CARD = 1;
	public static final int ONE_PAIR = 2;
	public static final int TWO_PAIR = 3;
	public static final int THREE_OF_A_KIND = 4;
	public static final int STRAIGHT = 5;
	public static final int FLUSH = 6;
	public static final int FULL_HOUSE = 7;
	public static final int FOUR_OF_A_KIND = 8;
	public static final int STRAIGHT_FLUSH = 9;
	
	/**
	 * handRanking will contain the ranking of the best hand - 
	 * ONE_PAIR, FULL_HOUSE, etc.
	 * Initialized at an invalid value to avoid confusion.
	 */
	private int handRanking = 0;
	private Card[] best5Cards = new Card[5];
	
	/*
	 * cardMatrix is a virtual grid that represents the cards in your hand.
	 * Different way to calculate hand rankings, let's see how it goes.
	 */
	private int cardMatrix[][] = new int[4][13];
	
	private Card[] cards = null;
	
	public PokerHand() {}
	
	public PokerHand(Card[] cards) {
		this.cards = new Card[cards.length];
		for (int i = 0; i < cards.length; i++) {
			this.cards[i] = cards[i];
		}
	}
	
	/**
	 * This function looks at all cards in 'cards' and determines
	 * the highest ranking combination between them.
	 * It will fill in the handRanking and best5Cards variables if successful.
	 * 
	 * @return	true on success, false on failure.  Less than 5 cards triggers a failure.
	 */
	public boolean evaluateHand() {
		if (cards.length < 4) {
			// If the hand doesn't have 5 cards, fail.  4 because of zero index 
			return false;
		}
		
		return false;
	}
	
	private boolean isStraightFlush() {
		return false;
	}
	
	private boolean isFourOfAKind() {
		return false;
	}
	
	private boolean isFullHouse() {
		return false;
	}
	
	private boolean isStraight() {
		return false;
	}
	
	/**
	 * isFlush returns the suit if a flush is found, or -1 for no flush.
	 */
	private int isFlush() {
		for (int suit = 0; suit < 4; suit++) {
			int suitCount = 0;
			for (int denom = 0; denom < 13; denom++) {
				if (cardMatrix[suit][denom] == 1) {
					suitCount++;
				}
				if (suitCount >= 5) {
					return suit;
				}
			}
		}
		return -1;  //Not a flush
	}
	
	private boolean isThreeOfAKind() {
		return false;
	}
	
	private boolean isTwoPair() {
		return false;
	}
	
	private boolean isOnePair() {
		return false;
	}
	
	private void determineHighCards() {

	}
	
	/**
	 * This function resets all values to zero in the cardMatrix.
	 */
	private void resetCardMatrix() {
		for (int i = 0; i < 4; i++) {
			Arrays.fill(cardMatrix[i], 0);
		}
	}
	
	private void populateCardMatrix() {
		for (Card c : cards) {
			cardMatrix[c.getSuit()][c.getDenomination()] = 1;
		}
	}
}
