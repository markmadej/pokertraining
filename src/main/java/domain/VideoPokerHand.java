package domain;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * This class represents a given video poker hand.
 * The hand will always contain 5 cards.
 */
public class VideoPokerHand {
	
	private String normalizedHand = null;


	private int[] normalizedIndices;
	private Card[] originalCards = null;
	private ArrayList<Card> sortedCards = new ArrayList<Card>(5);
	
	public VideoPokerHand(ArrayList<Card> cards) {
		normalizeCards(cards);
		normalizedHand = createNormalizedString(sortedCards);
		normalizedIndices = calculateNormalizedIndices(originalCards, sortedCards);
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
	private void normalizeCards(ArrayList<Card> cards) {
		
		if (cards.size() != 5) {
			throw new RuntimeException("Tried to normalize a hand with " + cards.size() + " (ie. not 5) cards.");
		}
		int[] cardsPerSuit = new int[5]; // Need 5 since suits are currently 1-indexed
		Arrays.fill(cardsPerSuit, 0);
		sortedCards.clear(); 
		originalCards = new Card[5];
		
		for (int i = 0; i < cards.size(); i++) {
			originalCards[i] = cards.get(i);
		}
		//Create counters for each suit that give you the number of cards in each suit, which will be used for sorting.
		for (int i = 0; i < 5; i++) {
			Card c = originalCards[i];
			cardsPerSuit[c.getSuit()] = cardsPerSuit[c.getSuit()] + 1;
		}
		
		sortedCards.add(0, originalCards[0]);
		for (int i = 1; i < 5; i++) {

			Card newCardToInsert = originalCards[i];
			boolean insertedCard = false;
			for (int j = 0; j < sortedCards.size(); j++) {
				
				Card compareCard = sortedCards.get(j);
				if (newCardToInsert.getSuit() == compareCard.getSuit()) {
					// If the cards have the same suit, insert before if the denomination is higher.
					if (newCardToInsert.getDenomination() > compareCard.getDenomination()) {
						sortedCards.add(j, newCardToInsert);
						insertedCard = true;
						break;	
					} else {
						continue;
					}
				}
				
				if (cardsPerSuit[newCardToInsert.getSuit()] > cardsPerSuit[compareCard.getSuit()]) {
					// If this card's suit has more than (or the same as) the current compare card, bump the compare card over one and insert.
					sortedCards.add(j, newCardToInsert);
					insertedCard = true;
					break;
				}	
				if (cardsPerSuit[newCardToInsert.getSuit()] == 1 && cardsPerSuit[compareCard.getSuit()] == 1) {
					if (newCardToInsert.getDenomination() > compareCard.getDenomination()) {
						sortedCards.add(j, newCardToInsert);
						insertedCard = true;
						break;
					} else {
						continue;
					}
				}
			}
			if (!insertedCard) {
				sortedCards.add(sortedCards.size(), newCardToInsert);
			}

		}
		
		// Now sortedCards has the cards in order, but there is one final potential issue.  If there are two pairs of two suits, 
		// those may be out of order.  Let's see if that is the case and fix it.
		if (sortedCards.get(0).getSuit() == sortedCards.get(1).getSuit() && sortedCards.get(2).getSuit() == sortedCards.get(3).getSuit()) {
			if ((sortedCards.get(2).getDenomination() > sortedCards.get(0).getDenomination()) ||
					(sortedCards.get(2).getDenomination() == sortedCards.get(0).getDenomination() &&
							sortedCards.get(3).getDenomination() > sortedCards.get(1).getDenomination()
							)
					) {
				// We need to flip the first two cards with the second two cards.
				Card tmp1 = sortedCards.get(0);
				Card tmp2 = sortedCards.get(1);
				Card c3 = sortedCards.remove(2);
				Card c4 = sortedCards.remove(2);
				sortedCards.add(2, tmp2);
				sortedCards.add(2, tmp1);
			}
		}
	}
	
	private int[] calculateNormalizedIndices(Card[] originals, ArrayList<Card> sorted) {
		// Given originalCards and sortedCards, determine what position the cards in originalCards have moved to.
		int[] returnIndices = new int[5];
		for (int i = 0; i < originals.length; i++) {
			Card currICard = originals[i];
			for (int j = 0; j < sorted.size(); j++) {
				Card compareCard = sorted.get(j);
				if (currICard.getSuit() == compareCard.getSuit() &&
						currICard.getDenomination() == compareCard.getDenomination()) {
					returnIndices[i] = j;
					break;
				}
			}
		}
		return returnIndices;
	}
	
	//Public for easy unit test creation.
	public int[] calculateNormalizedIndices(ArrayList<Card> originals, ArrayList<Card> sorted) {
		Card[] orig = new Card[5];
		for (int i = 0; i < 5; i++) {
			orig[i] = originals.get(i);
		}
		return calculateNormalizedIndices(orig, sorted);
	}

	// This should only be called internally and from unit tests.  I left it public for ease of use in unit tests.
	public String createNormalizedString(ArrayList<Card> normalizeCards) {
		// Create the normalized string.
		StringBuffer nb = new StringBuffer();
		int lastSuitNum = 0;
		int lastSuit = -1;
		for (Card c : normalizeCards) {
			nb.append(c.shortDenominationString());
			if (lastSuit != c.getSuit()) {
				lastSuitNum++;
				lastSuit = c.getSuit();
			}
			nb.append(lastSuitNum);
		}
		return nb.toString();
	}


	
	
	public String getNormalizedHand() {
		return normalizedHand;
	}

	public ArrayList<Card> getSortedCards() {
		// This was created solely for use within unit tests.
		// No guarantees on this method's behavior are given if this is applied to any other use cases.
		return sortedCards;
	}
}
