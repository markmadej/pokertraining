package domain;

import java.util.ArrayList;

/**
 * This class represents a range of hands.
 * You can create a range via:
 * - a set of PokerHand objects
 * - a traditional poker writing standard of things
 *   like 77+, AJs+, etc.
 * - a top % of hands (ie. 2% would be AA, KK, AKo, AKs)
 * @author marmadej
 *
 */
public class PokerHandRange {
	private ArrayList<PokerHand> pokerHands = new ArrayList<PokerHand>();
	
	/*
	 * Thoughts
	 * 
	 * Should PokerHand be broken down, or shoudl some HoleCards class be created
	 * where this is just a set of hole cards?  Seems better
	 */
	
}
