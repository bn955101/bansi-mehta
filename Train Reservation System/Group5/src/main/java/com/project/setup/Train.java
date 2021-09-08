package com.project.setup;

import java.sql.Date;
import java.util.List;

public class Train implements ITrain {
	public int trainId;
	public int trainCode;
	public String trainName;
	public String trainType;
	public String days;
	public String departureTime;
	public int totalCoaches;
	public String startStation;
	public String middleStations;
	public String endStation;
	public List<Integer> totalStation;
	public double fare;
	public String pickUPTime;
	public Date pickUPDate;
	public String dropTime;
	public Date dropUpDate;
	public int availableSeat;
	public double totalDistance;
	public Date startDate;

	public Train() {
	}

	public Train(int trainId, int trainCode, String trainName, String trainType, String days, String departureTime,
			int totalCoaches, String startStation, String middleStations, String endStation) {
		this.trainId = trainId;
		this.trainCode = trainCode;
		this.trainName = trainName;
		this.trainType = trainType;
		this.days = days;
		this.departureTime = departureTime;
		this.totalCoaches = totalCoaches;
		this.startStation = startStation;
		this.middleStations = middleStations;
		this.endStation = endStation;
	}

	@Override
	public int getTrainId() {
		return trainId;
	}

	@Override
	public void setTrainId(int trainId) {
		this.trainId = trainId;
	}

	@Override
	public int getTrainCode() {
		return trainCode;
	}

	@Override
	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	@Override
	public String getTrainName() {
		return trainName;
	}

	@Override
	public void setTrainName(String trainName) {
		this.trainName = trainName;
	}

	@Override
	public String getTrainType() {
		return trainType;
	}

	@Override
	public void setTrainType(String trainType) {
		this.trainType = trainType;
	}

	@Override
	public String getDays() {
		return days;
	}

	@Override
	public void setDays(String days) {
		this.days = days;
	}

	@Override
	public String getDepartureTime() {
		return departureTime;
	}

	@Override
	public void setDepartureTime(String departureTime) {
		this.departureTime = departureTime;
	}

	@Override
	public int getTotalCoaches() {
		return totalCoaches;
	}

	@Override
	public void setTotalCoaches(int totalCoaches) {
		this.totalCoaches = totalCoaches;
	}

	@Override
	public String getStartStation() {
		return startStation;
	}

	@Override
	public void setStartStation(String startStation) {
		this.startStation = startStation;
	}

	@Override
	public String getMiddleStations() {
		return middleStations;
	}

	@Override
	public void setMiddleStations(String middleStations) {
		this.middleStations = middleStations;
	}

	@Override
	public String getEndStation() {
		return endStation;
	}

	@Override
	public void setEndStation(String endStation) {
		this.endStation = endStation;
	}

	@Override
	public List<Integer> getTotalStation() {
		return totalStation;
	}

	@Override
	public void setTotalStation(List<Integer> totalStation) {
		this.totalStation = totalStation;
	}

	@Override
	public double getFare() {
		return fare;
	}

	@Override
	public void setFare(double fare) {
		this.fare = fare;
	}

	@Override
	public String getPickUPTime() {
		return pickUPTime;
	}

	@Override
	public void setPickUPTime(String pickUPTime) {
		this.pickUPTime = pickUPTime;
	}

	@Override
	public String getDropTime() {
		return dropTime;
	}

	@Override
	public void setDropTime(String dropTime) {
		this.dropTime = dropTime;
	}

	@Override
	public int getAvailableSeat() {
		return availableSeat;
	}

	@Override
	public void setAvailableSeat(int availableSeat) {
		this.availableSeat = availableSeat;
	}

	@Override
	public double getTotalDistance() {
		return totalDistance;
	}

	@Override
	public void setTotalDistance(double totalDistance) {
		this.totalDistance = totalDistance;
	}

	@Override
	public Date getPickUPDate() {
		return pickUPDate;
	}

	@Override
	public void setPickUPDate(Date pickUPDate) {
		this.pickUPDate = pickUPDate;
	}

	@Override
	public Date getDropUpDate() {
		return dropUpDate;
	}

	@Override
	public void setDropUpDate(Date dropUpDate) {
		this.dropUpDate = dropUpDate;
	}

	@Override
	public Date getStartDate() {
		return startDate;
	}

	@Override
	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	@Override
	public boolean isTrainCodeInvalid() {
		if (this.trainCode > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isTrainNameInvalid() {
		String trainNameWithoutSpace = this.trainName.trim();
		
		if (trainNameWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isTrainTypeInvalid() {
		String trainTypeWithoutSpace = this.trainType.trim();

		if (trainTypeWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isTrainDepartureTimeInvalid() {
		String trainDepartureTimeWithoutSpace = this.departureTime.trim();

		if (trainDepartureTimeWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isTotalCoachesInvalid() {
		if (this.trainCode > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isStartStationInvalid() {
		String trainStartStationWithoutSpace = this.startStation.trim();

		if (trainStartStationWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isEndStationInvalid() {
		String trainEndStationWithoutSpace = this.endStation.trim();

		if (trainEndStationWithoutSpace.length() > 0) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isSourceStationAndDestinationStationSame() {
		if (this.startStation.equals(this.endStation)) {
			return true;
		} else {
			return false;
		}
	}

}
