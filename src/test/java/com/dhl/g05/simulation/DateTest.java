package com.dhl.g05.simulation;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class DateTest {
	public Date date1;
	public Date date2;
	
	
	@Before
	public void init() {
		date1 = Date.getInstance();
		date2 = Date.getInstance();
	}


	@Test
	public void testGetInstance() {
		assertNotNull(date1);
		assertEquals(date1,date2);
	}

}
