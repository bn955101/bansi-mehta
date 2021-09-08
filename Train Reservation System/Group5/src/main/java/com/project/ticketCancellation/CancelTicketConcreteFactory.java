package com.project.ticketCancellation;

public class CancelTicketConcreteFactory extends CancelTicketAbstractFactory {
	private ISearchPassengerInformationDAO searchPassengerInformation;
	private ICalculateAmount calculateAmount;

	@Override
	public ISearchPassengerInformationDAO createSearchPassengerInfo() {
		if (searchPassengerInformation == null) {
			searchPassengerInformation = new SearchPassengerInformationDAO();
		}
		return searchPassengerInformation;
	}

	@Override
	public ISearchPassengerInformationDAO createNewSearchPassengerInfo() {
		return new SearchPassengerInformationDAO();
	}

	@Override
	public ICalculateAmount createCalculateAmounts() {
		if (calculateAmount == null) {
			calculateAmount = new CalculateAmount();
		}
		return calculateAmount;
	}

	@Override
	public ICalculateAmount createNewCalculateAmounts() {
		return new CalculateAmount();
	}

}
