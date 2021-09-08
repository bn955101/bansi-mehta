package com.project.setup;

import java.sql.Date;

public class CancelTrain implements ICancelTrain{
	private int trainCode;
	private Date cancellationDate;
	
	public CancelTrain() {}

	@Override
	public int getTrainCode() {
		return trainCode;
	}

	@Override
	public void setTrainCode(int trainCode) {
		this.trainCode = trainCode;
	}

	@Override
	public Date getCancellationDate() {
		return cancellationDate;
	}

	@Override
	public void setCancellationDate(Date cancellationDate) {
		this.cancellationDate = cancellationDate;
	}
	
}
