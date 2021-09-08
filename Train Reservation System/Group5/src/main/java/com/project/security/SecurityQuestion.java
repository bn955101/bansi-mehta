package com.project.security;

import java.util.HashMap;
import java.util.Map;

public class SecurityQuestion {
	Map<Integer, String> securityQuestions = new HashMap<Integer, String>();

	public SecurityQuestion() {
		securityQuestions.put(1, "What is your favorite cricketer?");
		securityQuestions.put(2, "What is your favorite food?");
		securityQuestions.put(3, "What is your mother's maiden name?");
		securityQuestions.put(4, "What was your first car?");
		securityQuestions.put(5, "Who was your childhood hero?");
		securityQuestions.put(6, "Where was your best family vacation as a kid?");
		securityQuestions.put(7, "Where did you go to high school/college?");
		securityQuestions.put(8, "What city were you born in?");
		securityQuestions.put(9, "Where is your favorite place to vacation?");
	}

	public Map<Integer, String> getSecurituQuestions() {
		return securityQuestions;
	}

}
