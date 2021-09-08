package com.project.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import com.project.user.IUser;
import com.project.user.IUserDAO;
import com.project.user.UserAbstractFactory;

public class UserDetailsServiceImplTest implements UserDetailsService {

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
		IUserDAO userDAO = userAbstractFactory.createUserDAO();
		IUser user = userAbstractFactory.createUser();
		user = userDAO.getUserByUsername("dhara@gmail.com");
		return new MyUserDetails(user);
	}

}
