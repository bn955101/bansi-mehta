package com.project.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;

@Controller
public class SignupController {
	private final String DATE_OF_BIRTH = "dateOfBirth";

	@ModelAttribute("user")
	public IUser getIUserModelObject(HttpServletRequest request) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();

		return user;
	}

	@RequestMapping("/signup")
	public String signUpPage(Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IUser user = userAbstractFactory.createUser();
		SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();

		model.addAttribute(user);
		model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
		return "signup";
	}

	@RequestMapping(value = "/signup/save", method = RequestMethod.POST)
	public String signUpPage(@ModelAttribute("user") IUser user,
			@RequestParam(name = DATE_OF_BIRTH, defaultValue = "2019-01-01") @DateTimeFormat(pattern = "yyyy-MM-dd") Date dateOfBirth,
			Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();

		boolean hasError = false;

		if (user.isFirstNameValid(user.getFirstName()) == true || user.isLastNameValid(user.getFirstName()) == true) {
			model.addAttribute("nameError", true);
			hasError = true;
		}
		if (user.isDateValid(dateOfBirth) == false) {
			model.addAttribute("dobError", true);
			hasError = true;
		}
		if (user.isPhoneNumberValid(user.getMobileNumber()) == true) {
			model.addAttribute("mobileError", true);
			hasError = true;
		}
		if (user.isQuestionValid(user.getQuestionOne(), user.getQuestionTwo()) == false) {
			model.addAttribute("securityQuestionError", true);
			hasError = true;
		}
		if (user.isAnswerValid(user.getAnswerOne()) == true) {
			model.addAttribute("answerErrorOne", true);
			hasError = true;
		}
		if (user.isAnswerValid(user.getAnswerTwo()) == true) {
			model.addAttribute("answerErrorTwo", true);
			hasError = true;
		}
		if (user.isEmailIdValid(user.getUserName()) == false) {
			model.addAttribute("emailError", true);
			hasError = true;
		}
		if (user.isPasswordValid(user.getPassword(), user.getConfirmPassword()) == false) {
			model.addAttribute("passwordError", true);
			hasError = true;
		}
		if (userDAO.isUserExists(user.getUserName()) == true) {
			model.addAttribute("userExists", true);
			hasError = true;
		}
		if (hasError) {
			IUser newUser = userAbstractFactory.createUser();
			SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();

			model.addAttribute(newUser);
			model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
			return "signup";
		} else {
			userDAO.saveUser(user);
			return "redirect:/login";
		}
	}

}
