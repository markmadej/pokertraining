package calculation;
import java.util.HashMap;

import domain.Card;
import domain.VideoPokerHand;

// I'll use this to test out some things and build the functions I need.
public class VPCalc {
	public static void main(String[] args) {
		VPCalc vpc = new VPCalc();
		vpc.generate5CardMappingTable();
	}
	
	public VPCalc() { 
	
	}
	
	public void generate5CardMappingTable() {
		//Go through all possible 5 card combinations.
		//Create the normalized string for each and determine the best hand possible.
		int combosCalculated = 0;
		int cacheHits = 0;
		HashMap<String, Integer> normalizedCache = new HashMap<String, Integer>();
		
		for (int card1 = 1; card1 <= 52; card1++) {
				for (int card2 = 1; card2 <= 52; card2++) {
					if (card2 == card1) 
						continue;  //Can't have two of the same card in the same hand.
					for (int card3 = 1; card3 <= 52; card3++) {
						if (card3 == card1 || card3 == card2)
							continue;
						for (int card4 = 1; card4 <= 52; card4++) {
							if (card4 == card1 || card4 == card2 || card4 == card3)
								continue;
							for (int card5 = 1; card5 <= 52; card5++) {
								if (card5 == card1 || card5 == card2 || card5 == card3 || card5 == card4) 
									continue;
								//At this point we have our 5 card combination.  Create the hand and evaluate.
								combosCalculated++;
								
								int[] cardArr = {card1, card2, card3, card4, card5};
								VideoPokerHand vph = new VideoPokerHand(cardArr);
								String normalized = vph.getNormalizedHand();
								//StringBuffer sb = new StringBuffer();
								if (normalizedCache.containsKey(normalized)) {
									cacheHits++;
								} else {
									normalizedCache.put(normalized, vph.getHandRank());
									System.out.println("Adding to cache : " + normalized + " - " + vph.getHumanReadableRank(vph.getHandRank()));
								}
								
								for (int i = 0; i < 5; i++) {
									Card c = new Card(cardArr[i]);
								//	sb.append(c.shortString());
								}
								/*sb.append(" - normalized : ");
								sb.append(normalized);
								sb.append(" - ");
								sb.append(vph.getHumanReadableRank(vph.getHandRank()));
								sb.append(" - cache hits : ");
								sb.append(cacheHits);
								sb.append("/");
								sb.append(combosCalculated);
								System.out.println(sb.toString());*/	
								if (combosCalculated % 100000 == 0) {
									float percentage = (float)cacheHits / (float)combosCalculated;
									System.out.println("Cache vs total : " + cacheHits + "/" + combosCalculated + " == " + (percentage * 100) + "%");
								}
							}
						}
					}
				}
		}
		float percentage = (float)cacheHits / (float)combosCalculated;
		System.out.println("FINAL TOTALS:\nCache vs total : " + cacheHits + "/" + combosCalculated + " == " + (percentage * 100) + "%");
	}
}
