package com.project.cancelTrain;

import java.util.List;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.ISeatAvailibilityDAO;
import com.project.reservation.IReservation;
import com.project.reservation.IReservationDAO;
import com.project.setup.IRouteDAO;
import com.project.ticketCancellation.ISearchPassengerInformationDAO;

public interface ITrainCancellation {

	void bookTicket(IReservation reservation, IReservationDAO reservationDAO );

	void cancelTicket(IReservation reservation, ISearchPassengerInformationDAO searchPassengerInformationDAO);

	void cancelOrRescheduleTicket(List<IReservation> reservationList, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO, ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO);

	void rescheduleOnWeekDays(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO, ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO);

	void rescheduleOnWeekEnds(IReservation reservation, ISearchTrainDAO searchTrainDAO, IRouteDAO routeDAO, ISeatAvailibilityDAO seatAvailibilityDAO, ISearchPassengerInformationDAO searchPassengerInformationDAO, IReservationDAO reservationDAO);
	
}