package com.project.ticketPrint;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class TicketPrintController {

	@PostMapping(value = "/user/ticketprint/download")
	public String downloadTicketPDF(@RequestParam(name = "reservationId") int reservationId, Model model) {
		TicketPrintAbstractFactory ticketPrintAbstractFactory = TicketPrintAbstractFactory.instance();
		ITicketPrintDAO ticketPrintDAO = ticketPrintAbstractFactory.createNewTicketPrintDAO();
		ITicketPrint ticketPrint = ticketPrintDAO.ticketPrint(reservationId);

		model.addAttribute("ticketPrint", ticketPrint);
		return "redirect:/user/home";
	}

}