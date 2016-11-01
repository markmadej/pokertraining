package domain;

import java.util.Arrays;

/* 
 * This class contains the calculated data for a single specific video
 * poker hand and held cards.  It contains the number of each possible rank
 * combination that you will get overall if you hold the given cards with these
 * starting cards.  Those numbers can later be extrapolated to give us the 
 * best possible cards to hold based on the paytable.
 */
public class ComboRank {
	private Card[] cards;
	private boolean[] heldIndices;
	private int[] rankResults;
	
	public ComboRank(Card[] cards, boolean[] heldCards) {
		initializeArrays();
		
		if (cards.length < 5) {
			throw new RuntimeException("Can not create a ComboRank with only " + cards.length + " cards.");
		}
		if (heldCards.length < 5) {
			throw new RuntimeException("Can not create a ComboRank with only " + heldCards.length + " held cards.");
		}
		for (int i = 0; i < 5; i++) {
			this.cards[i] = cards[i];
			this.heldIndices[i] = heldCards[i];
		}
		
	}
	
	private void initializeArrays() {
		cards = new Card[5];
		heldIndices = new boolean[5];
		Arrays.fill(heldIndices, false);
		rankResults = new int[15];
		Arrays.fill(rankResults, 0);
	}
	
	public void incrementCounterForRank(int rank) {
		// rank = the constant values in VideoPokerHand for different results (jacks or better, etc)
		if (rank < VideoPokerHand.MIN_RANK_INTEGER || rank > VideoPokerHand.MAX_RANK_INTEGER) {
			throw new RuntimeException("Tried to increment rank counter for invalid rank : " + rank);
		}
		rankResults[rank] = rankResults[rank] + 1;
	}

	public Card[] getCards() {
		return cards;
	}

	public boolean[] getHeldIndices() {
		return heldIndices;
	}

	public int[] getRankResults() {
		return rankResults;
	}
	
	public int getRankTotal(int rank) {
		if (rank < VideoPokerHand.MIN_RANK_INTEGER || rank > VideoPokerHand.MAX_RANK_INTEGER) {
			throw new RuntimeException("Tried to retrieve rank counter for invalid rank : " + rank);
		}
		return rankResults[rank];
	}
	
	/*
	 * This is mostly used in unit tests to determine how many combinations have been
	 * calculated.  It may have other uses though.
	 */
	public int getTotalIterations() {
		int total = 0;
		for (int i = 0; i < rankResults.length; i++) {
			total += rankResults[i];
		}
		return total;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("ComboRank:{\n");
		for (int i = 0; i < 5; i++) {
			sb.append(cards[i].toString());
			if (heldIndices[i] == true) {
				sb.append(" - HELD");
			}
			sb.append("\n");
		}

		VideoPokerHand vph = new VideoPokerHand();
		for (int i = VideoPokerHand.MIN_RANK_INTEGER; i <= VideoPokerHand.MAX_RANK_INTEGER; i++) {
			sb.append("\n");
			sb.append(vph.getHumanReadableRank(i));
			sb.append(":");
			sb.append(rankResults[i]);
		}
		return sb.toString();
	}
}
