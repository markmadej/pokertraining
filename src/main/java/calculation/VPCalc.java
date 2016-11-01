package calculation;
import java.util.ArrayList;

import domain.Card;
import domain.ComboRank;
import domain.FiveCardNormalizedResult;
import domain.VideoPokerHand;

// I'll use this to test out some things and build the functions I need.
public class VPCalc {
	
	private FiveCardNormalizedResult results;
	
	public static void main(String[] args) {
		VPCalc vpc = new VPCalc();

		VideoPokerHand vph = new VideoPokerHand("AcJcTc4s4d");
		ArrayList<ComboRank> solution = vpc.solveHandWithAllCombinations(vph);
		int ct = 0;
		for (ComboRank cbr : solution) {
			ct++;
			System.out.println("ComboRank " + ct + ":");
		}
	}
	
	public VPCalc() {
		results = new FiveCardNormalizedResult();
	}
	
	public VPCalc(boolean initializeCache) { 
		// mostly added this to exclude initializing the cache in test cases if not needed.
		if (initializeCache == true) {
			results = new FiveCardNormalizedResult();
		}
	}
	
	public ArrayList<ComboRank> solveHandWithAllCombinations(VideoPokerHand vph) {
		// Go through each combination of holding or not holding each card in the hand.
		Card[] cards = vph.getOriginalCards();
		ArrayList<ComboRank> comboRanks = new ArrayList<ComboRank>(32);
		int iterationCount = 0;
		for (int card1 = 0; card1 < 2; card1++) {
			for (int card2 = 0; card2 < 2; card2++) {
				for (int card3 = 0; card3 < 2; card3++) {
					for (int card4 = 0; card4 < 2; card4++) {
						for (int card5 = 0; card5 < 2; card5++) {
							boolean[] heldIndices = new boolean[5];
							heldIndices[0] = (card1 != 1);
							heldIndices[1] = (card2 != 1);
							heldIndices[2] = (card3 != 1);
							heldIndices[3] = (card4 != 1);
							heldIndices[4] = (card5 != 1);
							iterationCount++;
							System.out.println("Starting solveHandWithAllCombinations iteration " + iterationCount + "/32");
							ComboRank cbr = new ComboRank(cards, heldIndices);
							cbr = solveSingleHand(cbr);
							comboRanks.add(cbr);
						}
					}
				}
			}
		}
		return comboRanks;
	}
	
	/*
	 * Return an ArrayList<Card> structure with all cards that are not in the given list.
	 */
	public ArrayList<Card> getPossibleRemainingCards(Card[] deadCards) {
		ArrayList<Card> remainingCards = new ArrayList<Card>(52 - deadCards.length);
		for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++) {
			for (int denom = Card.DEUCE; denom <= Card.ACE; denom++) {
				boolean foundInDeadList = false;
				for (int i = 0; i < deadCards.length; i++) {
					if (deadCards[i].getSuit() == suit && deadCards[i].getDenomination() == denom) {
						// This card is in our dead card list, don't add it.
						foundInDeadList = true;
					}
					if (foundInDeadList == true) {
						break;
					}
				}
				if (!foundInDeadList) {
					remainingCards.add(new Card(suit, denom));
				}
			}
		}
		return remainingCards;
	}
	
	// This function takes the given 5 card hand and held cards, and it calculates how many
	// times each result takes place through each possible remaining iteration of the cards.
	public ComboRank solveSingleHand(ComboRank cbr) {
		int iterationCount = 0;
		int progressIncrement = 30000000;
		
		boolean[] heldIndices = cbr.getHeldIndices();
		Card[] cards = cbr.getCards();
		System.out.println("Starting solveSingleHand.");
		for (int i = 0; i < 5; i++) {
			System.out.print(cards[i]);
			if (heldIndices[i] == true) {
				System.out.println(" - HELD");
			} else {
				System.out.println(" - not held");
			}
		}
		for (int card1 = 1; card1 <= 52; card1++) {
			Card c1;
			if (heldIndices[0]) {
				c1 = cards[0];
			} else {
				c1 = new Card(card1);
				if (c1.equals(cards[0]) || c1.equals(cards[1]) || c1.equals(cards[2]) || c1.equals(cards[3]) || c1.equals(cards[4])) {
					//The new card can not be one of the cards we were originally dealt.
					continue;
				}
			}
			for (int card2 = 1; card2 <= 52; card2++) {
				Card c2;
				if (heldIndices[1]) {
					c2 = cards[1];
				} else {
					c2 = new Card(card2);
					if (c2.equals(cards[0]) || c2.equals(cards[1]) || c2.equals(cards[2]) || c2.equals(cards[3]) || c2.equals(cards[4])) {
						//The new card can not be one of the cards we were originally dealt.
						continue;
					}
				}

				if (c1.equals(c2)) {
					//Can't have two of the same card in our hand.
					continue;
				}
				for (int card3 = 1; card3 <= 52; card3++) {
					Card c3;
					if (heldIndices[2]) {
						c3 = cards[2];
					} else {
						c3 = new Card(card3);
						if (c3.equals(cards[0]) || c3.equals(cards[1]) || c3.equals(cards[2]) || c3.equals(cards[3]) || c3.equals(cards[4])) {
							//The new card can not be one of the cards we were originally dealt.
							continue;
						}
					}
					if (c3.equals(c1) || c3.equals(c2)) {
						//Can't have two of the same card in our hand.
						continue;
					}
					for (int card4 = 1; card4 <= 52; card4++) {
						Card c4;
						if (heldIndices[3]) {
							c4 = cards[3];
						} else {
							c4 = new Card(card4);
							if (c4.equals(cards[0]) || c4.equals(cards[1]) || c4.equals(cards[2]) || c4.equals(cards[3]) || c4.equals(cards[4])) {
								//The new card can not be one of the cards we were originally dealt.
								continue;
							}
						}
						if (c4.equals(c1) || c4.equals(c2) || c4.equals(c3)) {
							//Can't have two of the same card in our hand.
							continue;
						}
						for (int card5 = 1; card5 <= 52; card5++) {
							Card c5;
							if (heldIndices[4]) {
								c5 = cards[4];
							} else {
								c5 = new Card(card5);
								if (c5.equals(cards[0]) || c5.equals(cards[1]) || c5.equals(cards[2]) || c5.equals(cards[3]) || c5.equals(cards[4])) {
									//The new card can not be one of the cards we were originally dealt.
									continue;
								}
							}
							if (c5.equals(c1) || c5.equals(c2) || c5.equals(c3) || c5.equals(c4)) {
								//Can't have two of the same card in our hand.
								continue;
							}
							ArrayList<Card> ac = new ArrayList<Card>();
							ac.add(c1);
							ac.add(c2);
							ac.add(c3);
							ac.add(c4);
							ac.add(c5);
							VideoPokerHand vph = new VideoPokerHand(ac);
							String normalizedHand = vph.getNormalizedHand();
							int rank = results.getResult(normalizedHand);
							cbr.incrementCounterForRank(rank);
							iterationCount++;
							if (iterationCount >= progressIncrement && iterationCount % progressIncrement == 0) {
								System.out.println(iterationCount + " iterations so far.");
							}
							
							if (heldIndices[4]) {
								break;  // Only go through one iteration of this card since it's fixed to a specific card.
							}
						}
						if (heldIndices[3]) {
							break;  // Only go through one iteration of this card since it's fixed to a specific card.
						}
					}
					if (heldIndices[2]) {
						break;  // Only go through one iteration of this card since it's fixed to a specific card.
					}
				}
				if (heldIndices[1]) {
					break;  // Only go through one iteration of this card since it's fixed to a specific card.
				}
			}
			if (heldIndices[0]) {
				break;  // Only go through one iteration of this card since it's fixed to a specific card.
			}
		}
		System.out.println("Completed calculations, iteration total = " + iterationCount + ", results:");
		System.out.println(cbr.toString());
		return cbr;
	}
	

}
