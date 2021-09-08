package com.project.ticketCancellation;

public abstract class CancelTicketAbstractFactory {
	private static CancelTicketAbstractFactory instance = null;

	public static CancelTicketAbstractFactory instance() {
		if (null == instance) {
			instance = new CancelTicketConcreteFactory();
		}
		return instance;
	}

	public abstract ISearchPassengerInformationDAO createSearchPassengerInfo();

	public abstract ISearchPassengerInformationDAO createNewSearchPassengerInfo();

	public abstract ICalculateAmount createCalculateAmounts();

	public abstract ICalculateAmount createNewCalculateAmounts();

}
