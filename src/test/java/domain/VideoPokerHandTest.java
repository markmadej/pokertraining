package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class VideoPokerHandTest {

	@Test
	public void testSort1AlreadySorted() {
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
		
		ArrayList<Card> sortedCards = vph.getSortedCards();
		assertTrue("5 cards were not found after sorting.", sortedCards.size() == 5);
		for (int i = 0; i < 5; i++) {
			assertTrue("Cards did not match at position " + i + "\ncards:" + cards + "\nsortedCards:" + sortedCards, 
					cards.get(i).getSuit() == sortedCards.get(i).getSuit() && 
					cards.get(i).getDenomination() == sortedCards.get(i).getDenomination());
		}
	}
	
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

		VideoPokerHand vph = new VideoPokerHand(cards); //The cards passed in here are irrelevant - just needed a constructor to use
		String normalizedHand = vph.createNormalizedString(cards);
		
		assertTrue(normalizedHand + " did not match expected value of A1J1A2J253.", 
				normalizedHand.equals("A1J1A2J253"));
	}
	
	@Test
	public void testSort2() {
		// Test hand : 7s2s5h4c8d
		// Rearranged : 7s2s8d5h4c
		// Result with numbers : 7121825344
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.EIGHT));

		VideoPokerHand vph = new VideoPokerHand(cards);
		
		ArrayList<Card> expectedCards = new ArrayList<Card>();
		expectedCards.add(new Card(Card.SPADES, Card.SEVEN));
		expectedCards.add(new Card(Card.SPADES, Card.DEUCE));
		expectedCards.add(new Card(Card.DIAMONDS, Card.EIGHT));
		expectedCards.add(new Card(Card.HEARTS, Card.FIVE));
		expectedCards.add(new Card(Card.CLUBS, Card.FOUR));
		
		ArrayList<Card> sortedCards = vph.getSortedCards();
		assertTrue("5 cards were not found after sorting.", sortedCards.size() == 5);
		for (int i = 0; i < 5; i++) {
			assertTrue("Cards did not match at position " + i + "\nexpectedCards:" + expectedCards + "\nsortedCards:" + sortedCards, 
					expectedCards.get(i).getSuit() == sortedCards.get(i).getSuit() && 
					expectedCards.get(i).getDenomination() == sortedCards.get(i).getDenomination());
		}
	}
	
	@Test
	public void testNormalize2() {
		// Test hand : 7s2s5h4c8d
		// Rearranged : 7s2s8d5h4c
		// Result with numbers : 7121825344
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.DEUCE));
		cards.add(new Card(Card.DIAMONDS, Card.EIGHT));
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.FOUR));

		VideoPokerHand vph = new VideoPokerHand(cards); //The cards passed in here are irrelevant - just needed a constructor to use
		String normalizedHand = vph.createNormalizedString(cards);
		
		assertTrue(normalizedHand + " did not match expected value of 7121825344.", 
				normalizedHand.equals("7121825344"));
	}

}
