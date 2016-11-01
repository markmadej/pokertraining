package domain;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;

/* This class represents an object that can take a 5 card normalized hand
 * and return the rank it represents.
 * 
 * It also contains helper functions to serialize the calculated data to disk
 * as well as load from disk at startup (if present).
 * 
 */
public class FiveCardNormalizedResult {

	private static int PROPER_CACHE_SIZE = 292643;  //Number of possible combos.
	private HashMap<String, Integer> resultCache = null;
	private boolean cacheLoaded = false;
	
	private static String SERIALIZED_FILE_PATH = "src/main/resources/fiveCardNormalizedResults.data";
	
	public static void main(String args[]) {
		System.out.println("Running FiveCardNormalizedResult as script.");

		System.out.println("Exiting main().");
	}
	
	public FiveCardNormalizedResult() {
		cacheLoaded = false;
		System.out.println("Attempting to load cached results from file.");
		resultCache = loadFile();
		if (resultCache == null || resultCache.size() != PROPER_CACHE_SIZE) {
			System.out.println("Unable to properly load cache from file.  Rebuilding.");
			resultCache = generate5CardMappingTable();
			System.out.println("Generated file.  Now storing to disk.");
			if (!serializeFile(resultCache)) {
				System.out.println("Could not serialize and store file.  Cache is not initialized properly.");
				return;
			}
			System.out.println("Regenerated cache, attempting reload.");
			resultCache = loadFile();
		}
		
		if (resultCache == null || resultCache.size() != PROPER_CACHE_SIZE) {
			throw new RuntimeException("Unable to load cache for file even after regeneration.");
		} else {
			cacheLoaded = true;
			System.out.println("Successfully loaded results cache.");
		}
		
	}
	
	public int getResult(String normalizedHand) {
		if (cacheLoaded == false) {
			throw new RuntimeException("Result cache was not loaded properly, unable to get result.");
		}
		Integer result = resultCache.get(normalizedHand);
		if (result == null) {
			throw new RuntimeException("Result unable to be attained for normalized hand : " + normalizedHand);
		}
		return result.intValue();
	}
	
	private HashMap<String, Integer> loadFile() {
		System.out.println("Attempting to load serialized file from disk.");
		File readFile = new File(SERIALIZED_FILE_PATH);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(readFile);
		} catch (FileNotFoundException e) {
			System.out.println("Exception opening file " + readFile + " : " + e.toString());
			return null;
		}
		
		ObjectInputStream ois = null;
		Object obj = null;
		try {
			ois = new ObjectInputStream(fis);
			obj = ois.readObject();
		} catch (IOException e) {
			System.out.println("Exception creating ObjectInputStream:" + e.toString());
			try {
				ois.close();
				fis.close();
			} catch (Exception ex) {
				e.printStackTrace();
			}
			return null;
		} catch (ClassNotFoundException e) {
			e.printStackTrace();			
			try {
				ois.close();
				fis.close();
			} catch (Exception ex) {
				e.printStackTrace();
			}
			return null;
		}
		HashMap<String, Integer> cache = null;
		if (!(obj instanceof HashMap)) {
			System.out.println("A HashMap was not found - loading aborted.)");
		} else {
			cache = (HashMap<String, Integer>) obj;
			System.out.println("Loaded HashMap with " + cache.keySet().size() + " entries.");
		}
		try {
			ois.close();
			fis.close();
		} catch (IOException e) {
			System.out.println("Exception encountered while closing streams.");
			e.printStackTrace();
		}
		
		return cache;
	}
	
	private boolean serializeFile(HashMap<String, Integer> cacheMap) {
		System.out.println("Attempting to serialize file to disk.");
		File writeFile = new File(SERIALIZED_FILE_PATH);
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(writeFile, false);
		} catch (FileNotFoundException e) {
			System.out.println("Exception opening file " + writeFile + " : " + e.toString());
			return false;
		}
		ObjectOutputStream oos = null;
		try {
			oos = new ObjectOutputStream(fos);
		} catch (IOException e) {
			System.out.println("Exception creating output stream : " + e.toString());
			try {
				fos.close();
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			return false;
		}
		try {
			oos.writeObject(cacheMap);
		} catch (IOException e) {
			System.out.println("Exception writing object to file : " + e.toString());
			try { 
				fos.close();
				oos.close();
			} catch (Exception ex) {
				e.printStackTrace();
			}
			return false;
		} 
		try {
			System.out.println("Closing streams...");
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
			
		}
		System.out.println("Successfully wrote the 5 card normalized results to file.");
		return true;
	}
	
	private HashMap<String, Integer> generate5CardMappingTable() {
		//Go through all possible 5 card combinations.
		//Create the normalized string for each and determine the best hand possible.
		HashMap<String, Integer> cacheMap = new HashMap<String, Integer>(PROPER_CACHE_SIZE);
		
		int combosCalculated = 0;
		int cacheHits = 0;
		int totalCombosPossible = 311875200;
		int increment = totalCombosPossible / 100;
		
		
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
								int vpRank;
								if (cacheMap.containsKey(normalized)) {
									cacheHits++;
									vpRank = cacheMap.get(normalized);
								} else {
									vpRank = vph.calculateBestRank();
									cacheMap.put(normalized, vpRank);
								}
								
								if (combosCalculated >= increment && combosCalculated % increment == 0) {
									// Print out a status update.
									float percentage = (float)combosCalculated / (float)totalCombosPossible * 100.0f;
									System.out.println(combosCalculated + " / " + totalCombosPossible + " * 100 = " + percentage + "%...");
								}
							}
						}
					}
				}
		}
		System.out.println("FINAL TOTALS:\nCache vs total : " + cacheHits + "/" + combosCalculated);
		System.out.println("Cache created successfully with " + cacheMap.size() + " entries.");
		return cacheMap;
	}

	public boolean isCacheLoaded() {
		return cacheLoaded;
	}
}
