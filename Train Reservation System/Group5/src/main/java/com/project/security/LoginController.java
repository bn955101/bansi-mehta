package com.project.security;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;

@Controller
public class LoginController {
	private final String SECURITY_QUESTION_ONE = "securityQuestionOne";
	private final String SECURITY_QUESTION_TWO = "securityQuestionTwo";
	private final String ANSWER_ONE = "answerone";
	private final String ANSWER_TWO = "answertwo";
	private final String USERNAME = "userName";
	private final String PASSWORD = "password";
	private final String CONFIRM_PASSWORD = "confirmPassword";

	@GetMapping("/admin/home")
	public String viewHomePageForAdmin(Model model) {
		return "home";
	}

	@RequestMapping("/login")
	public String showLoginPage() {
		return "login";
	}

	@GetMapping("/user/forgotpassword")
	public String showForgotPasswordForm(Model model) {
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();

		model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
		return "forgotPassword";
	}

	@PostMapping("/user/forgotpassword")
	public String forgotPassword(@RequestParam(name = SECURITY_QUESTION_ONE) String securityQuestionTwo,
			@RequestParam(name = SECURITY_QUESTION_TWO) String securityQuestionOne,
			@RequestParam(name = ANSWER_ONE) String answerOne, @RequestParam(name = ANSWER_TWO) String answerTwo,
			@RequestParam(name = USERNAME) String userName, Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		SecurityAbstractFactory securityAbstractFactory = SecurityAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		SecurityQuestion securityQuestions = securityAbstractFactory.createSecurityQuestion();
		boolean hasError = false;

		if (user.isEmailIdValid(userName) == false) {
			model.addAttribute("emailError", true);
			hasError = true;
		}
		if (user.isQuestionValid(securityQuestionOne, securityQuestionTwo) == false) {
			model.addAttribute("securityQuestionError", true);
			hasError = true;
		}
		if (user.isAnswerValid(answerOne) == true) {
			model.addAttribute("answerErrorOne", true);
			hasError = true;
		}
		if (user.isAnswerValid(answerTwo) == true) {
			model.addAttribute("answerErrorTwo", true);
			hasError = true;
		}
		if (hasError) {
			model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
			return "forgotPassword";
		} else {
			if (userDAO.isUserExists(userName) == false) {
				model.addAttribute("userExists", true);
				model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
				return "forgotPassword";
			} else {
				user.setUserName(userName);
				user.setAnswerOne(answerOne);
				user.setAnswerTwo(answerTwo);
				user.setQuestionOne(securityQuestionOne);
				user.setQuestionTwo(securityQuestionTwo);
				if (userDAO.isUserPresentWithSameQuestionAndAnswer(user) == true) {
					model.addAttribute("userName", user.getUserName());
					return "newPassword";
				} else {
					model.addAttribute("questionAnswerError", true);
					model.addAttribute("securityQuestions", securityQuestions.getSecurituQuestions());
					return "forgotPassword";
				}
			}
		}
	}

	@PostMapping("/user/setNewPassword")
	public String setNewPassword(@RequestParam(name = PASSWORD) String password,
			@RequestParam(name = CONFIRM_PASSWORD) String confirmPassword,
			@RequestParam(name = USERNAME) String userName, Model model) {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		boolean hasError = false;

		if (user.isPasswordValid(password, confirmPassword) == false) {
			model.addAttribute("passwordError", true);
			hasError = true;
		}
		if (hasError) {
			return "newPassword";
		} else {
			user.setUserName(userName);
			user.setPassword(password);
			userDAO.updatePassword(user);
			return "login";
		}
	}

	@RequestMapping("/login-error")
	public String loginError(Model model) {
		model.addAttribute("loginError", true);
		return "login";
	}

}
