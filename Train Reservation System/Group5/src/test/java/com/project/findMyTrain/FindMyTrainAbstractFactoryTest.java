package com.project.findMyTrain;

public abstract class FindMyTrainAbstractFactoryTest {
	private static FindMyTrainAbstractFactoryTest instance = null;

	public static FindMyTrainAbstractFactoryTest instance() {
		if (null == instance) {
			instance = new FindMyTrainConcreteFactoryTest();
		}
		return instance;
	}

	public abstract DistanceDataMock createDistanceDataMock();
	public abstract FindMyTrainDAOMock createFindMyTrainDAOMock();
}
