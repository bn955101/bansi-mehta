package com.project.ticketCancellation;

import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.IReservation;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;

@Controller
public class TicketCancellationController {
	private final String PNR_NUMBER = "pnrNumber";
	private final String ID_CHECKED = "idChecked";
	private final String NO_PNR_EXISTS = "Pnr does not exists";
			
	@RequestMapping(value = "/ticket/cancel")
	public String cancelTicketModel(Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();

		model.addAttribute(user);
		return "cancelTicket/searchByPnr";
	}

	@PostMapping(value = "/ticket/cancellation")
	public String searchDetailsByPnr(@RequestParam(name = PNR_NUMBER) String pnrNumber, Model model) {
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInformation = cancelTicketAbstractFactory
				.createNewSearchPassengerInfo();
		List<IPassengerInformation> passengerInfo = searchTicketInformation.searchPassengerInfoByPNR(pnrNumber);
		
		if (passengerInfo.size() > 0) {
			model.addAttribute("passengerInformationList", passengerInfo);	
		} else {
			model.addAttribute("errorMessage", NO_PNR_EXISTS);
		}
		return "cancelTicket/displayTicketInformation";	
	}

	@PostMapping(value = "/ticket/delete")
	public String selectTicketsToDelete(@RequestParam(name = ID_CHECKED) List<Integer> idList, Model model) {
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchTicketInformation = cancelTicketAbstractFactory
				.createNewSearchPassengerInfo();
		ICalculateAmount calculateAmount = cancelTicketAbstractFactory.createNewCalculateAmounts();
		IReservation reservation = searchTicketInformation.getAmountPaidOnTicket(idList);
		double refundedAmount = calculateAmount.calculateRefundAmount(reservation, idList, searchTicketInformation);

		searchTicketInformation.deleteTickets(idList, reservation, refundedAmount);
		model.addAttribute("refundedAmount", refundedAmount);
		return "cancelTicket/cancelConfirmation";
	}

	@PostMapping(value = "/ticket/delete/done")
	public String cancelTickets(Model model) {
		return "redirect:/user/home";
	}

}
