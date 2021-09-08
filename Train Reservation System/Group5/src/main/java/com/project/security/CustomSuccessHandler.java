package com.project.security;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

public class CustomSuccessHandler implements AuthenticationSuccessHandler {
	// reference taken for spring security from
	// "https://www.codejava.net/frameworks/spring-boot/spring-security-custom-login-page"
	// for role based authentication "https://www.youtube.com/watch?v=_TrpJzuK-p4"
	private String redirectUrl = null;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

		for (GrantedAuthority grantedAuthority : authorities) {
			if (grantedAuthority.getAuthority().equals("USER")) {
				redirectUrl = "/user/home";
				break;
			} else if (grantedAuthority.getAuthority().equals("ADMIN")) {
				redirectUrl = "/admin/home";
				break;
			}
		}
		if (redirectUrl == null) {
			throw new IllegalStateException();
		}
		new DefaultRedirectStrategy().sendRedirect(request, response, redirectUrl);
	}

}
