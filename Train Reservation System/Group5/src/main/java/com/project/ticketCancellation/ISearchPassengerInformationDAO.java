package com.project.ticketCancellation;

import java.util.List;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.setup.ITrain;

public interface ISearchPassengerInformationDAO {

	public List<IPassengerInformation> searchPassengerInfoByPNR(String pnrNumber);

	public IReservation getAmountPaidOnTicket(List<Integer> ids);

	public ITrain getTrainDetails(int trainId);

	public String getPnrNumber(int id);

	public void deleteTickets(List<Integer> ids, IReservation reservation, double refundedAmount);

}
