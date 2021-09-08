package com.project.user;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class UserMock {

	public IUser createUserMock(IUser user) {
		user.setId(5);
		user.setUserName("dhara@gmail.com");
		user.setFirstName("Dhara");
		user.setLastName("Gohil");
		user.setGender("Female");
		String dateString = "2000-04-06";

		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateString);

			user.setDateOfBirth(date);
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
		user.setEnabled(true);
		user.setMobileNumber("9933562165");
		user.setPassword("Dhara");
		user.setRole("USER");
		return user;
	}

}
