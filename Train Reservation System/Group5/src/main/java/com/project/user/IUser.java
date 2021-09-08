package com.project.user;

import java.util.Date;

public interface IUser {

	String getRole();

	void setRole(String role);

	int getId();

	void setId(int id);

	String getUserName();

	void setUserName(String userName);

	String getPassword();

	void setPassword(String password);

	String getConfirmPassword();

	void setConfirmPassword(String confirmPassword);

	boolean isEnabled();

	void setEnabled(boolean enabled);

	String getFirstName();

	void setFirstName(String firstName);

	String getLastName();

	void setLastName(String lastName);

	String getGender();

	void setGender(String gender);

	Date getDateOfBirth();

	void setDateOfBirth(Date dateOfBirth);

	String getMobileNumber();

	void setMobileNumber(String mobileNumber);

	boolean passwordValidation(String password, String confirmPassword);

	boolean emailValidation(String email);

	boolean dateValidation(Date date);

	boolean isStringNullOrEmpty(String s);

	boolean isEmailIdValid(String emailId);

	boolean isFirstNameValid(String firstName);

	boolean isLastNameValid(String lastName);

	boolean isPasswordEmpty(String password);

	boolean isConfirmPasswordEmpty(String confirmPassword);

	boolean isPasswordValid(String password, String confirmPassword);

	boolean isDateValid(Date date);

	boolean isQuestionValid(String securityQuestionOne, String securityQuestionTwo);

	boolean isPhoneNumberValid(String number);

	boolean isAnswerValid(String answer);

	String getQuestionOne();

	void setQuestionOne(String questionOne);

	String getAnswerOne();

	void setAnswerOne(String answerOne);

	String getQuestionTwo();

	void setQuestionTwo(String questionTwo);

	String getAnswerTwo();

	void setAnswerTwo(String answerTwo);

}
