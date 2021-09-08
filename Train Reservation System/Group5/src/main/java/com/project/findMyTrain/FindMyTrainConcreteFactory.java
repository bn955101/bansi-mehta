package com.project.findMyTrain;


public class FindMyTrainConcreteFactory extends FindMyTrainAbstractFactory {
	private IDistanceData distanceData;
	private IFindMyTrainDAO findMyTrainDAO;
	private IFindMyTrainLocation findMyTrainLocation;

	@Override
	public IDistanceData createDistanceData() {
		if (distanceData == null) {
			distanceData = new DistanceData();
    	}
    	return distanceData;	
	}

	@Override
	public IDistanceData createNewDistanceData() {
		return new DistanceData();
	}

	@Override
	public IFindMyTrainDAO createFindMyTrainDAO() {
		if (findMyTrainDAO == null) {
			findMyTrainDAO = new FindMyTrainDAO();
    	}
    	return findMyTrainDAO;
	}

	@Override
	public IFindMyTrainDAO createNewFindMyTrainDAO() {
		return new FindMyTrainDAO();
	}

	@Override
	public IFindMyTrainLocation createFindMyTrainLocation() {
		if (findMyTrainLocation == null) {
			findMyTrainLocation = new FindMyTrainLocation();
    	}
    	return findMyTrainLocation;	
	}

	@Override
	public IFindMyTrainLocation createNewFindMyTrainLocation() {
		return new FindMyTrainLocation();
	}
	
}
