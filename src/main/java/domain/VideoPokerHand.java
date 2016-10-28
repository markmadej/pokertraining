package domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/*
 * This class represents a given video poker hand.
 * The hand will always contain 5 cards.
 */
public class VideoPokerHand {
	
	private String normalizedHand = null;
	public String getNormalizedHand() {
		return normalizedHand;
	}

	private int[] normalizedIndexes = null;
	private Card[] originalCards = null;
	private ArrayList<Card> sortedCards = new ArrayList<Card>(5);
	
	public VideoPokerHand(ArrayList<Card> cards) {
		calculateHand(cards);
	}

	private void calculateHand(ArrayList<Card> cards) {
		int[] cardsPerSuit = new int[5]; // Need 5 since suits are currently 1-indexed
		Arrays.fill(cardsPerSuit, 0);
		sortedCards.clear(); 
		originalCards = (Card[]) cards.toArray();
		for (int i = 0; i < 5; i++) {
			Card c = originalCards[i];
			cardsPerSuit[c.getSuit()] = cardsPerSuit[c.getSuit()] + 1;
		}
		
		sortedCards.add(0, originalCards[0]);
		for (int i = 1; i < 5; i++) {
			
			for (int j = 0; j < sortedCards.size(); j++) {
				Card compareCard = sortedCards.get(j);
				
			}
		}
	}

	/*
	 * To optimize for storage space as much as possible, we should normalize
	 * hands so the same combinations can be stored in one DB row.
	 * For example, the following hands are all equivalent:
	 * AcJcAdJd5s
	 * AhJhAcJc5d
	 * AhJhAcJc5s
	 * 
	 * Our normalized string will do the following:
	 * Rearrange so the suit with the highest number of cards is on the left, 2nd most to the right, etc
	 * Within each suit, arrange from the highest card to lowest, left to right
	 * Change suits to suit 1, 2, 3 (ie AcJcAdJd5s would translate to A1J1A2J253)
	 */
}
