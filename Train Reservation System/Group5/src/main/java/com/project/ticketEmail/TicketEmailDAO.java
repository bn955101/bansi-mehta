package com.project.ticketEmail;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import com.project.database.DatabaseAbstactFactory;
import com.project.database.IDatabaseUtilities;
import com.project.reservation.IPassengerInformation;
import com.project.reservation.ReservationAbstractFactory;

public class TicketEmailDAO implements ITicketEmailDAO{

	public final String TRAIN_CODE = "trainCode";
	public final String TRAIN_NAME = "trainName";
	public final String SOURCE_STATION = "sourceStation";
	public final String DESTINATION_STATION = "destinationStation";
	public final String RESERVATION_DATE = "reservationDate";
	public final String AMOUNT_PAID = "amountPaid";
	public final String TRAIN_TYPE = "trainType";
	public final String FIRST_NAME = "firstName";
	public final String LAST_NAME = "lastName";
	public final String AGE = "age";
	public final String COACH_NUMBER = "coachNumber";
	public final String SEAT_NUMBER = "seatNumber";
	public final String HOST = "smtp.gmail.com";
	public final int PORT = 587;
	public final String USERNAME = "sshahsanket31@gmail.com";
	public final String PASSWORD = "Sanket@31";
	public final String PROTOCOL = "mail.transport.protocol";
	public final String SMTP = "smtp";
	public final String AUTH = "mail.smtp.auth";
	public final String TRUE = "true";
	public final String TTL_ENABLE = "mail.smtp.starttls.enable";
	public final String DEBUG = "mail.debug";
	public final String FROM = "sshahsanket31@gmail.com";
	public final String SUBJECT = "Train Ticket";

	// Reference : https://www.baeldung.com/spring-email
	public JavaMailSender getJavaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    mailSender.setHost(HOST);
	    mailSender.setPort(PORT);
	    
	    mailSender.setUsername(USERNAME);
	    mailSender.setPassword(PASSWORD);
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put(PROTOCOL, SMTP);
	    props.put(AUTH, TRUE);
	    props.put(TTL_ENABLE, TRUE);
	    props.put(DEBUG, TRUE);
	    
	    return mailSender;
	}
	
	@Override
	public ITicketEmail ticketEmail(int reservationId) {
		
		ITicketEmail ticketEmail = getTicketInformation(reservationId);
		ticketEmail.setPassengerInformation(getPassengerInformation(reservationId));
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String userEmail = authentication.getName();
		SimpleMailMessage message = new SimpleMailMessage();
		message.setFrom(FROM);
		message.setTo(userEmail);
		message.setSubject(SUBJECT);
		
		String messageForEmail = "Hi, \n\n Greetings From Railway Reservation System.\n\n Please find below ticket information:";
		messageForEmail += "\n\nPNR Number: "+reservationId;
		messageForEmail+="\n\nTrain Code and Train Name: "+ticketEmail.getTrainCode()+" - "+ticketEmail.getTrainName();
		messageForEmail+="\nTrain Type: "+ticketEmail.getTrainType();
		messageForEmail+="\nSource Station: "+ticketEmail.getSourceStation()
		+" - "+" Destination Station: "+ticketEmail.getDestinationStation();
		
		for(int i=0; i<ticketEmail.getPassengerInformation().size(); i++) {
			messageForEmail+="\n\nPassenger "+String.valueOf(i+1);
			messageForEmail+="\n\nName: "+ticketEmail.getPassengerInformation().get(i).getFirstName()+" "+ticketEmail.getPassengerInformation().get(i).getLastName();
			messageForEmail+="\nAge: "+String.valueOf(ticketEmail.getPassengerInformation().get(i).getAge());
			messageForEmail+="\nCoach Number: "+ticketEmail.getPassengerInformation().get(i).getCoachNumber();
			messageForEmail+="\nSeat Number: "+String.valueOf(ticketEmail.getPassengerInformation().get(i).getPassengerInformationId());
		}
		
		messageForEmail+="\n\nAmount Paid: "+String.valueOf(ticketEmail.getAmountPaid());
		message.setText(messageForEmail);
		getJavaMailSender().send(message);
		return ticketEmail;
	}
	
	private ITicketEmail getTicketInformation(int reservationId) {
		ITicketEmail ticketEmail = new TicketEmail();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		try {
			statement = connection.prepareCall("{call getTicketPrintInformation(?)}");
			statement.setInt(1, reservationId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				ticketEmail.setReservationId(reservationId);
				ticketEmail.setTrainCode(resultSet.getInt(TRAIN_CODE));
				ticketEmail.setTrainName(resultSet.getString(TRAIN_NAME));
				ticketEmail.setSourceStation(resultSet.getString(SOURCE_STATION));
				ticketEmail.setDestinationStation(resultSet.getString(DESTINATION_STATION));
				ticketEmail.setTrainType(resultSet.getString(TRAIN_TYPE));
				ticketEmail.setAmountPaid(resultSet.getDouble(AMOUNT_PAID));
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return ticketEmail;
	}
	
	private List<IPassengerInformation> getPassengerInformation(int reservationId) {
		List<IPassengerInformation> passengerInformation = new ArrayList<>();
		DatabaseAbstactFactory databaseAbstractFactory = DatabaseAbstactFactory.instance();
		IDatabaseUtilities databaseUtilities =  databaseAbstractFactory.createDatabaseUtilities();
		Connection connection = databaseUtilities.establishConnection();
		CallableStatement statement = null;
		ResultSet resultSet = null;
		ReservationAbstractFactory reservationAbstractFactory = ReservationAbstractFactory.instance();
		try {
			statement = connection.prepareCall("{call getPassengerInfo(?)}");
			statement.setInt(1, reservationId);
			resultSet = statement.executeQuery();
			while(resultSet.next()) {
				IPassengerInformation passengerInfo = reservationAbstractFactory.createNewPassengerInformation();
				passengerInfo.setFirstName(resultSet.getString(FIRST_NAME));
				passengerInfo.setLastName(resultSet.getString(LAST_NAME));
				passengerInfo.setAge(resultSet.getInt(AGE));
				passengerInfo.setCoachNumber(resultSet.getString(COACH_NUMBER));
				int seatNumber = resultSet.getInt(SEAT_NUMBER);
				passengerInfo.setPassengerInformationId(seatNumber);
				passengerInformation.add(passengerInfo);
			}
		} catch (SQLException exception) {
			exception.printStackTrace();
		} finally {
			databaseUtilities.closeResultSet(resultSet);
			databaseUtilities.closeStatement(statement);
			databaseUtilities.closeConnection(connection);
		}
		return passengerInformation;
	}

}
