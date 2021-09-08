package com.project.lookup;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;
import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.setup.ITrain;
import com.project.setup.SetupAbstractFactory;

@Component
public class SearchTrainDAO implements ISearchTrainDAO {
	public final String TRAIN_ID = "trainId";
	public final String TRAIN_CODE = "trainCode";
	public final String TRAIN_NAME = "trainName";
	public final String TRAIN_TYPE = "trainType";
	public final String DAYS_NAME = "days";
	public final String DEPARTURE_TIME = "departureTime";
	public final String TOTAL_COACHES = "totalCoaches";
	public final String START_STATION = "startStation";
	public final String MIDDLE_STATION = "middleStations";
	public final String END_STATION = "endStation";

	@Override
	public List<ITrain> searchTrains(ISearchTrain searchTrain) {
		List<ITrain> trains = new ArrayList<ITrain>(0);
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		SetupAbstractFactory setupAbstractFactory = SetupAbstractFactory.instance();
		IDatabaseUtilities databaseUtilities = databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;

		try {
			statement = connection.prepareCall("{call searchTrain(? ,? , ? , ? , ?)}");
			statement.setInt(1, Integer.parseInt(searchTrain.getSourceStation()));
			statement.setInt(2, Integer.parseInt(searchTrain.getDestinationStation()));
			String searchStringForSourceStation = '%' + searchTrain.getSourceStation() + '%';
			String searchStringForDestinationStation = '%' + searchTrain.getDestinationStation() + '%';

			statement.setString(3, searchStringForSourceStation);
			statement.setString(4, searchStringForDestinationStation);
			statement.setString(5, searchTrain.getTrainType());
			boolean hadResultsForList = statement.execute();

			if (hadResultsForList) {
				resultSet = statement.getResultSet();
				while (resultSet.next()) {
					List<Integer> allStations = new ArrayList<Integer>();
					ITrain train = setupAbstractFactory.createNewTrain();

					train.setTrainId(resultSet.getInt(TRAIN_ID));
					train.setTrainCode(resultSet.getInt(TRAIN_CODE));
					train.setTrainName(resultSet.getString(TRAIN_NAME));
					train.setTrainType(resultSet.getString(TRAIN_TYPE));
					train.setDays(resultSet.getString(DAYS_NAME));
					train.setDepartureTime(resultSet.getString(DEPARTURE_TIME));
					train.setTotalCoaches(resultSet.getInt(TOTAL_COACHES));
					train.setStartStation(resultSet.getString(START_STATION));
					allStations.add(Integer.parseInt(train.getStartStation()));
					train.setMiddleStations(resultSet.getString(MIDDLE_STATION));
					if (train.getMiddleStations() == null) {
					} else {
						String[] middleStationsList = train.getMiddleStations().split(",");

						for (String middleStation : middleStationsList) {
							allStations.add(Integer.parseInt(middleStation));
						}
					}
					train.setEndStation(resultSet.getString(END_STATION));
					allStations.add(Integer.parseInt(train.getEndStation()));
					train.setTotalStation(allStations);
					trains.add(train);
				}
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeConnection(connection);
		}
		return trains;
	}

}
