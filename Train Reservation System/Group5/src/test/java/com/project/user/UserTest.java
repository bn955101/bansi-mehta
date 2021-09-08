package com.project.user;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.springframework.util.Assert;

@SuppressWarnings("deprecation")
public class UserTest {
	UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
	IUser user = userAbstractFactory.createUser();

	@Test
	public void testGetRole() {
		user.setRole("USER");

		assertEquals("USER", user.getRole());
	}

	@Test
	public void testSetRole() {
		user.setRole("USER");

		assertEquals("USER", user.getRole());
	}

	@Test
	public void testGetId() {
		user.setId(5);

		assertEquals(5, user.getId());
	}

	@Test
	public void testSetId() {
		user.setId(5);

		assertEquals(5, user.getId());
	}

	@Test
	public void testGetUserName() {
		user.setUserName("dhara@gmail.com");

		assertEquals("dhara@gmail.com", user.getUserName());
	}

	@Test
	public void testSetUserName() {
		user.setUserName("dhara@gmail.com");

		assertEquals("dhara@gmail.com", user.getUserName());
	}

	@Test
	public void testGetPassword() {
		user.setPassword("Dhara");

		assertEquals("Dhara", user.getPassword());
	}

	@Test
	public void testSetPassword() {
		user.setPassword("Dhara");

		assertEquals("Dhara", user.getPassword());
	}
	
	@Test
	public void testGetConfirmPassword() {
		user.setConfirmPassword("Dhara");

		assertEquals("Dhara", user.getConfirmPassword());
	}

	@Test
	public void testSetConfirmPassword() {
		user.setConfirmPassword("Dhara");

		assertEquals("Dhara", user.getConfirmPassword());
	}
	
	@Test
	public void testSetFirstName() {
		user.setFirstName("Dhara");

		assertEquals("Dhara", user.getFirstName());
	}

	@Test
	public void testGetFirstName() {
		user.setFirstName("Dhara");

		assertEquals("Dhara", user.getFirstName());
	}

	@Test
	public void testGetLastName() {
		user.setLastName("Gohil");

		assertEquals("Gohil", user.getLastName());
	}

	@Test
	public void testSetLastName() {
		user.setLastName("Gohil");

		assertEquals("Gohil", user.getLastName());
	}

	@Test
	public void testGetIsEnabled() {
		user.setEnabled(true);

		assertEquals(true, user.isEnabled());
	}

	@Test
	public void testSetIsEnabled() {
		user.setEnabled(true);

		assertEquals(true, user.isEnabled());
	}

	@Test
	public void testGetDateOfBirth() {
		String dateStr = "2000-04-06";

		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

			user.setDateOfBirth(date);

			assertEquals(date, user.getDateOfBirth());
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testSetDateOfBirth() {
		String dateStr = "2000-04-06";

		try {
			Date date = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);

			user.setDateOfBirth(date);

			assertEquals(date, user.getDateOfBirth());
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testGetMobileNumber() {
		user.setMobileNumber("9933562165");

		assertEquals("9933562165", user.getMobileNumber());
	}

	@Test
	public void testSetMobileNumber() {
		user.setMobileNumber("9933562165");

		assertEquals("9933562165", user.getMobileNumber());
	}

	@Test
	public void testPasswordValidation() {
		assertTrue(user.passwordValidation("Dhara", "Dhara"));
		assertFalse(user.passwordValidation("Dhara", "Hello"));
		assertFalse(user.passwordValidation("Dhara", null));
	}

	@Test
	public void testEmailValidation() {
		assertTrue(user.emailValidation("dhara@gmail.com"));
		assertFalse(user.emailValidation(null));
		assertFalse(user.emailValidation(""));
		assertFalse(user.emailValidation("@gmail.com"));
	}

	@Test
	public void testDateValidation() {
		String trueDateStr = "2000-04-06";
		String falseDateStr = "2025-04-02";

		try {
			Date trueDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(trueDateStr);
			Date falseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(falseDateStr);

			Assert.isTrue(user.dateValidation(trueDate));
			assertFalse(user.dateValidation(falseDate));
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testIsStringNullOrEmpty() {
		assertEquals(false, user.isStringNullOrEmpty("Dhara"));
		assertEquals(true, user.isStringNullOrEmpty(""));
		assertEquals(true, user.isStringNullOrEmpty(null));
	}

	@Test
	public void testIsFirstNameValid() {
		assertEquals(false, user.isFirstNameValid("Dhara"));
		assertEquals(true, user.isFirstNameValid(""));
		assertEquals(true, user.isFirstNameValid(null));
	}

	@Test
	public void testIsLastNameValid() {
		assertEquals(false, user.isLastNameValid("Gohil"));
		assertEquals(true, user.isLastNameValid(null));
		assertEquals(true, user.isLastNameValid(""));
	}

	@Test
	public void testIsEmailValid() {
		assertEquals(true, user.isEmailIdValid("dhara@gmail.com"));
		assertEquals(false, user.isEmailIdValid(null));
		assertEquals(false, user.isEmailIdValid(""));
	}

	@Test
	public void testIsPasswordEmpty() {
		assertEquals(false, user.isPasswordEmpty("Dhara"));
		assertEquals(true, user.isPasswordEmpty(null));
		assertEquals(true, user.isPasswordEmpty(""));
	}

	@Test
	public void testIsConfirmPasswordEmpty() {
		assertEquals(false, user.isPasswordEmpty("Dhara"));
		assertEquals(true, user.isPasswordEmpty(null));
		assertEquals(true, user.isPasswordEmpty(""));
	}

	@Test
	public void testIsDateValid() {
		String trueDateStr = "2000-04-06";
		String falseDateStr = "2025-04-02";

		try {
			Date trueDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(trueDateStr);
			Date falseDate = (Date) new SimpleDateFormat("yyyy-MM-dd").parse(falseDateStr);

			assertEquals(true, user.isDateValid(trueDate));
			assertEquals(false, user.isDateValid(falseDate));
		} catch (ParseException exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void testIsPasswordValid() {
		assertEquals(true, user.isPasswordValid("Dhara", "Dhara"));
		assertEquals(false, user.isPasswordValid("Dhara", "Hello"));
	}

	@Test
	public void testIsPhoneNumberValid() {
		assertEquals(false, user.isPhoneNumberValid("9945825422"));
		assertEquals(true, user.isPhoneNumberValid(""));
		assertEquals(true, user.isPhoneNumberValid(null));
	}

	@Test
	public void testIsQuestionValid() {
		assertEquals(false, user.isQuestionValid("favorite food?", "favorite food?"));
		assertEquals(true, user.isQuestionValid("favorite food?", "favorite sport?"));
	}

	@Test
	public void testIsAnswerValid() {
		assertEquals(true, user.isAnswerValid(""));
		assertEquals(false, user.isAnswerValid("Hello"));
	}

	@Test
	public void testSetQuestionOne() {
		user.setQuestionOne("favorite food?");

		assertEquals("favorite food?", user.getQuestionOne());
	}

	@Test
	public void testGetQuestionOne() {
		user.setQuestionOne("favorite food?");

		assertEquals("favorite food?", user.getQuestionOne());
	}

	@Test
	public void testGetAnswerOne() {
		user.setAnswerOne("khaman");

		assertEquals("khaman", user.getAnswerOne());
	}

	@Test
	public void testSetAnswerOne() {
		user.setAnswerOne("khaman");

		assertEquals("khaman", user.getAnswerOne());
	}

	@Test
	public void testGetQuestionTwo() {
		user.setQuestionOne("favorite sport?");

		assertEquals("favorite sport?", user.getQuestionOne());
	}

	@Test
	public void testSetQuestionTwo() {
		user.setQuestionOne("favorite sport?");

		assertEquals("favorite sport?", user.getQuestionOne());
	}

	@Test
	public void testGetAnswerTwo() {
		user.setAnswerTwo("cricket");

		assertEquals("cricket", user.getAnswerTwo());
	}

	@Test
	public void testSetAnswerTwo() {
		user.setAnswerTwo("cricket");

		assertEquals("cricket", user.getAnswerTwo());
	}

}
