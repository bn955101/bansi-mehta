package com.project.security;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.util.Assert;

public class CustomSuccessHandlerTest implements AuthenticationSuccessHandler {

	@SuppressWarnings("deprecation")
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			Assert.isTrue(grantedAuthority.getAuthority().equals("USER"));
		}
	}

}
