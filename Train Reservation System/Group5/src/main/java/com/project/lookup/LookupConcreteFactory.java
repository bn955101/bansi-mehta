package com.project.lookup;

public class LookupConcreteFactory extends LookupAbstractFactory {
	private ISearchTrainDAO searchTrainDAO;
	private ISearchTrain searchtrain;
	private ITrainFilterAndFairCalculation trainFilterAndCalculation;
	private IAvailableSeats avialableSeats;
	private ISeatAvailibilityDAO seatAvailibilityDAO;
	private IBookedTickets bookedTickets;
	private IDayCalculation dayCalculation;

	@Override
	public ISearchTrainDAO createSearchTrainDAO() {
		if (searchTrainDAO == null) {
			searchTrainDAO = new SearchTrainDAO();
		}
		return searchTrainDAO;
	}

	@Override
	public ISearchTrainDAO createNewSearchTrainDAO() {
		return new SearchTrainDAO();
	}

	@Override
	public ISearchTrain createSearchTrain() {
		if (searchtrain == null) {
			searchtrain = new SearchTrain();
		}
		return searchtrain;
	}

	@Override
	public ISearchTrain createNewSearchTrain() {
		return new SearchTrain();
	}
	
	@Override
	public ISeatAvailibilityDAO createSeatAvailibilityDAO() {
		if (seatAvailibilityDAO == null) {
			seatAvailibilityDAO = new SeatAvailibilityDAO();
		}
		return seatAvailibilityDAO;
	}

	@Override
	public ISeatAvailibilityDAO createNewSeatAvailibilityDAO() {
		return new SeatAvailibilityDAO();
	}

	@Override
	public ITrainFilterAndFairCalculation createTrainFilterAndCalculateFair() {
		if (trainFilterAndCalculation == null) {
			trainFilterAndCalculation = new TrainFilterAndFairCalculation();
		}
		return trainFilterAndCalculation;
	}

	@Override
	public ITrainFilterAndFairCalculation createNewTrainFilterAndCalculateFair() {
		return new TrainFilterAndFairCalculation();
	}

	@Override
	public IAvailableSeats createAvaliableSeats() {
		if (avialableSeats == null) {
			avialableSeats = new AvailableSeats();
		}
		return avialableSeats;
	}

	@Override
	public IAvailableSeats createNewAvaliableSeats() {
		return new AvailableSeats();
	}

	@Override
	public IBookedTickets createBookedTickets() {
		if (bookedTickets == null) {
			bookedTickets = new BookedTickets();
		}
		return bookedTickets;
	}

	@Override
	public IBookedTickets createNewBookedTickets() {
		return new BookedTickets();
	}

	@Override
	public IDayCalculation createDayCalculation() {
		if (dayCalculation == null) {
			dayCalculation = new DaysCalculation();
		}
		return dayCalculation;
	}

	@Override
	public IDayCalculation createNewDayCalculation() {
		return new DaysCalculation();
	}

}
