package com.project.setup;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TrainController {

	@GetMapping(value = "/admin/train/list")
	public String showTrainPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		List<Train> listOfTrain = trainDAO.getAllTrain();

		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/admin/train")
	public String showTrain(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		List<Train> listOfTrain = trainDAO.getAllTrain();

		model.addAttribute("listOfTrain", listOfTrain);
		return "train/train";
	}

	@GetMapping(value = "/admin/train/add")
	public String showAddTrainPage(Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		ITrain train = setupAbstractFactory.createTrain();

		model.addAttribute("listOfStations", stations);
		model.addAttribute(train);
		return "train/addTrain";
	}

	@PostMapping(value = "/admin/train/save")
	public String saveTrain(@ModelAttribute("train") ITrain train, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();
		boolean validOrNot = true;

		if (train.isTrainCodeInvalid()) {
			model.addAttribute("trainCodeError", true);
			validOrNot = false;
		}
		if (train.isTrainNameInvalid()) {
			model.addAttribute("trainNameError", true);
			validOrNot = false;
		}
		if (train.isTrainTypeInvalid()) {
			model.addAttribute("trainTypeError", true);
			validOrNot = false;
		}
		if (train.isTrainDepartureTimeInvalid()) {
			model.addAttribute("departureTimeError", true);
			validOrNot = false;
		}
		if (train.isTotalCoachesInvalid()) {
			model.addAttribute("coachesError", true);
			validOrNot = false;
		}
		if (train.isStartStationInvalid()) {
			model.addAttribute("startStationError", true);
			validOrNot = false;
		}
		if (train.isEndStationInvalid()) {
			model.addAttribute("endStationError", true);
			validOrNot = false;
		}
		if (train.isSourceStationAndDestinationStationSame()) {
			model.addAttribute("sameStationError", true);
			validOrNot = false;
		}
		if (validOrNot) {
			if (trainDAO.saveTrain(train)) {
				return "redirect:/admin/train/list";
			} else {
				model.addAttribute("trainRouteError", true);
				if(train.getTrainId() > 0) {
					return this.showEditTrainPage(train.getTrainId() ,model );
				}
				else {
					return this.showAddTrainPage(model);
				}
			}
		} else {
			if(train.getTrainId() > 0) {
				return this.showEditTrainPage(train.getTrainId() ,model );
			}
			else {
				return this.showAddTrainPage(model);
			}
		}
	}

	@RequestMapping("/admin/train/edit/{trainId}")
	public String showEditTrainPage(@PathVariable(name = "trainId") Integer trainId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IStationDAO stationDAO = setupAbstractFactory.createStationDAO();
		List<IStation> stations = stationDAO.getAllStation();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();

		model.addAttribute("listOfStations", stations);
		ITrain train = trainDAO.getTrain(trainId);

		model.addAttribute(train);
		String[] daysList = train.getDays().split(",");
		Map<String, String> allDays = new HashMap<>();

		for (String day : daysList) {
			allDays.put(day, "true");
		}
		String[] middleStationsList = train.getMiddleStations().split(",");

		model.addAttribute("listOfMiddleStations", middleStationsList);
		model.addAttribute("listOfDays", allDays);
		return "train/editTrain";
	}

	@ModelAttribute("train")
	public ITrain getITrainModelObject(HttpServletRequest request) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrain train = setupAbstractFactory.createNewTrain();

		return train;
	}

	@RequestMapping("/admin/train/delete/{trainId}")
	public String deleteTrain(@PathVariable(name = "trainId") Integer trainId, Model model) {
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		ITrainDAO trainDAO = setupAbstractFactory.createTrainDAO();

		trainDAO.deleteTrain(trainId);
		return "redirect:/admin/train/list";
	}

}
