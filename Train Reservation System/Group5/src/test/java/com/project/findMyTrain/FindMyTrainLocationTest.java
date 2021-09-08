package com.project.findMyTrain;

import static org.junit.Assert.assertEquals;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;
import com.project.setup.SetupAbstractFactoryTest;

public class FindMyTrainLocationTest {
	FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
	FindMyTrainAbstractFactoryTest findMyTrainAbstractFactoryTest = FindMyTrainAbstractFactoryTest.instance();
	SetupAbstractFactoryTest setupAbstractFactoryTest = SetupAbstractFactoryTest.instance();
	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();

	@Test
	void testFindMyTrainCalculation() {
		ITrain train = setupAbstractFactory.createNewTrain();
		train.setStartStation("1");
		train.setMiddleStations("2,3");
		train.setEndStation("4");
		train.setDays("Monday, Friday");
		IFindMyTrainLocation findMyTrainLocation = findMyTrainAbstractFactory.createFindMyTrainLocation();
		FindMyTrainDAOMock findMyTrainDAOMock = findMyTrainAbstractFactoryTest.createFindMyTrainDAOMock();
		try {
			Date date = new SimpleDateFormat("dd-MM-yyyy").parse("2021-04-05");
			assertEquals("No train running on that day.", findMyTrainLocation.findMyTrainCalculation(train, date, findMyTrainDAOMock));
		} catch (ParseException exception) {
			exception.printStackTrace();
		} 
	}
}
