package com.project.setup;

import java.util.List;

public interface ITrainDAO {

	List<Train> getAllTrain();

	boolean saveTrain(ITrain train);

	ITrain getTrain(Integer trainId);

	void deleteTrain(Integer trainId);

}
