package com.project.lookup;

import java.sql.Date;
import java.util.List;

import com.project.setup.ITrain;

public interface ISeatAvailibilityDAO {

	List<IBookedTickets> getListOfTicketsFromSeatNo(ITrain train, Date date, int seatNo);

	List<Integer> getReservationId(ITrain train, Date date);

	int maximumSeatNumberOfReservationId(int reservationId);

}
