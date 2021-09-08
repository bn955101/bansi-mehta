package com.project.lookup;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DayCalculationTest {

	@Test
	void testGetDay() {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		IDayCalculation dayCalculation = lookupAbstractFactory.createDayCalculation();

		assertEquals("Tuesday", dayCalculation.getDay("Monday", 1));
		assertEquals("Wednesday", dayCalculation.getDay("Monday", 2));
		assertEquals("Thursday", dayCalculation.getDay("Monday", 3));
		assertEquals("Tuesday", dayCalculation.getDay("Saturday", 3));
		assertEquals("Tuesday", dayCalculation.getDay("Tuesday", 0));
	}

}
