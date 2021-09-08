package com.project.security;

import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import com.project.user.IUser;

public class SecurityConcreteFactoryTest extends SecurityAbstractFactoryTest {
	private UserDetailsService userDetailService;
	private BCryptPasswordEncoder passwordEncoder;
	private DaoAuthenticationProvider authenticationProvider;
	private AuthenticationSuccessHandler successHandler;
	private UserDetails userDetail;

	@Override
	public UserDetailsService createUserDetailsService() {
		if (userDetailService == null) {
			userDetailService = new UserDetailsServiceImpl();
		}
		return userDetailService;
	}

	@Override
	public BCryptPasswordEncoder createPasswordEncoder() {
		if (passwordEncoder == null) {
			passwordEncoder = new BCryptPasswordEncoder();
		}
		return passwordEncoder;
	}

	@Override
	public DaoAuthenticationProvider createAuthenticationprovider() {
		if (authenticationProvider == null) {
			authenticationProvider = new DaoAuthenticationProvider();
		}
		return authenticationProvider;
	}

	@Override
	public AuthenticationSuccessHandler createCustomeSuccessHandler() {
		if (successHandler == null) {
			successHandler = new CustomSuccessHandler();
		}
		return successHandler;
	}

	@Override
	public UserDetails createMyUserDetail(IUser user) {
		if (userDetail == null) {
			userDetail = new MyUserDetails(user);
		}
		return userDetail;
	}

}
