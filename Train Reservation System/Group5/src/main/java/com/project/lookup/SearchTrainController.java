package com.project.lookup;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.reservation.IReservation;
import com.project.reservation.ReservationAbstractFactory;
import com.project.setup.IRouteDAO;
import com.project.setup.IStation;
import com.project.setup.IStationDAO;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Controller
public class SearchTrainController {
	private final String DATE_OF_JOURNEY = "dateOfJourny";

	@ModelAttribute("searchTrain")
	public ISearchTrain getISearchStationModelObject(HttpServletRequest request) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();

		return searchTrain;
	}

	@GetMapping(value = "/user/home")
	public String showSearchTrainModel(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createNewStationDAO();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		List<IStation> sourceStations = stationDAO.getAllStation();
		List<IStation> destinationStation = stationDAO.getAllStation();

		model.addAttribute("listOfSourceStations", sourceStations);
		model.addAttribute("listOfDestinationStations", destinationStation);
		model.addAttribute(searchTrain);
		return "searchTrain/searchTrain";

	}

	@PostMapping(value = "/user/home")
	public String searchTrainModel(@ModelAttribute("searchTrain") ISearchTrain searchTrain,
			@RequestParam(name = DATE_OF_JOURNEY, defaultValue = "2019-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateofJourny,
			Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		IRouteDAO routeDAO = setupAbstractFactory.createNewRouteDAO();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		IReservation reservation = reservationAbstractFactory.createReservation();
		ISeatAvailibilityDAO seatAvaillibilityDAO = lookupAbstractFactory.createNewSeatAvailibilityDAO();
		ISearchTrainDAO searchTrainDAO = lookupAbstractFactory.createSearchTrainDAO();
		ITrainFilterAndFairCalculation trainFilterAndCalculateFair = lookupAbstractFactory
				.createNewTrainFilterAndCalculateFair();
		IAvailableSeats availableSeats = lookupAbstractFactory.createAvaliableSeats();
		IDayCalculation dayCalculation = lookupAbstractFactory.createDayCalculation();
		IStation sourceStation = null;
		IStation destinationStation = null;
		boolean hasError = false;

		if (searchTrain.isSourceStationAndDestinationStationSame(searchTrain.getSourceStation(),
				searchTrain.getDestinationStation())) {
			hasError = true;
			model.addAttribute("stationError", "true");
		}
		if (searchTrain.isDatePreviousDate(dateofJourny)) {
			hasError = true;
			model.addAttribute("dateError", "true");
		}
		if (searchTrain.isDateInWithinOneMonthPeriod(dateofJourny) == false) {
			hasError = true;
			model.addAttribute("dateExceedOneMOnth", "true");
		}
		if (hasError) {
			List<IStation> sourceStations = stationDAO.getAllStation();
			List<IStation> destinationStations = stationDAO.getAllStation();

			model.addAttribute("listOfSourceStations", sourceStations);
			model.addAttribute("listOfDestinationStations", destinationStations);
			return "searchTrain/searchTrain";
		} else {
			List<ITrain> trainList = searchTrainDAO.searchTrains(searchTrain);
			List<IStation> listOfStation = stationDAO.getAllStation();

			for (IStation station : listOfStation) {
				if (station.getStationId() == Integer.parseInt(searchTrain.getSourceStation())) {
					sourceStation = station;
				} else if (station.getStationId() == Integer.parseInt(searchTrain.getDestinationStation())) {
					destinationStation = station;
				}
			}
			List<ITrain> trainListWithFairCalculation = trainFilterAndCalculateFair.filterTrain(trainList, searchTrain,
					routeDAO, dayCalculation);

			if (trainListWithFairCalculation.size() <= 0) {
				model.addAttribute("noTrain", true);
			} else {
				availableSeats.findAvailableSeats(trainListWithFairCalculation, searchTrain, seatAvaillibilityDAO);
				model.addAttribute("listOfTrain", trainListWithFairCalculation);
				model.addAttribute("sourceStation", sourceStation);
				model.addAttribute("destinationStation", destinationStation);
				model.addAttribute("noTrain", false);
				model.addAttribute("reservationInformation", reservation);
			}
			return "searchTrain/listOfSearchTrain";
		}
	}

}
