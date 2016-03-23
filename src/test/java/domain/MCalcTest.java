package domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class MCalcTest {

	public MCalcTest() {
	}

	@Test
	public void calcTests() {
		assertTrue(MCalc.calc(10000, 50, 100) == (10000 / (50 + 100)));
		assertTrue(MCalc.calc(5000, 200, 400) == (5000 / (200 + 400)));
		assertTrue(MCalc.calc(10000, 500, 1000) == (10000 / (500 + 1000)));
		
		assertTrue(MCalc.calc(10000, 500, 1000, 100, 8) == (10000 / (500 + 1000 + (100 * 8))));
		assertTrue(MCalc.calc(50000, 1000, 2000, 400, 7) == (50000 / (1000 + 2000 + (400 * 7))));		
	}

}
