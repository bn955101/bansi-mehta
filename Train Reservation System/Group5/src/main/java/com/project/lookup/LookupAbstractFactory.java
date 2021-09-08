package com.project.lookup;

public abstract class LookupAbstractFactory {
	private static LookupAbstractFactory instance = null;

	public static LookupAbstractFactory instance() {
		if (null == instance) {
			instance = new LookupConcreteFactory();
		}
		return instance;
	}

	public abstract ISearchTrainDAO createSearchTrainDAO();

	public abstract ISearchTrainDAO createNewSearchTrainDAO();

	public abstract ISearchTrain createSearchTrain();

	public abstract ISearchTrain createNewSearchTrain();

	public abstract ISeatAvailibilityDAO createSeatAvailibilityDAO();

	public abstract ISeatAvailibilityDAO createNewSeatAvailibilityDAO();

	public abstract ITrainFilterAndFairCalculation createTrainFilterAndCalculateFair();

	public abstract ITrainFilterAndFairCalculation createNewTrainFilterAndCalculateFair();

	public abstract IAvailableSeats createAvaliableSeats();

	public abstract IAvailableSeats createNewAvaliableSeats();

	public abstract IBookedTickets createBookedTickets();

	public abstract IBookedTickets createNewBookedTickets();

	public abstract IDayCalculation createDayCalculation();

	public abstract IDayCalculation createNewDayCalculation();

}
