package domain;

import org.junit.Test;

import junit.framework.TestCase;

public class CardTest extends TestCase {

	@Test
	public void testToString() {
		Card c1 = new Card(Card.HEARTS, Card.ACE);
		String str = c1.toString();
		String shortStr = c1.shortString();
		assertTrue("Bad string found", str.equals("Ace of hearts"));
		assertTrue("Bad short string found", shortStr.equals("Ah"));
		
		c1 = new Card(Card.CLUBS, Card.DEUCE);
		str = c1.toString();
		shortStr = c1.shortString();
		assertTrue("Bad string found", str.equals("Deuce of clubs"));
		assertTrue("Bad short string found", shortStr.equals("2c"));
		
		c1 = new Card(Card.SPADES, Card.JACK);
		str = c1.toString();
		shortStr = c1.shortString();
		assertTrue("Bad string found", str.equals("Jack of spades"));
		assertTrue("Bad short string found", shortStr.equals("Js"));
		
		c1 = new Card(Card.DIAMONDS, Card.FOUR);
		str = c1.toString();
		shortStr = c1.shortString();
		assertTrue("Bad string found", str.equals("Four of diamonds"));
		assertTrue("Bad short string found", shortStr.equals("4d"));
	}
}
