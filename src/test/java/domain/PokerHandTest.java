package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class PokerHandTest {

	@Test
	public void testStraightFlush() {
		ArrayList<Card> cards = new ArrayList();
		cards.add(new Card(Card.SPADES, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.SPADES, Card.DEUCE));
		cards.add(new Card(Card.SPADES, Card.NINE));
		cards.add(new Card(Card.SPADES, Card.TEN));
		cards.add(new Card(Card.CLUBS, Card.QUEEN));
		cards.add(new Card(Card.HEARTS, Card.QUEEN));
		
		PokerHand ph = new PokerHand(cards);
		assertTrue("Could not evaluate hand", ph.evaluateHand());
		
		assertTrue("Did not find a straight flush.",
				ph.getHandRanking() == PokerHand.STRAIGHT_FLUSH);
	}
	
	@Test
	public void testStraightFlushWheel() {
		ArrayList<Card> cards = new ArrayList();
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

}
