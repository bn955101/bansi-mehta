package com.project.setup;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.jupiter.api.Test;

class CancelTrainTest {
	SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();

	@Test
	void testGetTrainCode() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();

		cancelTrain.setTrainCode(1);

		assertEquals(1, cancelTrain.getTrainCode());
	}

	@Test
	void testSetTrainCode() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();

		cancelTrain.setTrainCode(1);

		assertEquals(1, cancelTrain.getTrainCode());
	}

	@Test
	void testGetCancellationDate() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		Date currentDate = Date.valueOf("2021-04-08");

		cancelTrain.setCancellationDate(currentDate);

		assertEquals(currentDate, cancelTrain.getCancellationDate());
	}

	@Test
	void testSetCancellationDate() {
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();
		Date currentDate = Date.valueOf("2021-04-08");

		cancelTrain.setCancellationDate(currentDate);

		assertEquals(currentDate, cancelTrain.getCancellationDate());
	}

}
