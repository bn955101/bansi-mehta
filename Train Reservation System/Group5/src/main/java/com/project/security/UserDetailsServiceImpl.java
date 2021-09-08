package com.project.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;

public class UserDetailsServiceImpl implements UserDetailsService {
	// reference taken for spring security from
	// "https://www.codejava.net/frameworks/spring-boot/spring-security-custom-login-page"
	// for role based authentication "https://www.youtube.com/watch?v=_TrpJzuK-p4"
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		user = userDAO.getUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Could not find User");
		}
		return new MyUserDetails(user);
	}

}
