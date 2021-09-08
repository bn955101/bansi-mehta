package com.project.findMyTrain;

public abstract class FindMyTrainAbstractFactory {
	private static FindMyTrainAbstractFactory instance = null;
	
	public static FindMyTrainAbstractFactory instance() {
		if (null == instance) {
			instance = new FindMyTrainConcreteFactory();
		}
		return instance;
	}
	
	public abstract IDistanceData createDistanceData();
	
	public abstract IDistanceData createNewDistanceData();
	
	public abstract IFindMyTrainDAO createFindMyTrainDAO();
	
	public abstract IFindMyTrainDAO createNewFindMyTrainDAO();
	
	public abstract IFindMyTrainLocation createFindMyTrainLocation();
	
	public abstract IFindMyTrainLocation createNewFindMyTrainLocation();
	
}
