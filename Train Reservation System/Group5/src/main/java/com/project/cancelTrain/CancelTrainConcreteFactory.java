package com.project.cancelTrain;

public class CancelTrainConcreteFactory extends CancelTrainAbstractFactory{
	private ITrainCancellationDAO trainCancellationDAO;
	private ITrainCancellation trainCancellation;
	
	@Override
	public ITrainCancellationDAO createTrainCancellationDAO() {
		if (trainCancellationDAO == null ) {
			trainCancellationDAO = new TrainCancellationDAO();
		}
		return trainCancellationDAO;
	}
	
	@Override
	public ITrainCancellationDAO createNewTrainCancellationDAO() {
		return new TrainCancellationDAO();
	}
	
	@Override
	public ITrainCancellation createNewTrainCancellation() {
		if (trainCancellation == null) {
			trainCancellation = new TrainCancellation();
		}
		return trainCancellation;
	}
	
	@Override
	public ITrainCancellation createTrainCancellation() {
		return new TrainCancellation();
	}
	
}
