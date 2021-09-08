package com.project.setup;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class StationController {

	@ModelAttribute("station")
	public IStation getIStationModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStation station = setupAbstractFactory.createNewStation();

		return station;
	}

	@GetMapping(value = "/admin/station/list")
	public String showStationPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> listOfStation = stationDAO.getAllStation();

		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/admin/station")
	public String showStation(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> listOfStation = stationDAO.getAllStation();

		model.addAttribute("listOfStation", listOfStation);
		return "station/station";
	}

	@GetMapping(value = "/admin/station/add")
	public String showAddStationPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStation station = setupAbstractFactory.createNewStation();

		model.addAttribute(station);
		return "station/addStation";
	}

	@PostMapping(value = "/admin/station/addNew/save")
	public String saveNewStation(@ModelAttribute("station") IStation station, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		boolean validOrNot = true;

		if (station.isStationNameInvalid()) {
			model.addAttribute("errorStationName", true);
			validOrNot = false;
		}
		if (station.isStationCodeInvalid()) {
			model.addAttribute("errorStationCode", true);
			validOrNot = false;
		}
		if (station.isStationCityInvalid()) {
			model.addAttribute("errorStationCity", true);
			validOrNot = false;
		}
		if (station.isStationStateInvalid()) {
			model.addAttribute("errorStationState", true);
			validOrNot = false;
		}
		if (validOrNot) {
			boolean isUnique = stationDAO.save(station);

			if (isUnique) {
				return "redirect:/admin/station/list";
			} else {
				model.addAttribute("errorUnique", true);
				return "station/addStation";
			}
		} else {
			return "station/addStation";
		}
	}

	@PostMapping(value = "/admin/station/edit/save")
	public String saveUpdatedStation(@Valid @ModelAttribute("station") IStation station, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		boolean validOrNot = true;

		if (station.isStationNameInvalid()) {
			model.addAttribute("errorStationName", true);
			validOrNot = false;
		}
		if (station.isStationCodeInvalid()) {
			model.addAttribute("errorStationCode", true);
			validOrNot = false;
		}
		if (station.isStationCityInvalid()) {
			model.addAttribute("errorStationCity", true);
			validOrNot = false;
		}
		if (station.isStationStateInvalid()) {
			model.addAttribute("errorStationState", true);
			validOrNot = false;
		}
		if (validOrNot) {
			boolean isUnique = stationDAO.save(station);

			if (isUnique) {
				return "redirect:/admin/station/list";
			} else {
				model.addAttribute("errorUnique", true);
				return "station/editStation";
			}
		} else {
			return "station/editStation";
		}
	}

	@RequestMapping("/admin/station/edit/{stationId}")
	public String showEditStationPage(@PathVariable(name = "stationId") Integer stationId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		IStation station = stationDAO.getStation(stationId);

		model.addAttribute(station);
		return "station/editStation";
	}

	@RequestMapping("/admin/station/delete/{stationId}")
	public String deleteStation(@PathVariable(name = "stationId") Integer stationId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();

		stationDAO.delete(stationId);
		return "redirect:/admin/station/list";
	}

}
