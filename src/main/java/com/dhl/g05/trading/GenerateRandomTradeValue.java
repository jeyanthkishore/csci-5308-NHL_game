package com.dhl.g05.trading;

import java.util.Random;

public class GenerateRandomTradeValue 
{
	public Random randomValue;
	public double ramdomeTradeValue;

	public GenerateRandomTradeValue() 
	{
		randomValue = new Random();
		ramdomeTradeValue = 0.0;
	}

	public double generateRandomValue()
	{	
		ramdomeTradeValue = randomValue.nextDouble();
		return ramdomeTradeValue;		
	}

}
