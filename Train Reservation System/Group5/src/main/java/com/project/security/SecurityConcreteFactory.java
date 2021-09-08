package com.project.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.project.user.IUser;

public class SecurityConcreteFactory extends SecurityAbstractFactory {
	private UserDetailsService userDetailService;
	private BCryptPasswordEncoder passwordEncoder;
	private DaoAuthenticationProvider authenticationProvider;
	private AuthenticationSuccessHandler successHandler;
	private SecurityQuestion securityQuestion;
	private UserDetails userDetail;

	public UserDetailsService createUserDetailsService() {
		if (userDetailService == null) {
			userDetailService = new UserDetailsServiceImpl();
		}
		return userDetailService;
	}

	public UserDetailsService createNewUserDetailsService() {
		return new UserDetailsServiceImpl();
	}

	public BCryptPasswordEncoder createPasswordEncoder() {
		if (passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder();
		}
		return passwordEncoder;
	}

	public BCryptPasswordEncoder createNewPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	public DaoAuthenticationProvider createAuthenticationprovider() {
		if (authenticationProvider == null) {
			authenticationProvider = new DaoAuthenticationProvider();
		}
		return authenticationProvider;
	}

	public DaoAuthenticationProvider createNewAuthenticationprovider() {
		return new DaoAuthenticationProvider();
	}

	public AuthenticationSuccessHandler createCustomeSuccessHandler() {
		if (successHandler == null) {
			successHandler = new CustomSuccessHandler();
		}
		return successHandler;
	}

	public AuthenticationSuccessHandler createNewCustomeSuccessHandler() {
		return new CustomSuccessHandler();
	}

	public UserDetails createMyUserDetail(IUser user) {
		if (userDetail == null) {
			userDetail = new MyUserDetails(user);
		}
		return userDetail;
	}

	public UserDetails createNewMyUserDetail(IUser user) {
		return new MyUserDetails(user);
	}

	public SecurityQuestion createSecurityQuestion() {
		if (securityQuestion == null) {
			securityQuestion = new SecurityQuestion();
		}
		return securityQuestion;
	}

}
