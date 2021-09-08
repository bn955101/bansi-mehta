package com.project.findMyTrain;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;

public class DistanceDataTest {
	FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
	FindMyTrainAbstractFactoryTest findMyTrainAbstractFactoryTest = FindMyTrainAbstractFactoryTest.instance();
	IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

	@Test
	void testGetStartStation() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setStartStation(1);

		assertEquals(1, distanceData.getStartStation());
	}

	@Test
	void testSetStartStation() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setStartStation(1);

		assertEquals(1, distanceData.getStartStation());
	}

	@Test
	void testGetEndStation() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setEndStation(4);

		assertEquals(4, distanceData.getEndStation());
	}

	@Test
	void testSetEndStation() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setEndStation(4);

		assertEquals(4, distanceData.getEndStation());
	}

	@Test
	void testGetDistance() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setDistance(1000.0);

		assertEquals(1000.0, distanceData.getDistance(), 0.001);
	}

	@Test
	void testSetDistance() {
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();

		distanceData.setDistance(1000.0);

		assertEquals(1000.0, distanceData.getDistance(), 0.001);
	}

	@Test
	void testIsTrainCodeValid() {
		assertEquals(false, distanceData.isTrainCodeValid("1"));
		assertEquals(true, distanceData.isTrainCodeValid(""));
		assertEquals(true, distanceData.isTrainCodeValid(null));
	}

	@Test
	void testIsDateValid() {
		String dateStr = "2000-04-06";

		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

			assertEquals(false, distanceData.isDateValid(date.toString()));
			assertEquals(true, distanceData.isDateValid(""));
			assertEquals(true, distanceData.isDateValid(null));

		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

}
