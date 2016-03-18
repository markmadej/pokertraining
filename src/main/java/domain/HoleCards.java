package domain;

import java.util.ArrayList;

/**
 * Simple class to represent 2 hole cards.
 * 
 * @author marmadej
 *
 */
public class HoleCards {
	private ArrayList<Card> holeCards;
	
	public HoleCards(ArrayList<Card> holeCards) {
		if (holeCards.size() != 2) {
			throw new RuntimeException("Number of cards other than 2 found - error!");
		}
		this.holeCards = holeCards;
	}
}
