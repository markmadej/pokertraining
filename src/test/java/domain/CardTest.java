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
	
	@Test
	public void testShortStringCreation() {
		String[] cardStrings = {"Ac", "5d", "4s", "Jh", "Td"};
		int[] expectedDenoms = {Card.ACE, Card.FIVE, Card.FOUR, Card.JACK, Card.TEN};
		int[] expectedSuits = {Card.CLUBS, Card.DIAMONDS, Card.SPADES, Card.HEARTS, Card.DIAMONDS};
		for (int i = 0; i < cardStrings.length; i++) {
			Card c = new Card(cardStrings[i]);
			assertTrue("Suit didn't match for " + cardStrings[i] + " : " + c, c.getSuit() == expectedSuits[i]);
			assertTrue("Denomination didn't match for " + cardStrings[i] + " : " + c, c.getDenomination() == expectedDenoms[i]);
		}
	}
	
	@Test
	public void testIntegerConstructor() {
		int[] cardNumbers = {1, 13, 5, 10, 15, 25, 26, 39, 41, 52};
		int[] expectedDenoms = {Card.DEUCE, Card.ACE, Card.SIX, Card.JACK, Card.TREY, Card.KING, Card.ACE, Card.ACE, Card.TREY, Card.ACE};
		int[] expectedSuits = {Card.CLUBS, Card.CLUBS, Card.CLUBS, Card.CLUBS, Card.DIAMONDS, Card.DIAMONDS, Card.DIAMONDS, Card.HEARTS, Card.SPADES, Card.SPADES};
		for (int i = 0; i < cardNumbers.length; i++) {
			Card c = new Card(cardNumbers[i]);
			assertTrue("Suit didn't match for " + cardNumbers[i] + " : " + c, c.getSuit() == expectedSuits[i]);
			assertTrue("Denomination didn't match for " + cardNumbers[i] + " : " + c, c.getDenomination() == expectedDenoms[i]);
		}
		
	}
}
