package com.project.setup;

import java.sql.Date;

public interface ICancelTrain {

	int getTrainCode();

	void setTrainCode(int trainCode);

	Date getCancellationDate();

	void setCancellationDate(Date cancellationDate);

}