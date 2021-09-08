package com.project.security;

import java.util.Collection;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.Assert;
import com.project.user.IUser;
import com.project.user.UserAbstractFactory;

@SuppressWarnings("deprecation")
public class MyUserDetailTest implements UserDetails {
	private static final long serialVersionUID = 1L;
	UserAbstractFactory userAbstractFactory = UserAbstractFactory.instance();
	IUser user = userAbstractFactory.createUser();

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return null;
	}

	@Override
	public String getPassword() {
		user.setPassword("Dhara");
		Assert.isTrue(user.getPassword().equals("Dhara"));
		return "Dhara";
	}

	@Override
	public String getUsername() {
		user.setUserName("dhara@gmail.com");
		Assert.isTrue(user.getUserName().equals("dhara@gmail.com"));
		return "dhara@gmail.com";
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		user.setEnabled(true);
		Assert.isTrue(user.isEnabled() == true);
		return true;
	}

}
