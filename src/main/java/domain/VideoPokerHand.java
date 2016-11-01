package domain;

import java.util.ArrayList;
import java.util.Arrays;

/*
 * This class represents a given video poker hand.
 * The hand will always contain 5 cards.
 */
public class VideoPokerHand {
	
	public static final int MIN_RANK_INTEGER = 1;
	public static final int MAX_RANK_INTEGER = 14;
	
	//Ranks are not yet factoring in deuces wild.  Focusing on double double bonus
	public static final int RANK_NOT_CALCULATED = -1;
	public static final int NOTHING 			= 1;
	public static final int JACKS_OR_BETTER 	= 2;
	public static final int TWO_PAIR 			= 3;
	public static final int THREE_OF_A_KIND 	= 4;
	public static final int STRAIGHT 			= 5;
	public static final int FLUSH 				= 6;
	public static final int FULL_HOUSE 			= 7;
	public static final int FOUR_FIVES 			= 8;  //Four 5s-Ks.
	public static final int FOUR_DEUCES 		= 9;  //Four 2s, 3s, 4s.
	public static final int FOUR_DEUCES_KICKER 	= 10;
	public static final int FOUR_ACES 			= 11;
	public static final int FOUR_ACES_KICKER	= 12;  //Aces with 2, 3, 4 kicker.
	public static final int STRAIGHT_FLUSH 		= 13;
	public static final int ROYAL_FLUSH 		= 14;
	
	private String normalizedHand = null;
	private int handRank = RANK_NOT_CALCULATED;

	private int[] normalizedIndices;
	private Card[] originalCards = null;
	private ArrayList<Card> sortedCards = new ArrayList<Card>(5);
	
	// This empty constructor is intended for use with unit tests only.
	public VideoPokerHand() { }
	
	// This function takes a string of 5 cards like this:
	// - AcJc5d7s7h
	public VideoPokerHand(String cardStr) {
		if (cardStr.length() != 10) {
			throw new RuntimeException("Can't create a video poker hand with a " + cardStr.length() + " length string:" + cardStr);
		}
		ArrayList<Card> cards = new ArrayList<Card>(5);
		for (int i = 0; i < 5; i++) {
			String cStr = cardStr.substring(i*2, (i*2)+2);
			Card c = new Card(cStr);
			cards.add(c);
		}
		initializeHandData(cards);
	}
	
	public VideoPokerHand(int[] cardNums) {
		// This leverages the integer constructor of Card.
		if (cardNums.length != 5) {
			throw new RuntimeException("Tried to create VideoPokerHand with " + cardNums.length + " cards - invalid.");
		}
		ArrayList<Card> cards = new ArrayList<Card>(5);
		for (int i = 0; i < 5; i++) {
			Card c = new Card(cardNums[i]);
			cards.add(c);
		}
		initializeHandData(cards);
	}
	
	public VideoPokerHand(ArrayList<Card> cards) {
		initializeHandData(cards);
	}

	private void initializeHandData(ArrayList<Card> cards) {
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
				sortedCards.remove(0);
				sortedCards.remove(0);
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
	
	public int calculateBestRank() {
		return calculateBestRank(originalCards);	
	}
	
	// This function calculates the rank of the 5 card hand we currently have.
	public int calculateBestRank(Card[] cards) {
		boolean flush = true;
		int[] numPerCard = new int[MAX_RANK_INTEGER];
		Arrays.fill(numPerCard, 0);
		int suitToMatch = cards[0].getSuit();
		for (int i = 0; i < 5; i++) {
			Card c = cards[i];
			if (c.getSuit() != suitToMatch) {
				flush = false;
			}
			numPerCard[c.getDenomination()] = numPerCard[c.getDenomination()] + 1;
		}
		
		boolean straight = false;
		int straightCards = 0;
		if (numPerCard[Card.ACE] == 1) {
			//Prep for the wheel
			straightCards = 1;
		}

		for (int i = Card.DEUCE; i <= Card.ACE; i++) {
			if (numPerCard[i] == 1) {
				straightCards++;
				if (straightCards == 5) {
					straight = true;
					break;
				}
			} else {
				straightCards = 0;
			}
		}
		if (flush == true && straight == true) {
			if (numPerCard[Card.ACE] == 1 && numPerCard[Card.TEN] == 1) {
				return ROYAL_FLUSH;
			} else {
				return STRAIGHT_FLUSH;
			}
		}
		if (flush == true) {
			return FLUSH;
		}
		if (straight == true) {
			return STRAIGHT;
		}
		
		// At this point we have ruled out any kinds of straights or flushes.
		
		int numPairs = 0;
		int tripsDenom = -1;
		int quadDenom = -1;
		boolean jacksOrBetter = false;
		boolean aceKicker = false;
		boolean kicker234 = false;
		for (int i = Card.DEUCE; i <= Card.ACE; i++) {
			switch (numPerCard[i]) {
			case 4:
				quadDenom = i;
				break;
			case 3:
				tripsDenom = i;
				break;
			case 2:
				numPairs++;
				if (i >= Card.JACK) {
					jacksOrBetter = true;
				}
				break;
			case 1:
				if (i == Card.ACE) {
					aceKicker = true;
				} else if (i == Card.DEUCE || i == Card.TREY || i == Card.FOUR) {
					kicker234 = true;
				}
			}
		}
		
		if (quadDenom != -1) {
			//We have quads, but need to determine which one.
			if (quadDenom == Card.ACE) {
				if (kicker234 == true) {
					return FOUR_ACES_KICKER;
				} else {
					return FOUR_ACES;
				}
			}
			if (quadDenom >= Card.FIVE) {
				return FOUR_FIVES;
			}
			// At this point we have quad 2s, 3s, or 4s.
			if (aceKicker == true) {
				return FOUR_DEUCES_KICKER;
			} else {
				return FOUR_DEUCES;
			}
		}
		if (tripsDenom != -1 && numPairs == 1) {
			return FULL_HOUSE;
		}
		if (numPairs == 2) {
			return TWO_PAIR;
		}
		if (jacksOrBetter == true) {
			return JACKS_OR_BETTER;
		}
		return NOTHING;  // Sad face.  
	}
	
	public String getHumanReadableRank(int rank) {
		switch (rank) {
			case RANK_NOT_CALCULATED:
				return "Not calculated";
			case NOTHING:
				return "Nothing";
			case JACKS_OR_BETTER:
			return "Jacks or better";
			case TWO_PAIR:
				return "Two pair";
			case THREE_OF_A_KIND:
				return "Three of a kind";
			case STRAIGHT:
				return "Straight";
			case FLUSH:
				return "Flush";
			case FULL_HOUSE:
				return "Full house";
			case FOUR_FIVES:
				return "Four fives through kings";
			case FOUR_DEUCES:
				return "Four deuces through fours";
			case FOUR_DEUCES_KICKER:
				return "Four deuces through fours with kicker";
			case FOUR_ACES:
				return "Four aces";
			case FOUR_ACES_KICKER:
				return "Four aces with kicker";
			case STRAIGHT_FLUSH:
				return "Straight flush";
			case ROYAL_FLUSH:
				return "Royal flush";
			default:
				return "ERROR : Rank not found : " + rank;
		}
	}

	public int getHandRank() {
		return handRank;
	}

	public Card[] getOriginalCards() {
		return originalCards;
	}
}
