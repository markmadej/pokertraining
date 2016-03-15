package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PokerHandTest {

	@Test
	public void testStraightFlush() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.SPADES, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.TEN));
		cards.add(new Card(Card.CLUBS, Card.QUEEN));
		cards.add(new Card(Card.SPADES, Card.QUEEN));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a straight flush.",
				ph.getHandRanking() == PokerHand.STRAIGHT_FLUSH);
	}

	@Test
	public void testNegativeStraightFlush() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.TEN));
		cards.add(new Card(Card.CLUBS, Card.QUEEN));
		cards.add(new Card(Card.SPADES, Card.QUEEN));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Found a straight flush, but there isn't one.",
				ph.getHandRanking() != PokerHand.STRAIGHT_FLUSH);
	}	
	
	@Test
	public void testStraightFlushWheel() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.TREY));
		cards.add(new Card(Card.DIAMONDS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.ACE));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find ze wheel flush.",
				ph.getHandRanking() == PokerHand.STRAIGHT_FLUSH);
	}
	
	@Test
	public void testFourOfAKind() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.TREY));
		cards.add(new Card(Card.DIAMONDS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.SPADES, Card.FOUR));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find 4 4s.",
				ph.getHandRanking() == PokerHand.FOUR_OF_A_KIND);
	}
	
	@Test
	public void testFourOfAKindNegative() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.TREY));
		cards.add(new Card(Card.DIAMONDS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.SPADES, Card.FOUR));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Found 4 of a kind but it doesn't really exist.",
				ph.getHandRanking() != PokerHand.FOUR_OF_A_KIND);
	}
	
	@Test
	public void testFullHouseWith3CardsHigherThanTwo() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.SPADES, Card.TREY));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.TREY));
		cards.add(new Card(Card.SPADES, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a boat.",
				ph.getHandRanking() == PokerHand.FULL_HOUSE);
	}
	
	@Test
	public void testFullHouseWith2CardsHigherThanThree() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.QUEEN));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		cards.add(new Card(Card.CLUBS, Card.SEVEN));
		cards.add(new Card(Card.DIAMONDS, Card.QUEEN));
		cards.add(new Card(Card.SPADES, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a boat.",
				ph.getHandRanking() == PokerHand.FULL_HOUSE);
	}
	
	@Test
	public void testCardMatrix() {
		// Make sure the matrix is being populated properly.
		
		// Probably need to use the introspection API to make the card matrix data
		// publicly available for this test.  Need to research.
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.HEARTS, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.TREY));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		
		PokerHand ph = new PokerHand(cards);
		
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.JACK) == 1);
		assertTrue(ph.getCardMatrixValue(Card.SPADES, Card.TREY) == 1);
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.DEUCE) == 1);
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.FOUR) == 1);
		assertTrue(ph.getCardMatrixValue(Card.CLUBS, Card.FOUR) == 1);
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.FIVE) == 1);
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.ACE) == 1);
		
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.EIGHT) == 0);
		assertTrue(ph.getCardMatrixValue(Card.CLUBS, Card.SEVEN) == 0);
		assertTrue(ph.getCardMatrixValue(Card.HEARTS, Card.SIX) == 0);
		assertTrue(ph.getCardMatrixValue(Card.SPADES, Card.ACE) == 0);
		
	}
	
	@Test
	public void testFlush() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.HEARTS, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.TREY));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a flush.",
				ph.getHandRanking() == PokerHand.FLUSH);
	}
	
	@Test
	public void testWheel() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.SPADES, Card.TREY));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.QUEEN));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a straight (wheel).",
				ph.getHandRanking() == PokerHand.STRAIGHT);
	}
	
	@Test
	public void testStraight() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.SEVEN));
		cards.add(new Card(Card.HEARTS, Card.SIX));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a straight.",
				ph.getHandRanking() == PokerHand.STRAIGHT);
	}
	
	@Test
	public void testTrips() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.EIGHT));
		cards.add(new Card(Card.DIAMONDS, Card.EIGHT));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.SEVEN));
		cards.add(new Card(Card.HEARTS, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find three of a kind.",
				ph.getHandRanking() == PokerHand.THREE_OF_A_KIND);
	}
	
	@Test
	public void testTwoPair() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.EIGHT));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.SEVEN));
		cards.add(new Card(Card.HEARTS, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find two pair.",
				ph.getHandRanking() == PokerHand.TWO_PAIR);
	}
	
	@Test
	public void testOnePair() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.NINE));
		cards.add(new Card(Card.CLUBS, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.EIGHT));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.QUEEN));
		cards.add(new Card(Card.HEARTS, Card.SEVEN));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find one pair.",
				ph.getHandRanking() == PokerHand.ONE_PAIR);
	}
	
	@Test
	public void testHighCard() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.NINE));
		cards.add(new Card(Card.CLUBS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.EIGHT));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.TREY));
		cards.add(new Card(Card.HEARTS, Card.SEVEN));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a high card.",
				ph.getHandRanking() == PokerHand.HIGH_CARD);
	}
	

}
