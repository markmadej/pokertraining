package domain;

/*
 * Each fully calculated video poker hand (starting 5 cards) will result in the following data:
 * - Starting 5 card hand, in order
 * - Normalized hand (to be used in a lookup later)
 * - For each of the 32 possible card holding combinations:
 * -- Indices of cards that were held (referencing the normalized hand I think)
 * -- Number of times each specific rank occurred (jacks or better, four aces with kicker, etc)
 */
public class VideoPokerHandStats {
	
	private Card[] originalCards;
	private String normalizedHand;
	
	
}
