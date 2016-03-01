package domain;

import static org.junit.Assert.*;

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
			}
		} catch (Exception e) {
			// This gets thrown when the cards run out - ignore it
		}
		assertTrue("Did not create a 52 card deck.", ct == 52);
	}

}
