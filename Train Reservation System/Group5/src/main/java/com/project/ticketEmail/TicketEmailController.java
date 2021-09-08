package com.project.ticketEmail;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class TicketEmailController {

	@GetMapping(value = "/user/ticketemail/{reservationId}")
	public String downloadTicketPDF(@PathVariable(name ="reservationId") int reservationId,Model model) {
		TicketEmailAbstractFactory ticketEmailAbstractFactory = TicketEmailAbstractFactory.instance();
		ITicketEmailDAO ticketEmailDAO = ticketEmailAbstractFactory.createNewTicketEmailDAO();
		ITicketEmail ticketEmail = ticketEmailDAO.ticketEmail(reservationId);
		model.addAttribute("ticketEmail", ticketEmail);
		return "redirect:/user/home";
	}
}
