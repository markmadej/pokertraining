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
	
	/**
	 * This constructor takes a human readable range.  The range follows these rules:
	 * 
	 * Two of the same card (22, 33, etc) is a pair.  A plus sign after it means
	 *   every pair higher than that pair.
	 * A card followed by a suit character (c/d/h/s) means the card is that suit.  "x"
	 *   means any suit.  If there are two cards followed by a "s" (and the first card
	 *   is not followed by a suit) then the "s" means "suited".  
	 * AJ means any ace/jack combination.  AJs means all 4 suited combinations.
	 * AcJs means ace of clubs, jack of spades.  AxJs means any ace with the jack of spades.
	 * QJ+ means any queen/jack combination as well as any combination of higher cards.  
	 *   So QJ+ means: QJ, KJ, AJ, AQ, and AK.
	 * Finally, different ranges can be separated by commas.  For example : 22+,AQ+
	 *   
	 * @param range
	 */
	public HoleCardRange(String range) {
		range.replace(" ", "");  //Remove spaces
		range = range.toUpperCase();  //Simplify our logic by knowing everything is uppercase
		String[] ranges = range.split(",");
		for (String rng : ranges) {
			parseRange(rng);
		}
	}
	
	private void parseRange(String range) {
		
	}
	
	private boolean isPlus(String range) {
		return range.endsWith("+");
	}
	
}
