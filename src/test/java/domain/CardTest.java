package domain;

import org.junit.Test;

import junit.framework.TestCase;

public class CardTest extends TestCase {

	@Test
	public void testToString() {
		Card c1 = new Card(Card.HEARTS, Card.ACE);
		String str = c1.toString();
		String shortStr = c1.shortString();
		String shortDenominationString = c1.shortDenominationString();
		assertTrue("Bad string found", str.equals("Ace of hearts"));
		assertTrue("Bad short string found", shortStr.equals("Ah"));
		assertTrue("Bad short denom string found", shortDenominationString.equals("A"));
		
		c1 = new Card(Card.CLUBS, Card.DEUCE);
		str = c1.toString();
		shortStr = c1.shortString();
		shortDenominationString = c1.shortDenominationString();
		assertTrue("Bad string found", str.equals("Deuce of clubs"));
		assertTrue("Bad short string found", shortStr.equals("2c"));
		assertTrue("Bad short denom string found", shortDenominationString.equals("2"));
		
		c1 = new Card(Card.SPADES, Card.JACK);
		str = c1.toString();
		shortStr = c1.shortString();
		shortDenominationString = c1.shortDenominationString();
		assertTrue("Bad string found", str.equals("Jack of spades"));
		assertTrue("Bad short string found", shortStr.equals("Js"));
		assertTrue("Bad short denom string found", shortDenominationString.equals("J"));
		
		c1 = new Card(Card.DIAMONDS, Card.FOUR);
		str = c1.toString();
		shortStr = c1.shortString();
		shortDenominationString = c1.shortDenominationString();
		assertTrue("Bad string found", str.equals("Four of diamonds"));
		assertTrue("Bad short string found", shortStr.equals("4d"));
		assertTrue("Bad short denom string found", shortDenominationString.equals("4"));
	}
}
