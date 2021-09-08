package com.project.setup;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import com.project.cancelTrain.CancelTrainAbstractFactory;
import com.project.cancelTrain.ITrainCancellation;
import com.project.cancelTrain.ITrainCancellationDAO;
import com.project.lookup.ISearchTrainDAO;
import com.project.lookup.ISeatAvailibilityDAO;
import com.project.lookup.LookupAbstractFactory;
import com.project.reservation.IReservation;
import com.project.reservation.IReservationDAO;
import com.project.reservation.ReservationAbstractFactory;
import com.project.ticketCancellation.CancelTicketAbstractFactory;
import com.project.ticketCancellation.ISearchPassengerInformationDAO;

@Controller
public class CancelTrainController {

	@ModelAttribute("cancelTrain")
	public ICancelTrain getIRouteModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();

		return cancelTrain;
	}

	@GetMapping(value = "/admin/cancelTrain")
	public String displayCancelTrain(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ICancelTrain cancelTrain = setupAbstractFactory.createNewCancelTrain();

		model.addAttribute("cancelTrain", cancelTrain);
		return "cancelTrain/cancelTrain";
	}

	@PostMapping(value = "/admin/cancelTrain/success")
	public String cancelTrain(@ModelAttribute("cancelTrain") ICancelTrain cancelTrain, Model model) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		CancelTrainAbstractFactory cancelTrainAbstractFactory = CancelTrainAbstractFactory.instance();
		CancelTicketAbstractFactory cancelTicketAbstractFactory = CancelTicketAbstractFactory.instance();
		ISearchPassengerInformationDAO searchPassengerInformationDAO = cancelTicketAbstractFactory
				.createNewSearchPassengerInfo();
		IReservationDAO reservationDAO = reservationAbstractFactory.createNewReservationDAO();
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
		ISeatAvailibilityDAO seatAvailabilityDAO = lookupAbstractFactory.createNewSeatAvailibilityDAO();
		ITrainCancellationDAO trainCancellationDAO = cancelTrainAbstractFactory.createNewTrainCancellationDAO();
		ITrainCancellation trainCancellation = cancelTrainAbstractFactory.createNewTrainCancellation();
		List<IReservation> reservationList = trainCancellationDAO.fetchAllReservations(cancelTrain);

		trainCancellation.cancelOrRescheduleTicket(reservationList, searchTrainDAO, routeDAO, seatAvailabilityDAO,
				searchPassengerInformationDAO, reservationDAO);
		return "home";
	}

}
