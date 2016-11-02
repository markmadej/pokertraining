package domain;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.ArrayList;

/*
 * Given the available combos and paytable, determine the best choice.
 */
public class VPDecision {
	
	private ArrayList<Double> sortedAverageValues;
	private ArrayList<ComboRank> sortedComboRanks;
	
	public VPDecision() {
		
	}
	
	public void calculateBest(ArrayList<ComboRank> comboRanks, Paytable p) {
		sortedAverageValues = new ArrayList<Double>();
		sortedComboRanks = new ArrayList<ComboRank>();
		
		//Calculate the average value for each combo.
		for (ComboRank cr : comboRanks) {
			BigDecimal total = BigDecimal.valueOf(0);
			BigDecimal divisor = BigDecimal.valueOf(0);
			for (int rank = VideoPokerHand.MIN_RANK_INTEGER; rank <= VideoPokerHand.MAX_RANK_INTEGER; rank++) {
				BigDecimal rankCount = BigDecimal.valueOf(cr.getRankTotal(rank));
				BigDecimal rankPayout = BigDecimal.valueOf(p.payoutForRank(rank));
				BigDecimal newRankValue = rankPayout.multiply(rankCount);
				total = total.add(newRankValue);
				divisor = divisor.add(rankCount);
			}
			
			if (divisor.doubleValue() > 0) {
				total = total.divide(divisor, MathContext.DECIMAL128);
			} else if (divisor.doubleValue() == 0) {
				// This is fine unless total > 0, then that is weird.
				if (total.doubleValue() > 0) {
					throw new RuntimeException("The total should not be nonzero if the divisor is zero.  That's just weird.");
				}
			}
			//Put this in the proper place in the ArrayLists.

			boolean addedYet = false;
			for (int i = 0; i < sortedAverageValues.size(); i++) {
				if (total.doubleValue() > sortedAverageValues.get(i)) {
					//New highest value.  Shift everything down.
					sortedAverageValues.add(i, total.doubleValue());
					sortedComboRanks.add(i, cr);
					addedYet = true;
				}
				if (addedYet == true) {
					break;
				}
			}
			if (addedYet == false) {
				//Either the ArrayLists were empty or this needs to be added at the end.
				sortedAverageValues.add(total.doubleValue());
				sortedComboRanks.add(cr);
			}
		}
		printReport();
		
	}
	
	public void printReport() {
		System.out.println("*** VP DECISION REPORT ***");
		if (sortedComboRanks.get(0).getCards().length == 0) {
			System.out.println("* NONE");
		}
		for (Card c : sortedComboRanks.get(0).getCards()) {
			System.out.println("* " + c);
		}
		for (int i = 0; i < sortedComboRanks.size(); i++) {
			double value = sortedAverageValues.get(i);
			ComboRank cbr = sortedComboRanks.get(i);
			System.out.print((i+1) + " : ");
			boolean[] indices = cbr.getHeldIndices();
			Card[] cards = cbr.getCards();
			for (int j = 0; j < 5; j++) {
				if (indices[j] == true) {
					System.out.print(" " + cards[j].shortString());
				}
			}
			System.out.println(" : Average value : " + value);
		}
	}

	public ArrayList<Double> getSortedAverageValues() {
		return sortedAverageValues;
	}

	public ArrayList<ComboRank> getSortedComboRanks() {
		return sortedComboRanks;
	}
}
