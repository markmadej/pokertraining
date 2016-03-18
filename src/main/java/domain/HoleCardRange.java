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
public class HoleCardRange {
	private ArrayList<HoleCards> holeCardCombos = new ArrayList<HoleCards>();
	
	public HoleCardRange(ArrayList<HoleCards> hands) {
		
	}
	
}
