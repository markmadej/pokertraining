package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class ComboRankTest {

	@Test
	public void testInitialization() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.FOUR);
		cards[1] = new Card(Card.SPADES, Card.KING);
		cards[2] = new Card(Card.DIAMONDS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.KING);
		cards[4] = new Card(Card.CLUBS, Card.KING);
		
		boolean[] held = {false, true, false, true, false};
		
		ComboRank cbr = new ComboRank(cards, held);
		
		assertTrue(cbr.getTotalIterations() == 0);
		
		boolean[] heldResult = cbr.getHeldIndices();
		for (int i = 0; i < heldResult.length; i++) {
			assertTrue(heldResult[i] == held[i]);
		}
		
		cbr.incrementCounterForRank(VideoPokerHand.TWO_PAIR);
		cbr.incrementCounterForRank(VideoPokerHand.TWO_PAIR);
		cbr.incrementCounterForRank(VideoPokerHand.FOUR_DEUCES);
		cbr.incrementCounterForRank(VideoPokerHand.TWO_PAIR);
		
		assertTrue(cbr.getTotalIterations() == 4);
		assertTrue(cbr.getRankTotal(VideoPokerHand.TWO_PAIR) == 3);
		assertTrue(cbr.getRankTotal(VideoPokerHand.FOUR_DEUCES) == 1);
	}

}
