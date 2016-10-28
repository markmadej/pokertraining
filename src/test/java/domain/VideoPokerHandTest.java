package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import junit.framework.Assert;

public class VideoPokerHandTest {

	@Test
	public void testNormalize1() {
		// Test hand : AcJcAdJd5s
		// Rearranged : AcJcAdJc5s (same)
		// Result with numbers : A1J1A2J253
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.CLUBS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.DIAMONDS, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.FIVE));

		VideoPokerHand vph = new VideoPokerHand(cards);
		String normalizedHand = vph.getNormalizedHand();
		String expectedValue = "A1J1A2J253";
		assertTrue(normalizedHand + " did not match the expected value of " + expectedValue, normalizedHand.equals(expectedValue));
	}
	
	@Test
	public void testNormalize2() {
		// Test hand : Ac5d7d2s2c
		// Rearranged : Ac2c7d5d2s
		// Result with numbers : A121725223
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.CLUBS, Card.ACE));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.DIAMONDS, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.DEUCE));
		cards.add(new Card(Card.CLUBS, Card.DEUCE));

		VideoPokerHand vph = new VideoPokerHand(cards);
		String normalizedHand = vph.getNormalizedHand();
		String expectedValue = "A121725223";
		assertTrue(normalizedHand + " did not match the expected value of " + expectedValue, normalizedHand.equals(expectedValue));
	}

}
