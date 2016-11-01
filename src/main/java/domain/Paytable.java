package domain;

import java.math.BigDecimal;

/*
 * A paytable represents a particular video poker game's payouts.
 */
public class Paytable {

	private String name;
	private BigDecimal[] payouts = new BigDecimal[VideoPokerHand.MAX_RANK_INTEGER];
	private int coinsIn;  //Number of coins played, typically 5 is the max but not always
	
	
	public BigDecimal payoutForRank(int rank) {
		if (rank < VideoPokerHand.MIN_RANK_INTEGER || rank > VideoPokerHand.MAX_RANK_INTEGER) {
			throw new RuntimeException("Invalid rank for payout : " + rank);
		}
		return payouts[rank];
	}
	
}
