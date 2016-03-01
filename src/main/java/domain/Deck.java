package domain;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

/*
 * Class to represent a standard 52 card deck.
 */
public class Deck {

	private Stack<Card> cards = null;
	
	public Deck() {
		//Put all 52 cards in a big list.
		List<Card> cardList = new ArrayList<Card>();
		
		for (int suit = 1; suit <= 4; suit++) {
			for (int denomination = 1; denomination <= 13; denomination++) {
				Card c = new Card(suit, denomination);
				cardList.add(c);
			}
		}
		
		// Shuffle the deck
		Collections.shuffle(cardList);
		
		// Put shuffled cards in a Stack object which becomes our deck.
		cards = new Stack<Card>();
		for (int i = 0; i <= cardList.size(); i++) {
			cards.push(cardList.get(i));
		}
	}
	
	public int cardsLeft() {
		if (cards == null) return 0;
		
		return cards.size();
	}
	
	public Card dealCard() throws Exception {
		if (cards != null && cards.size() > 0) {
			return (Card) cards.pop();
		} else {
			throw new Exception("No more cards exist in the deck.");
		}
	}
}
