package com.project.findMyTrain;

public class FindMyTrainConcreteFactoryTest extends FindMyTrainAbstractFactoryTest {

	@Override
	public DistanceDataMock createDistanceDataMock() {
		return new DistanceDataMock();
	}
	
	@Override
	public FindMyTrainDAOMock createFindMyTrainDAOMock() {
		return new FindMyTrainDAOMock();
	}
}
