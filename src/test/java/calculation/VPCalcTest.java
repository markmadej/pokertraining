package calculation;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

import domain.Card;

public class VPCalcTest {

	@Test
	public void testGetPossibleRemainingCards() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.ACE);
		cards[1] = new Card(Card.CLUBS, Card.JACK);
		cards[2] = new Card(Card.HEARTS, Card.ACE);
		cards[3] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[4] = new Card(Card.SPADES, Card.SEVEN);
		
		int expectedRemaining = 52 - cards.length;
		
		VPCalc vpc = new VPCalc(false);
		
		ArrayList<Card> remainingCards = vpc.getPossibleRemainingCards(cards);
		for (Card c : remainingCards) {
			for (int i = 0; i < cards.length; i++) {
				assertTrue("Error - dead card found in resulting list.", !c.equals(cards[i]));
			}
		}
		
		assertTrue(remainingCards.size() + " remained instead of the expected " + expectedRemaining, remainingCards.size() == expectedRemaining);
		
	}
	
	@Test
	public void testGetPossibleRemainingCards2() {
		Card[] cards = new Card[10];
		cards[0] = new Card(Card.DIAMONDS, Card.ACE);
		cards[1] = new Card(Card.CLUBS, Card.JACK);
		cards[2] = new Card(Card.HEARTS, Card.ACE);
		cards[3] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[4] = new Card(Card.SPADES, Card.SEVEN);
		cards[5] = new Card(Card.CLUBS, Card.ACE);
		cards[6] = new Card(Card.HEARTS, Card.JACK);
		cards[7] = new Card(Card.HEARTS, Card.TREY);
		cards[8] = new Card(Card.DIAMONDS, Card.TREY);
		cards[9] = new Card(Card.SPADES, Card.TREY);
		
		int expectedRemaining = 52 - cards.length;
		
		VPCalc vpc = new VPCalc(false);
		
		ArrayList<Card> remainingCards = vpc.getPossibleRemainingCards(cards);
		for (Card c : remainingCards) {
			for (int i = 0; i < cards.length; i++) {
				assertTrue("Error - dead card found in resulting list.", !c.equals(cards[i]));
			}
		}
		
		assertTrue(remainingCards.size() + " remained instead of the expected " + expectedRemaining, remainingCards.size() == expectedRemaining);
		
	}

}
