package domain;

/**
 * This class calculates "M" or "CSI" 
 * Which is basically:
 * Player's chip stack divided by all money the player
 * has to pay per round - the small and big blinds plus total of all antes.
 * @author mmadej
 *
 */
public class MCalc {

	/**
	 * Primary M calculation routine.
	 * 
	 * @param stackSize
	 * @param smallBlind
	 * @param bigBlind
	 * @return M value
	 */
	public static float calc(int stackSize, int smallBlind, int bigBlind, int ante, int players) {
		if (stackSize < 0 || smallBlind < 0 || bigBlind < 0 || ante < 0 || players < 0) {
			throw new RuntimeException("Negative value passed to M calculation routine.");
		}
		
		if (smallBlind + bigBlind == 0) {
			throw new RuntimeException("M calculation would throw a divide by zero exception.");
		}
		
		return (stackSize / (smallBlind + bigBlind + (ante * players)));
	}
	
	/**
	 * Simpler version to be used when antes are not present.
	 * @param stackSize
	 * @param smallBlind
	 * @param bigBlind
	 * @return M value
	 */
	public static float calc(int stackSize, int smallBlind, int bigBlind) {
		return MCalc.calc(stackSize, smallBlind, bigBlind, 0 , 0);
	}
	

}
