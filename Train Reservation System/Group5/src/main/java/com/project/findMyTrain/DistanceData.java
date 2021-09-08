package com.project.findMyTrain;

public class DistanceData implements IDistanceData {
		private int startStation;
		private int endStation;
		private double distance;
	    
	 	public DistanceData() {
	    }
	    
	    public DistanceData(int startStation, int endStation, double distance) {
	        super();
	        this.startStation = startStation;
	        this.endStation = endStation;
	        this.distance = distance;
	    }

	    @Override
	    public int getStartStation() {
	        return startStation;
	    }

	    @Override
	    public void setStartStation(int startStation) {
	        this.startStation = startStation;
	    }

	    @Override
	    public int getEndStation() {
	        return endStation;
	    }

	    @Override
	    public void setEndStation(int endStation) {
	        this.endStation = endStation;
	    }

	    @Override
	    public double getDistance() {
	        return distance;
	    }

	    @Override
	    public void setDistance(double distance) {
	        this.distance = distance;
	    }
	    
	    @Override
	    public boolean isStringNullOrEmpty(String string) {
			if (null == string) {
				return true;
			}
			return string.isEmpty();
		}
	    
	    @Override
	    public boolean isTrainCodeValid(String trainCode) {
			return isStringNullOrEmpty(trainCode);
		}

	    @Override
		public boolean isDateValid(String date) {
			return isStringNullOrEmpty(date);
		}
}
