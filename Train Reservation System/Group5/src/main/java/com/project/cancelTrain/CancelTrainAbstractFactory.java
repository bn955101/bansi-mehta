package com.project.cancelTrain;

public abstract class CancelTrainAbstractFactory {
	private static CancelTrainAbstractFactory instance = null;
	
	public static CancelTrainAbstractFactory instance() {
        if (null == instance) {
            instance = new CancelTrainConcreteFactory();
        }
        return instance;
	}

	public abstract ITrainCancellationDAO createNewTrainCancellationDAO();

	public abstract ITrainCancellationDAO createTrainCancellationDAO();

	public abstract ITrainCancellation createTrainCancellation();

	public abstract ITrainCancellation createNewTrainCancellation();
	
}
