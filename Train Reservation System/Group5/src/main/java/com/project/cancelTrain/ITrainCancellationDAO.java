package com.project.cancelTrain;

import java.util.List;

import com.project.reservation.IReservation;
import com.project.setup.ICancelTrain;

public interface ITrainCancellationDAO {

	List<IReservation> fetchAllReservations(ICancelTrain cancelTrain);

}