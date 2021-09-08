package com.project.cancelTrain;

import java.sql.Date;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.project.lookup.IAvailableSeats;
import com.project.lookup.IDayCalculation;
import com.project.lookup.ISearchTrain;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.ISeatAvailibilityDAO;
import com.project.lookup.ITrainFilterAndFairCalculation;
import com.project.lookup.LookupAbstractFactory;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.reservation.IReservationDAO;
import com.project.setup.IRouteDAO;
import com.project.setup.ITrain;
import com.project.ticketCancellation.CancelTicketAbstractFactory;
import com.project.ticketCancellation.ICalculateAmount;
import com.project.ticketCancellation.ISearchPassengerInformationDAO;

public class TrainCancellation implements ITrainCancellation {
	private final static String CANCEL_TRAIN = "Cancel Ticket";
	private final static String RESCHEDULE_ON_WEEK_DAYS = "Reschedule on week-days";
	private final static String RESCHEDULE_ON_WEEK_ENDS = "Reschedule on week-ends";
	
	@Override
	public void cancelOrRescheduleTicket(List<IReservation> reservationList, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO , ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO) {
		for(int index = 0; index < reservationList.size(); index++) {
			IReservation reservation = reservationList.get(index);
			
			if (reservation.getTrainCancelEvent().equals(CANCEL_TRAIN)) {
				this.cancelTicket(reservation , searchPassengerInformationDAO);
			} else if (reservation.getTrainCancelEvent().equals(RESCHEDULE_ON_WEEK_DAYS)) {
				this.rescheduleOnWeekDays(reservation, searchTrainDAO, routeDAO, seatAvailibilityDAO ,searchPassengerInformationDAO , reservationDAO  ); 
			} else if (reservation.getTrainCancelEvent().equals(RESCHEDULE_ON_WEEK_ENDS)) {
				this.rescheduleOnWeekEnds(reservation, searchTrainDAO, routeDAO, seatAvailibilityDAO , searchPassengerInformationDAO , reservationDAO );
			} 
		}
	}
	
	@Override
	public void rescheduleOnWeekDays(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO ,  ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();		
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		IDayCalculation dayCalculation = lookupAbstractFactory.createNewDayCalculation();
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		boolean trainFound = false;
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		while(currentDate.before(dateAfterAWeek)) {
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			
			if (dayOfWeek.equals(DayOfWeek.MONDAY) || dayOfWeek.equals(DayOfWeek.TUESDAY) || dayOfWeek.equals(DayOfWeek.WEDNESDAY) || dayOfWeek.equals(DayOfWeek.THURSDAY) || dayOfWeek.equals(DayOfWeek.FRIDAY)) {
				searchTrain.setDateOfJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFareCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain, routeDAO , dayCalculation);
				boolean ticketBooked = false;
				
				availableSeats.findAvailableSeats(trainListWithFareCalculation, searchTrain, seatAvailibilityDAO);
				for (int index = 0; index < trainListWithFareCalculation.size(); index++) {
					ITrain train = trainListWithFareCalculation.get(index); 
					boolean sameDayDifferentTrain = true;
					
					if (currentDate.equals(reservation.getStartDate())) {
						if (train.getTrainId() == reservation.getTrainId()) {
							sameDayDifferentTrain = false;
						}
					}
					if (train.getAvailableSeat() >= reservation.getTicketBooked() && sameDayDifferentTrain) {
						this.cancelTicket(reservation ,  searchPassengerInformationDAO );
						reservation.setTrainId(train.getTrainId());
						reservation.setDistance(train.getTotalDistance());
						reservation.setStartDate(train.getStartDate());
						this.bookTicket(reservation, reservationDAO);
						ticketBooked = true;
						break;
					}
				}
				if (ticketBooked) {
					trainFound = true;
					break;
				} 
			}	
			currentDate = this.getNextDate(currentDate);
		}
		if (trainFound == false) {
			this.cancelTicket(reservation , searchPassengerInformationDAO);
		}
	}
	
	@Override
	public void rescheduleOnWeekEnds(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO ,  ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory.createNewTrainFilterAndCalculateFair();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		IDayCalculation dayCalculation = lookupAbstractFactory.createNewDayCalculation();
		Date currentDate = reservation.getStartDate();
		Date dateAfterAWeek = this.getDateAfterAWeek(currentDate);
		boolean trainFound = false;
		
		searchTrain.setTrainType(reservation.getTrainType());
		searchTrain.setDestinationStation(Integer.toString(reservation.getDestinationStationId()));
		searchTrain.setSourceStation(Integer.toString(reservation.getSourceStationId()));
		while(currentDate.before(dateAfterAWeek)) {
			DayOfWeek dayOfWeek = this.findDay(currentDate);
			
			if (dayOfWeek.equals(DayOfWeek.SATURDAY) || dayOfWeek.equals(DayOfWeek.SUNDAY)) {
				searchTrain.setDateOfJourny(currentDate);
				List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
				List<ITrain> trainListWithFareCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain,routeDAO , dayCalculation);
				boolean ticketBooked = false;
				
				availableSeats.findAvailableSeats(trainListWithFareCalculation, searchTrain, seatAvailibilityDAO);
				for (int index = 0; index < trainListWithFareCalculation.size(); index++) {
					ITrain train = trainListWithFareCalculation.get(index);
					boolean sameDayDifferentTrain = true;
					
					if (currentDate.equals(reservation.getStartDate())) {
						if (train.getTrainId() == reservation.getTrainId()) {
							sameDayDifferentTrain = false;
						}
					}
					if (train.getAvailableSeat() >= reservation.getTicketBooked() && sameDayDifferentTrain) {
						this.cancelTicket(reservation , searchPassengerInformationDAO);
						reservation.setTrainId(train.getTrainId());
						reservation.setDistance(train.getTotalDistance());
						reservation.setStartDate(train.getStartDate());
						this.bookTicket(reservation , reservationDAO);
						ticketBooked = true;
						break;
					}
				}
				if (ticketBooked) {
					trainFound = true;
					break;
				} 
			}
			currentDate = this.getNextDate(currentDate);
		}
		if (trainFound == false) {
			this.cancelTicket(reservation , searchPassengerInformationDAO );
		}
	}
	
	@Override
	public void bookTicket(IReservation reservation ,  IReservationDAO reservationDAO) {
		IReservation reservationInformation = reservationDAO.saveReservationInformation(reservation);
		
		reservation.calculateTotalReservationFare(reservation);
		reservationDAO.savePassengerInformation(reservationInformation);
	}
	
	@Override
	public void cancelTicket(IReservation reservation ,  ISearchPassengerInformationDAO searchPassengerInformationDAO) {
		List<Integer> ticketsToBeCancelled = new ArrayList<Integer>(0);
		
		if (reservation.getPassengerInformation().size() > 0) {
			for(int index = 0; index < reservation.getPassengerInformation().size(); index++) {
				IPassengerInformation passenger = reservation.getPassengerInformation().get(index);
				
				ticketsToBeCancelled.add(passenger.getPassengerInformationId());
			}
		}
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ICalculateAmount calculateAmount = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservationInformation = searchPassengerInformationDAO.getAmountPaidOnTicket(ticketsToBeCancelled);
		double refundedAmount = calculateAmount.calculateRefundAmount(reservationInformation, ticketsToBeCancelled, searchPassengerInformationDAO);
		
		searchPassengerInformationDAO.deleteTickets(ticketsToBeCancelled, reservationInformation, refundedAmount);	
	}
	
	public DayOfWeek findDay(Date date) {
		LocalDate localDate = date.toLocalDate();
		
		return localDate.getDayOfWeek();
	}
	
	public Date getNextDate(Date date) {
		LocalDate localDate = date.toLocalDate();
		
		return Date.valueOf(localDate.plusDays(1));
	}
	
	public Date getDateAfterAWeek(Date date) {
		LocalDate localDateAfterAWeek = date.toLocalDate().plusDays(8);
		
		return Date.valueOf(localDateAfterAWeek);
	}
	
}
