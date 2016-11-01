package domain;

import java.math.BigDecimal;

/*
 * A paytable represents a particular video poker game's payouts.
 */
public class Paytable {

	private String name;
	private double[] payouts = new double[VideoPokerHand.MAX_RANK_INTEGER + 1];
	private int coinsIn;  //Number of coins played, typically 5 is the max but not always
	
	public Paytable() {
		//For now initialize with data from Double Double Bonus Poker.  This will be made more flexible later.
		payouts[VideoPokerHand.NOTHING] = 0;
		payouts[VideoPokerHand.JACKS_OR_BETTER] = 5;
		payouts[VideoPokerHand.TWO_PAIR] = 5;
		payouts[VideoPokerHand.THREE_OF_A_KIND] = 15;
		payouts[VideoPokerHand.STRAIGHT] = 20;
		payouts[VideoPokerHand.FLUSH] = 25;
		payouts[VideoPokerHand.FULL_HOUSE] = 40;
		payouts[VideoPokerHand.FOUR_FIVES] = 250;
		payouts[VideoPokerHand.FOUR_DEUCES] = 400;
		payouts[VideoPokerHand.FOUR_DEUCES_KICKER] = 800;
		payouts[VideoPokerHand.FOUR_ACES] = 1000;
		payouts[VideoPokerHand.FOUR_ACES_KICKER] = 2000;
		payouts[VideoPokerHand.STRAIGHT_FLUSH] = 250;
		payouts[VideoPokerHand.ROYAL_FLUSH] = 4000;

	}
	
	public double payoutForRank(int rank) {
		if (rank < VideoPokerHand.MIN_RANK_INTEGER || rank > VideoPokerHand.MAX_RANK_INTEGER) {
			throw new RuntimeException("Invalid rank for payout : " + rank);
		}
		return payouts[rank];
	}
	
}
