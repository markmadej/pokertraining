package calculation;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import org.junit.Test;

import domain.Card;
import domain.ComboRank;
import domain.VideoPokerHand;

public class VPCalcTest {
	
	@Test
	public void testSolveSingleHandIterations() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.JACK);
		cards[1] = new Card(Card.HEARTS, Card.ACE);
		cards[2] = new Card(Card.HEARTS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.QUEEN);
		cards[4] = new Card(Card.HEARTS, Card.TEN);
		
		boolean[] heldIndices = {true, true, true, true, true};
		
		ComboRank cbr = new ComboRank(cards, heldIndices);
		
		VPCalc vpc = new VPCalc();
		cbr = vpc.solveSingleHand(cbr);
		assertTrue(cbr.getTotalIterations() == 1);
		assertTrue(cbr.getRankResults()[VideoPokerHand.ROYAL_FLUSH] == 1);
	}
	
	@Test
	public void testSolveSingleHandIterationsOneCardNotHeld() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.JACK);
		cards[1] = new Card(Card.HEARTS, Card.ACE);
		cards[2] = new Card(Card.HEARTS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.QUEEN);
		cards[4] = new Card(Card.HEARTS, Card.TEN);
		
		boolean[] heldIndices = {true, true, true, true, false};
		
		ComboRank cbr = new ComboRank(cards, heldIndices);
		
		VPCalc vpc = new VPCalc();
		cbr = vpc.solveSingleHand(cbr);
		assertTrue(cbr.getTotalIterations() == 47);
	}
	
	@Test
	public void testSolveSingleHandIterationsTwoCardsNotHeld() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.JACK);
		cards[1] = new Card(Card.HEARTS, Card.ACE);
		cards[2] = new Card(Card.HEARTS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.QUEEN);
		cards[4] = new Card(Card.HEARTS, Card.TEN);
		
		boolean[] heldIndices = {true, true, true, false, false};
		
		ComboRank cbr = new ComboRank(cards, heldIndices);
		
		VPCalc vpc = new VPCalc();
		cbr = vpc.solveSingleHand(cbr);
		assertTrue(cbr.getTotalIterations() == (47 * 46));
	}
	
	@Test
	public void testSolveSingleHandIterationsThreeCardsNotHeld() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.JACK);
		cards[1] = new Card(Card.HEARTS, Card.ACE);
		cards[2] = new Card(Card.HEARTS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.QUEEN);
		cards[4] = new Card(Card.HEARTS, Card.TEN);
		
		boolean[] heldIndices = {true, true, false, false, false};
		
		ComboRank cbr = new ComboRank(cards, heldIndices);
		
		VPCalc vpc = new VPCalc();
		cbr = vpc.solveSingleHand(cbr);
		assertTrue(cbr.getTotalIterations() == (47 * 46 * 45));
	}

	@Test
	public void testGetPossibleRemainingCards() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		
		int expectedRemaining = 52 - cards.size();
		
		VPCalc vpc = new VPCalc(false);
		
		ArrayList<Card> remainingCards = vpc.getPossibleRemainingCards(cards);
		for (Card c : remainingCards) {
			for (int i = 0; i < cards.size(); i++) {
				assertTrue("Error - dead card found in resulting list.", !c.equals(cards.get(i)));
			}
		}
		
		assertTrue(remainingCards.size() + " remained instead of the expected " + expectedRemaining, remainingCards.size() == expectedRemaining);
		
	}
	
	@Test
	public void testGetPossibleRemainingCards2() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		cards.add(new Card(Card.DIAMONDS, Card.FIVE));
		cards.add(new Card(Card.SPADES, Card.SEVEN));		
		cards.add(new Card(Card.CLUBS, Card.ACE));
		cards.add(new Card(Card.HEARTS, Card.JACK));
		cards.add(new Card(Card.HEARTS, Card.TREY));
		cards.add(new Card(Card.DIAMONDS, Card.TREY));
		cards.add(new Card(Card.SPADES, Card.TREY));
		
		int expectedRemaining = 52 - cards.size();
		
		VPCalc vpc = new VPCalc(false);
		
		ArrayList<Card> remainingCards = vpc.getPossibleRemainingCards(cards);
		for (Card c : remainingCards) {
			for (int i = 0; i < cards.size(); i++) {
				assertTrue("Error - dead card found in resulting list.", !c.equals(cards.get(i)));
			}
		}
		
		assertTrue(remainingCards.size() + " remained instead of the expected " + expectedRemaining, remainingCards.size() == expectedRemaining);
		
	}
	
	@Test
	public void testGetPossibleRemainingCardsWithForce1() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		
		Card forceCard = new Card(Card.SPADES, Card.KING);

		VPCalc vpc = new VPCalc(false);
		
		ArrayList<Card> remainingCards = vpc.getPossibleRemainingCards(cards, forceCard);
		assertTrue(remainingCards.size() == 1);
		Card returnCard = remainingCards.get(0);
		assertTrue(returnCard.equals(forceCard));
		
	}


}
