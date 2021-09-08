package com.project.findMyTrain;


import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import com.project.lookup.ISearchTrain;
import com.project.lookup.LookupAbstractFactory;
import com.project.setup.ITrain;

@Controller
public class FindMyTrainController {
	private final String TRAIN_CODE = "trainCode";
	private final String START_DATE = "startDate";

	@RequestMapping("/findMyTrain")
	public String findMyTrain(Model model) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain = lookupAbstractFactory.createNewSearchTrain();
		
		model.addAttribute(searchTrain);
		return "findMyTrain/findMyTrain";
	}
	
	@RequestMapping(value = "/findMyTrain/location", method = RequestMethod.POST)
	public String findLocationOfTrain(@RequestParam(name = TRAIN_CODE) String trainCodeString,
								@RequestParam(name = START_DATE, defaultValue = "2021-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date startDate,
								Model model) {	
		FindMyTrainAbstractFactory findMyTrainAbstractFactory = FindMyTrainAbstractFactory.instance();
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		IFindMyTrainDAO findMyTrainDAO = findMyTrainAbstractFactory.createFindMyTrainDAO();
		IFindMyTrainLocation findMyTrainLocation = findMyTrainAbstractFactory.createFindMyTrainLocation();
		IDistanceData distanceData = findMyTrainAbstractFactory.createDistanceData();
		boolean hasError = false;
		
		if (distanceData.isTrainCodeValid(trainCodeString) == true || distanceData.isDateValid(startDate.toString()) == true) {
			model.addAttribute("nameError", true);
			hasError = true;
		}	
		if (hasError) {
			ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
			
			model.addAttribute(searchTrain);
			return "findMyTrain/findMyTrain";
		}
		else {
			int trainCode = Integer.parseInt(trainCodeString);
			ITrain train = findMyTrainDAO.getLiveTrainStatus(trainCode, startDate);
			String trainLocation = findMyTrainLocation.findMyTrainCalculation(train, startDate, findMyTrainDAO);
			
			model.addAttribute("trainLocation", trainLocation);
			return "findMyTrain/displayLocation";
		}
	}
	
	@RequestMapping(value = "/findMyTrain/location/done", method = RequestMethod.POST)
	public String findLocationDone(Model model) {
		LookupAbstractFactory lookupAbstractFactory = LookupAbstractFactory.instance();
		ISearchTrain searchTrain =lookupAbstractFactory.createNewSearchTrain();
		
		model.addAttribute(searchTrain);
		return "redirect:/user/home";
	}
	
}
