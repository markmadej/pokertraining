package domain;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * This class represents a poker hand.
 * 
 * It contains up to 7 cards.  This is a new restriction I put in place
 * because it will make calculations easier and realistically, most
 * hands (if not all) that I want to run through this will have 7 cards.
 * We can modify for Omaha/etc later as needed.
 * 
 * This contains functions that allow us to evaluate the hand,
 * as well as compare two different poker hands to determine a 
 * winner.
 */
public class PokerHand {
	
	public static final int RANK_NOT_CALCULATED = 0;
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
	private int handRanking = RANK_NOT_CALCULATED;
	private ArrayList<Card> best5Cards = new ArrayList<Card>();
	
	/*
	 * cardMatrix is a virtual grid that represents the cards in your hand.
	 * Different way to calculate hand rankings, let's see how it goes.
	 * 
	 * Has to be 5/14 as opposed to 4/13 because first card is 1-based, not zero based.
	 */
	private int cardMatrix[][] = new int[5][14];
	
	private ArrayList<Card> cards = new ArrayList<Card>();
	
	public PokerHand() {}
	
	public PokerHand(ArrayList<Card> cards) {
		for (Card c : cards) {
			this.cards.add(c);
		}
		refreshHand();
	}
	
	public void addCard(Card c) {
		if (cards.size() >= 7) {
			throw new RuntimeException("Can't add more than 7 cards to a hand.");
		}
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
	 * @return	true on success, false on failure.  Less than 5 cards returns true.
	 */
	public boolean evaluateHand() {
		if (cards.size() < 4) {
			// If the hand doesn't have 5 cards, no need to evaluate.
			handRanking = RANK_NOT_CALCULATED;
			return true;
		}
		
		if (isStraightFlush()) return true;
		
		if (isFourOfAKind()) return true;
		
		if (isFullHouse()) return true;
		
		if (isFlush()) return true;
		
		if (isStraight()) return true;
		
		if (isThreeOfAKind()) return true;
		
		if (isTwoPair()) return true;
		
		if (isOnePair()) return true;
		
		if (calculateHighCards()) return true;
		
		return false;
	}
	
	private boolean isStraightFlush() {
		// Go through each suit, try to find longest string of 5 cards
		
		for (int suit = 1; suit <= 4; suit++) {
			best5Cards.clear();
			//Count the ace on the bottom end too, starting with the wheel
			int cardsInARow = 0;
			for (int denom = Card.ACE; denom >= Card.DEUCE; denom--) {
				if (cardMatrix[suit][denom] == 1) {
					cardsInARow++;
					best5Cards.add(new Card(suit, denom));
					if (cardsInARow == 5) {
						// We found a straight flush!  
						handRanking = STRAIGHT_FLUSH;
						return true;
					}
				} else {
					cardsInARow = 0;
					best5Cards.clear();
				}
			}
			if (cardMatrix[suit][Card.ACE] == 1 && cardsInARow == 4) {
				best5Cards.add(new Card(suit, Card.ACE));
				handRanking = STRAIGHT_FLUSH;
				return true;
			}
		}
		best5Cards.clear();
		return false;
	}
	
	private boolean isFourOfAKind() {
		//First check to see if there is a 4 of a kind.  Then find the highest kicker after.
		best5Cards.clear();
		for (int denom = Card.DEUCE; denom <= Card.ACE; denom++) {
			if (cardMatrix[1][denom] == 1 && 
				cardMatrix[2][denom] == 1 &&
				cardMatrix[3][denom] == 1 &&
				cardMatrix[4][denom] == 1) {
				// 4 of a kind!  Add the cards to the best card list.
				best5Cards.add(new Card(1, denom));
				best5Cards.add(new Card(2, denom));
				best5Cards.add(new Card(3, denom));
				best5Cards.add(new Card(4, denom));
				
				// Identify the highest kicker and you're done.
				for (int kickerDenom = Card.ACE; kickerDenom >= Card.DEUCE; kickerDenom--) {
					for (int suit = 1; suit <= 4; suit++) {
						if (cardMatrix[suit][denom] == 1) {
							best5Cards.add(new Card(suit, kickerDenom));
							handRanking = FOUR_OF_A_KIND;
							return true;
						}
					}
				}
			}
		}
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
	private boolean isFlush() {
		
		for (int suit = 1; suit < 5; suit++) {
			best5Cards.clear();
			int suitCount = 0;
			for (int denom = Card.DEUCE; denom <= Card.ACE; denom++) {
				if (cardMatrix[suit][denom] == 1) {
					suitCount++;
					best5Cards.add(new Card(suit, denom));
					if (suitCount >= 5) {
						handRanking = FLUSH;
						return true;
					}
				}

			}
		}
		best5Cards.clear();
		return false;  //Not a flush
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
	
	private boolean calculateHighCards() {
		return false;
	}
	
	/**
	 * This function resets all values to zero in the cardMatrix.
	 * Don't call this directly - populatecardMatrix calls it.
	 */
	private void resetCardMatrix() {
		for (int i = 1; i < 5; i++) {
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

	/**
	 * This function returns the current hand ranking assuming 
	 * its been calculated.  
	 * 
	 * @return The integer corresponding to the right ranking.
	 */
	public int getHandRanking() {
		if (handRanking == RANK_NOT_CALCULATED) {
			throw new RuntimeException("Can't get hand rank - it's not calculated yet");
		}
		return handRanking;
	}

	public int getCardMatrixValue(int suit, int denom) {
		return cardMatrix[suit][denom];
	}
}
