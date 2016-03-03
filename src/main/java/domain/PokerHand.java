package domain;

import java.util.ArrayList;
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
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public PokerHand() {}
	
	public PokerHand(ArrayList<Card> cards) {
		for (Card c : cards) {
			this.cards.add(c);
		}
		refreshHand();
	}
	
	public void addCard(Card c) {
		cards.add(c);
		refreshHand();
	}
	
	/**
	 * This function refreshes data and recalculates the hand.
	 * It should be called any time poker hand data is modified (cards added, etc)
	 */
	public void refreshHand() {
		populateCardMatrix();
		evaluateHand();	
	}
	
	/**
	 * This function looks at all cards in 'cards' and determines
	 * the highest ranking combination between them.
	 * It will fill in the handRanking and best5Cards variables if successful.
	 * 
	 * @return	true on success, false on failure.  Less than 5 cards triggers a failure.
	 */
	public boolean evaluateHand() {
		if (cards.size() < 4) {
			// If the hand doesn't have 5 cards, fail.  4 because of zero index 
			return false;
		}
		
		return false;
	}
	
	private boolean isStraightFlush() {
		// Go through each suit, try to find longest string of 5 cards
		for (int suit = 0; suit < 4; suit++) {
			//Count the ace on the bottom end too, starting with the wheel
			int cardsInARow = 0;
			for (int denom = Card.ACE; denom >= Card.DEUCE; denom--) {
				if (cardMatrix[suit][denom] == 1) {
					cardsInARow++;
				} else {
					cardsInARow = 0;
				}
				if (cardsInARow == 5) {
					// We found a straight flush!  
				}
			}
			if (cardMatrix[suit][Card.ACE] == 1) {
				cardsInARow++;
			}
		}
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
	 * Don't call this directly - populatecardMatrix calls it.
	 */
	private void resetCardMatrix() {
		for (int i = 0; i < 4; i++) {
			Arrays.fill(cardMatrix[i], 0);
		}
	}
	
	private void populateCardMatrix() {
		//Reset the array to all zeroes
		resetCardMatrix();
		
		for (Card c : cards) {
			//Add a marker for each card we have
			cardMatrix[c.getSuit()][c.getDenomination()] = 1;
		}
	}
}
