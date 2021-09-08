package com.project.findMyTrain;

import java.util.Date;
import com.project.setup.ITrain;

public interface IFindMyTrainLocation {

	String findMyTrainCalculation(ITrain train, Date startDate, IFindMyTrainDAO findMyTrainDAO);
}
