package domain;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class VideoPokerHandTest {

	@Test
	public void bestRankTestRoyal() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.JACK);
		cards[1] = new Card(Card.HEARTS, Card.ACE);
		cards[2] = new Card(Card.HEARTS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.QUEEN);
		cards[4] = new Card(Card.HEARTS, Card.TEN);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("A royal flush was not found : " + rank, rank == VideoPokerHand.ROYAL_FLUSH);
	}
	
	@Test
	public void testNormalizedRoyalString() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.CLUBS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.JACK));
		cards.add(new Card(Card.CLUBS, Card.TEN));
		cards.add(new Card(Card.CLUBS, Card.QUEEN));
		cards.add(new Card(Card.CLUBS, Card.KING));
		
		VideoPokerHand vph = new VideoPokerHand(cards);
		assertTrue("Normalized string was " + vph.getNormalizedHand(), vph.getNormalizedHand().equals("A1K1Q1J1T1"));
	}
	
	@Test
	public void bestRankTestFourKings() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.FOUR);
		cards[1] = new Card(Card.SPADES, Card.KING);
		cards[2] = new Card(Card.DIAMONDS, Card.KING);
		cards[3] = new Card(Card.HEARTS, Card.KING);
		cards[4] = new Card(Card.CLUBS, Card.KING);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Four kings was not found : " + rank, rank == VideoPokerHand.FOUR_FIVES);
	}
	
	@Test
	public void bestRankTestFourAcesNoKicker() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.ACE);
		cards[1] = new Card(Card.SPADES, Card.ACE);
		cards[2] = new Card(Card.DIAMONDS, Card.ACE);
		cards[3] = new Card(Card.HEARTS, Card.KING);
		cards[4] = new Card(Card.CLUBS, Card.ACE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Four aces was not found : " + rank, rank == VideoPokerHand.FOUR_ACES);
	}
	
	@Test
	public void bestRankTestFourAcesWithKicker() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.ACE);
		cards[1] = new Card(Card.SPADES, Card.ACE);
		cards[2] = new Card(Card.DIAMONDS, Card.ACE);
		cards[3] = new Card(Card.HEARTS, Card.FOUR);
		cards[4] = new Card(Card.CLUBS, Card.ACE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Four aces with kicker was not found : " + rank, rank == VideoPokerHand.FOUR_ACES_KICKER);
	}
	
	@Test
	public void bestRankTestFourDeucesNoKicker() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.SEVEN);
		cards[1] = new Card(Card.SPADES, Card.DEUCE);
		cards[2] = new Card(Card.DIAMONDS, Card.DEUCE);
		cards[3] = new Card(Card.HEARTS, Card.DEUCE);
		cards[4] = new Card(Card.CLUBS, Card.DEUCE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Four deuces was not found : " + rank, rank == VideoPokerHand.FOUR_DEUCES);
	}
	
	@Test
	public void bestRankTestFourDeucesWithKicker() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.HEARTS, Card.ACE);
		cards[1] = new Card(Card.SPADES, Card.DEUCE);
		cards[2] = new Card(Card.DIAMONDS, Card.DEUCE);
		cards[3] = new Card(Card.HEARTS, Card.DEUCE);
		cards[4] = new Card(Card.CLUBS, Card.DEUCE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Four deuces with kicker was not found : " + rank, rank == VideoPokerHand.FOUR_DEUCES_KICKER);
	}
	
	@Test
	public void bestRankTestStraightFlush() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FOUR);
		cards[1] = new Card(Card.DIAMONDS, Card.SEVEN);
		cards[2] = new Card(Card.DIAMONDS, Card.SIX);
		cards[3] = new Card(Card.DIAMONDS, Card.EIGHT);
		cards[4] = new Card(Card.DIAMONDS, Card.FIVE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Straight flush was not found : " + rank, rank == VideoPokerHand.STRAIGHT_FLUSH);
	}
	
	@Test
	public void bestRankTestFullHouse() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.EIGHT);
		cards[1] = new Card(Card.CLUBS, Card.FIVE);
		cards[2] = new Card(Card.CLUBS, Card.EIGHT);
		cards[3] = new Card(Card.SPADES, Card.EIGHT);
		cards[4] = new Card(Card.HEARTS, Card.FIVE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("A full house was not found : " + rank, rank == VideoPokerHand.FULL_HOUSE);
	}
	
	@Test
	public void bestRankTestStraight() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FOUR);
		cards[1] = new Card(Card.DIAMONDS, Card.SEVEN);
		cards[2] = new Card(Card.HEARTS, Card.SIX);
		cards[3] = new Card(Card.DIAMONDS, Card.EIGHT);
		cards[4] = new Card(Card.DIAMONDS, Card.FIVE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Straight was not found : " + rank, rank == VideoPokerHand.STRAIGHT);
	}
	
	@Test
	public void bestRankTestFlush() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FOUR);
		cards[1] = new Card(Card.DIAMONDS, Card.SEVEN);
		cards[2] = new Card(Card.DIAMONDS, Card.SIX);
		cards[3] = new Card(Card.DIAMONDS, Card.TEN);
		cards[4] = new Card(Card.DIAMONDS, Card.FIVE);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Flush was not found : " + rank, rank == VideoPokerHand.FLUSH);
	}
	
	@Test
	public void bestRankTestTwoPair() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[1] = new Card(Card.CLUBS, Card.JACK);
		cards[2] = new Card(Card.HEARTS, Card.FIVE);
		cards[3] = new Card(Card.DIAMONDS, Card.JACK);
		cards[4] = new Card(Card.SPADES, Card.SEVEN);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Two pair was not found : " + rank, rank == VideoPokerHand.TWO_PAIR);
	}
	
	@Test
	public void bestRankTestJacksOrBetter() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[1] = new Card(Card.CLUBS, Card.JACK);
		cards[2] = new Card(Card.HEARTS, Card.ACE);
		cards[3] = new Card(Card.DIAMONDS, Card.JACK);
		cards[4] = new Card(Card.SPADES, Card.SEVEN);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Jacks or better was not found : " + rank, rank == VideoPokerHand.JACKS_OR_BETTER);
	}

	@Test
	public void bestRankTestNothing() {
		Card[] cards = new Card[5];
		cards[0] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[1] = new Card(Card.CLUBS, Card.JACK);
		cards[2] = new Card(Card.HEARTS, Card.ACE);
		cards[3] = new Card(Card.DIAMONDS, Card.FIVE);
		cards[4] = new Card(Card.SPADES, Card.SEVEN);
		
		VideoPokerHand vph = new VideoPokerHand();
		int rank = vph.calculateBestRank(cards);
		assertTrue("Nothing was not found (irony much): " + rank, rank == VideoPokerHand.NOTHING);
	}
	
	@Test
	public void testNormalizedIndices1() {
		// Start : 5c8cAsAdJs
		// End :   AsJs8c5cAd
		// End[] : 3,2,0,4,1
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.EIGHT));
		cards.add(new Card(Card.SPADES, Card.ACE));
		cards.add(new Card(Card.DIAMONDS, Card.ACE));
		cards.add(new Card(Card.SPADES, Card.JACK));
		
		// I'm using a separate sorted array as opposed to whatever VideoPokerHand generates
		// to ensure that I'm ONLY testing out calculatedNormalizedIndices and nothing else.
		ArrayList<Card> sortedCards = new ArrayList<Card>();
		sortedCards.add(new Card(Card.SPADES, Card.ACE));
		sortedCards.add(new Card(Card.SPADES, Card.JACK));
		sortedCards.add(new Card(Card.CLUBS, Card.EIGHT));
		sortedCards.add(new Card(Card.CLUBS, Card.FIVE));
		sortedCards.add(new Card(Card.DIAMONDS, Card.ACE));
		
		VideoPokerHand vph = new VideoPokerHand(cards);
		int[] indices = vph.calculateNormalizedIndices(cards, sortedCards);
		int[] expectedIndices = {3,2,0,4,1};
		for (int i = 0; i < 5; i++) {
			assertTrue("Index " + i + " was " + indices[i] + ", not " + expectedIndices[i],
					indices[i] == expectedIndices[i]);
		}
	}
	
	@Test
	public void testNormalizedIndices2NoChange() {
		// Start : KsJs5s9c5c
		// End :   same
		// End[] : 0,1,2,3,4
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.KING));
		cards.add(new Card(Card.SPADES, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.NINE));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		
		VideoPokerHand vph = new VideoPokerHand(cards);
		int[] indices = vph.calculateNormalizedIndices(cards, cards);
		int[] expectedIndices = {0,1,2,3,4};
		for (int i = 0; i < 5; i++) {
			assertTrue("Index " + i + " was " + indices[i] + ", not " + expectedIndices[i],
					indices[i] == expectedIndices[i]);
		}
	}
	
	@Test
	public void testNormalizedIndices3() {
		// Start : 4cJhJd7c5c
		// End :   7c5c4cJhJd
		// End[] : 2,3,4,0,1
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		cards.add(new Card(Card.HEARTS, Card.JACK));
		cards.add(new Card(Card.DIAMONDS, Card.JACK));
		cards.add(new Card(Card.CLUBS, Card.SEVEN));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		
		// I'm using a separate sorted array as opposed to whatever VideoPokerHand generates
		// to ensure that I'm ONLY testing out calculatedNormalizedIndices and nothing else.
		ArrayList<Card> sortedCards = new ArrayList<Card>();
		sortedCards.add(new Card(Card.CLUBS, Card.SEVEN));
		sortedCards.add(new Card(Card.CLUBS, Card.FIVE));
		sortedCards.add(new Card(Card.CLUBS, Card.FOUR));
		sortedCards.add(new Card(Card.HEARTS, Card.JACK));
		sortedCards.add(new Card(Card.DIAMONDS, Card.JACK));

		VideoPokerHand vph = new VideoPokerHand(cards);
		int[] indices = vph.calculateNormalizedIndices(cards, sortedCards);
		int[] expectedIndices = {2,3,4,0,1};
		for (int i = 0; i < 5; i++) {
			assertTrue("Index " + i + " was " + indices[i] + ", not " + expectedIndices[i],
					indices[i] == expectedIndices[i]);
		}
	}
	
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
	
	@Test
	public void testFlushInOrder() {
		// Test hand : KsTs7s4s2s
		// Rearranged : KsTs7s4s2s
		// Result with numbers : K1T1714121
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.KING));
		cards.add(new Card(Card.SPADES, Card.TEN));
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.FOUR));
		cards.add(new Card(Card.SPADES, Card.DEUCE));

		VideoPokerHand vph = new VideoPokerHand(cards);
		
		ArrayList<Card> sortedCards = vph.getSortedCards();
		assertTrue("5 cards were not found after sorting.", sortedCards.size() == 5);
		for (int i = 0; i < 5; i++) {
			assertTrue("Cards did not match at position " + i + "\ncards:" + cards + "\nsortedCards:" + sortedCards, 
					cards.get(i).getSuit() == sortedCards.get(i).getSuit() && 
							cards.get(i).getDenomination() == sortedCards.get(i).getDenomination());
		}
		String expectedNormalized = "K1T1714121";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}
	
	@Test
	public void testRoyal() {
		// Test hand : JhQhAhThKh
		// Rearranged : AhKhQhJhTh
		// Result with numbers : A1K1Q1J1T1
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.HEARTS, Card.JACK));
		cards.add(new Card(Card.HEARTS, Card.QUEEN));
		cards.add(new Card(Card.HEARTS, Card.ACE));
		cards.add(new Card(Card.HEARTS, Card.TEN));
		cards.add(new Card(Card.HEARTS, Card.KING));

		VideoPokerHand vph = new VideoPokerHand(cards);
		
		ArrayList<Card> expectedCards = new ArrayList<Card>();
		expectedCards.add(new Card(Card.HEARTS, Card.ACE));
		expectedCards.add(new Card(Card.HEARTS, Card.KING));
		expectedCards.add(new Card(Card.HEARTS, Card.QUEEN));
		expectedCards.add(new Card(Card.HEARTS, Card.JACK));
		expectedCards.add(new Card(Card.HEARTS, Card.TEN));

		
		ArrayList<Card> sortedCards = vph.getSortedCards();
		assertTrue("5 cards were not found after sorting.", sortedCards.size() == 5);
		for (int i = 0; i < 5; i++) {
			assertTrue("Cards did not match at position " + i + "\nexpectedCards:" + expectedCards + "\nsortedCards:" + sortedCards, 
					expectedCards.get(i).getSuit() == sortedCards.get(i).getSuit() && 
					expectedCards.get(i).getDenomination() == sortedCards.get(i).getDenomination());
		}
		String expectedNormalized = "A1K1Q1J1T1";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}

	@Test
	public void testFullHouse() {
		// Test hand : 4s4d5c5s4h
		// Result with numbers : 5141524344
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.FOUR));
		cards.add(new Card(Card.DIAMONDS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FIVE));
		cards.add(new Card(Card.SPADES, Card.FIVE));
		cards.add(new Card(Card.HEARTS, Card.FOUR));

		VideoPokerHand vph = new VideoPokerHand(cards);

		String expectedNormalized = "5141524344";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}
	
	@Test
	public void testStraightFlush() {
		// Test hand : 4s3s6s7s5s
		// Result with numbers : 7161514131
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.FOUR));
		cards.add(new Card(Card.SPADES, Card.TREY));
		cards.add(new Card(Card.SPADES, Card.SIX));
		cards.add(new Card(Card.SPADES, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.FIVE));

		VideoPokerHand vph = new VideoPokerHand(cards);

		String expectedNormalized = "7161514131";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}
	
	@Test
	public void testStraightWith2ToASuit() {
		// Test hand : 5h7c6s8s9d
		// Result rearranged : 8s6s9d7c5h
		// Normalized result : 8161927354
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.HEARTS, Card.FIVE));
		cards.add(new Card(Card.CLUBS, Card.SEVEN));
		cards.add(new Card(Card.SPADES, Card.SIX));
		cards.add(new Card(Card.SPADES, Card.EIGHT));
		cards.add(new Card(Card.DIAMONDS, Card.NINE));

		VideoPokerHand vph = new VideoPokerHand(cards);

		String expectedNormalized = "8161927354";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}

	@Test
	public void testFourOfAKind() {
		// Test hand : 2s2dAc2c2h
		// Normalized result : A121222324
		
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.DEUCE));
		cards.add(new Card(Card.DIAMONDS, Card.DEUCE));
		cards.add(new Card(Card.CLUBS, Card.ACE));
		cards.add(new Card(Card.CLUBS, Card.DEUCE));
		cards.add(new Card(Card.HEARTS, Card.DEUCE));

		VideoPokerHand vph = new VideoPokerHand(cards);

		String expectedNormalized = "A121222324";
		assertTrue("Normalized string was " + vph.getNormalizedHand() + " instead of " + expectedNormalized, vph.getNormalizedHand().equals(expectedNormalized));
	}

	@Test
	public void testStringCreation() {
		ArrayList<Card> cards = new ArrayList<Card>();
		cards.add(new Card(Card.SPADES, Card.ACE));
		cards.add(new Card(Card.SPADES, Card.JACK));
		cards.add(new Card(Card.SPADES, Card.TEN));
		cards.add(new Card(Card.HEARTS, Card.FOUR));
		cards.add(new Card(Card.CLUBS, Card.FOUR));
		
		VideoPokerHand vph = new VideoPokerHand("AsJsTs4h4c");
		for (Card c : vph.getOriginalCards()) {
			Card compareCard = cards.remove(0);
			assertTrue(c.getSuit() == compareCard.getSuit() && c.getDenomination() == compareCard.getDenomination());
		}
	}
}
