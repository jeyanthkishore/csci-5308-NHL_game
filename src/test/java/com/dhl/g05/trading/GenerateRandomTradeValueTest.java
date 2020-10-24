package com.dhl.g05.trading;

import org.junit.Test;
import static org.junit.Assert.assertTrue;

public class GenerateRandomTradeValueTest {
	
	@Test
	public void generateRandomValueTest() {
	GenerateRandomTradeValue tradeValue = new GenerateRandomTradeValue();
	double randomValue= tradeValue.generateRandomValue();
	assertTrue(randomValue>= 0.00 && randomValue <=1.00);
	}
}
