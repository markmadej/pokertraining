package calculation;
import java.util.ArrayList;

import domain.Card;
import domain.ComboRank;
import domain.FiveCardNormalizedResult;
import domain.Paytable;
import domain.VPDecision;
import domain.VideoPokerHand;

// I'll use this to test out some things and build the functions I need.
public class VPCalc {
	
	private FiveCardNormalizedResult results;
	
	public static void main(String[] args) {
		VPCalc vpc = new VPCalc();

		VideoPokerHand vph = new VideoPokerHand("AcAdKcQcJs");
		ArrayList<ComboRank> solution = vpc.solveHandWithAllCombinations(vph);
		System.out.println("Finished solveHandWithAllCombinations");
		VPDecision vpd = new VPDecision();
		vpd.calculateBest(solution, new Paytable());
		
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
	
	public ArrayList<Card> getPossibleRemainingCards(ArrayList<Card> deadCards) {
		return getPossibleRemainingCards(deadCards, null);
	}
	/*
	 * Return an ArrayList<Card> structure with all cards that are not in the given list.
	 * If forceSingleCard != null, the only possibility is an ArrayList<Card> with that one card.
	 */
	public ArrayList<Card> getPossibleRemainingCards(ArrayList<Card> deadCards, Card forceSingleCard) {
		
		ArrayList<Card> remainingCards = new ArrayList<Card>(forceSingleCard == null ? 52 - deadCards.size() : 1);
		if (forceSingleCard != null) {
			remainingCards.add(forceSingleCard);
			return remainingCards;
		}
		
		for (int suit = Card.CLUBS; suit <= Card.SPADES; suit++) {
			for (int denom = Card.DEUCE; denom <= Card.ACE; denom++) {
				boolean foundInDeadList = false;
				for (int i = 0; i < deadCards.size(); i++) {
					if (deadCards.get(i).getSuit() == suit && deadCards.get(i).getDenomination() == denom) {
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

		ArrayList<Card> deadCards = new ArrayList<Card>(5);
		for (int i = 0; i < cards.length; i++) {
			deadCards.add(cards[i]);
		}
		
		for (Card c1 : getPossibleRemainingCards(deadCards, heldIndices[0] == true ? cards[0] : null)) {
			deadCards.add(c1);
			for (Card c2 : getPossibleRemainingCards(deadCards, heldIndices[1] == true ? cards[1] : null)) {
				deadCards.add(c2);
				for (Card c3 : getPossibleRemainingCards(deadCards, heldIndices[2] == true ? cards[2] : null)) {
					deadCards.add(c3);
					for (Card c4 : getPossibleRemainingCards(deadCards, heldIndices[3] == true ? cards[3] : null)) {
						deadCards.add(c4);
						for (Card c5 : getPossibleRemainingCards(deadCards, heldIndices[4] == true ? cards[4] : null)) {
							ArrayList<Card> ac = new ArrayList<Card>(5);
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
						}
						deadCards.remove(c4);
					}
					deadCards.remove(c3);
				}
				deadCards.remove(c2);
			}
			deadCards.remove(c1);
		}

		System.out.println("Completed calculations, iteration total = " + iterationCount + ".");
		return cbr;
	}
	

}
