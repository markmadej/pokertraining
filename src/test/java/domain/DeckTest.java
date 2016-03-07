package domain;

import static org.junit.Assert.*;

import java.util.HashSet;

import org.junit.Test;

public class DeckTest {

	@Test
	public void test52CardDeck() {
		Deck d = new Deck();
		int ct = 0;
		try {
			Card c = d.dealCard();
			while (c != null) {
				ct++;
				c = d.dealCard();
			}
		} catch (Exception e) {
			// This gets thrown when the cards run out - ignore it
		}
		assertTrue("Did not create a 52 card deck - count was " + ct, ct == 52);
	}

	@Test
	public void testAllCardsAreUnique() {
		Deck d = new Deck();
		try {
			Card c = d.dealCard();
			HashSet<Card> set = new HashSet<Card>();
			set.add(c);
			while (c != null) {
				c = d.dealCard();
				if (set.contains(c)) {
					fail("Deck contains two copies of " + c.toString());
				} else {
					set.add(c);
				}
			}
		} catch (Exception e) {
			// This gets thrown when the cards run out - ignore it
		}
	}	
	
}
