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
		
	}

}
