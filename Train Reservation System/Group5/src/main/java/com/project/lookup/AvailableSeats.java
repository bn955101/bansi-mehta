package com.project.lookup;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

import com.project.setup.ITrain;

@Component
public class AvailableSeats implements IAvailableSeats {
	private final int SEATS_IN_ONE_COACH = 32;

	@Override
	public List<ITrain> findAvailableSeats(List<ITrain> trains, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvalibilityDAO) {
		int totalNumberOfTrain = 0;

		totalNumberOfTrain = trains.size();
		for (int index = 0; index < totalNumberOfTrain; index++) {
			findAvailableSeatCountInSingleTrain(trains.get(index), searchTrain, seatAvalibilityDAO);
		}
		return trains;
	}

	@Override
	public List<Integer> listOfMiddleStation(ITrain train, ISearchTrain searchTrain) {
		int sourceStationIndex = 0;
		int destinationStationIndex = 0;
		List<Integer> totalStation = train.getTotalStation();
		List<Integer> middleStationList = new ArrayList<Integer>();

		sourceStationIndex = Integer.parseInt(searchTrain.getSourceStation());
		destinationStationIndex = Integer.parseInt(searchTrain.getDestinationStation());
		for (int index = sourceStationIndex - 1; index < destinationStationIndex; index++) {
			middleStationList.add(totalStation.get(index));
		}
		return middleStationList;
	}

	@Override
	public void findAvailableSeatCountInSingleTrain(ITrain train, ISearchTrain searchTrain,
			ISeatAvailibilityDAO seatAvaillibilityDAO) {
		int seatAvailableFromBookedSeat = 0;
		int firstIndexOfMiddleStation = 0;
		int lastIndexOfMiddleStation = 0;
		int sourceStationIndexOfBookedTicket = 0;
		int destinationStationIndexOfBookedTicket = 0;
		int seatThatIsAvailableForBooking = 0;
		int firstIndexOfStationFromTripCode = 0;
		int lastIndexOfStationFromTripCode = 0;
		int totalSeatInOneTrain = 0;
		boolean isSeatAvailable = true;
		int maximumSeatNoFromDatabase = 0;

		maximumSeatNoFromDatabase = findMaximumSeatInSingleTrain(train, train.getStartDate(), seatAvaillibilityDAO);
		if (maximumSeatNoFromDatabase == 0) {

		} else {
			for (int index = 1; index <= maximumSeatNoFromDatabase; index++) {
				List<IBookedTickets> bookedTickets = seatAvaillibilityDAO.getListOfTicketsFromSeatNo(train,
						train.getStartDate(), index);

				isSeatAvailable = true;
				for (int bookedTicketIterator = 0; bookedTicketIterator < bookedTickets
						.size(); bookedTicketIterator++) {
					List<Integer> allStation = train.getTotalStation();
					List<Integer> stationOfTripCode = new ArrayList<Integer>();

					sourceStationIndexOfBookedTicket = bookedTickets.get(bookedTicketIterator).getSourceStationId();
					destinationStationIndexOfBookedTicket = bookedTickets.get(bookedTicketIterator).getDestinationId();
					for (int middleStationIterator = sourceStationIndexOfBookedTicket; middleStationIterator <= destinationStationIndexOfBookedTicket; middleStationIterator++) {
						stationOfTripCode.add(allStation.get(middleStationIterator - 1));
					}
					List<Integer> afterRemovingSourceStationFromTripCode = new ArrayList<Integer>();
					List<Integer> afterRemovingDesinationStationFromTripCode = new ArrayList<Integer>();

					for (int stationRemoverIterator = 0; stationRemoverIterator < stationOfTripCode
							.size(); stationRemoverIterator++) {
						afterRemovingSourceStationFromTripCode.add(stationOfTripCode.get(stationRemoverIterator));
						afterRemovingDesinationStationFromTripCode.add(stationOfTripCode.get(stationRemoverIterator));
					}
					firstIndexOfStationFromTripCode = 0;
					afterRemovingSourceStationFromTripCode.remove(firstIndexOfStationFromTripCode);
					lastIndexOfStationFromTripCode = stationOfTripCode.size() - 1;
					afterRemovingDesinationStationFromTripCode.remove(lastIndexOfStationFromTripCode);
					List<Integer> middleStationAfterRemovingSourceStation = new ArrayList<Integer>();

					middleStationAfterRemovingSourceStation = listOfMiddleStation(train, searchTrain);
					middleStationAfterRemovingSourceStation.remove(firstIndexOfMiddleStation);
					for (int sourceStationRemoverIterator = 0; sourceStationRemoverIterator < middleStationAfterRemovingSourceStation
							.size(); sourceStationRemoverIterator++) {
						for (int tripCodeIterator = 0; tripCodeIterator < afterRemovingSourceStationFromTripCode
								.size(); tripCodeIterator++) {
							if (middleStationAfterRemovingSourceStation
									.get(sourceStationRemoverIterator) == afterRemovingSourceStationFromTripCode
											.get(tripCodeIterator)) {
								isSeatAvailable = false;
								break;
							}
						}
					}
					List<Integer> middleStationAfterRemovingDestinationStation = new ArrayList<Integer>();

					middleStationAfterRemovingDestinationStation = listOfMiddleStation(train, searchTrain);
					lastIndexOfMiddleStation = middleStationAfterRemovingDestinationStation.size() - 1;
					middleStationAfterRemovingDestinationStation.remove(lastIndexOfMiddleStation);
					for (int destinationStationRemoverIterator = 0; destinationStationRemoverIterator < middleStationAfterRemovingDestinationStation
							.size(); destinationStationRemoverIterator++) {
						for (int tripCodeIterator = 0; tripCodeIterator < afterRemovingDesinationStationFromTripCode
								.size(); tripCodeIterator++) {
							if (middleStationAfterRemovingDestinationStation.get(
									destinationStationRemoverIterator) == afterRemovingDesinationStationFromTripCode
											.get(tripCodeIterator)) {
								isSeatAvailable = false;
								break;
							}
						}
					}
				}
				if (isSeatAvailable) {
					seatAvailableFromBookedSeat++;
				}
			}
		}
		totalSeatInOneTrain = train.getTotalCoaches() * SEATS_IN_ONE_COACH;
		seatThatIsAvailableForBooking = totalSeatInOneTrain - maximumSeatNoFromDatabase + seatAvailableFromBookedSeat;
		train.setAvailableSeat(seatThatIsAvailableForBooking);
	}

	@Override
	public int findMaximumSeatInSingleTrain(ITrain train, Date date, ISeatAvailibilityDAO seatAvaillibilityDAO) {
		int maximumSeatNumber = 0;
		int maximumSeatNumberOfThatReservationId = 0;
		List<Integer> reservationIds = seatAvaillibilityDAO.getReservationId(train, date);

		for (int index = 0; index < reservationIds.size(); index++) {
			maximumSeatNumberOfThatReservationId = seatAvaillibilityDAO
					.maximumSeatNumberOfReservationId(reservationIds.get(index));
			if (maximumSeatNumberOfThatReservationId > maximumSeatNumber) {
				maximumSeatNumber = maximumSeatNumberOfThatReservationId;
			}
		}
		return maximumSeatNumber;
	}

}
