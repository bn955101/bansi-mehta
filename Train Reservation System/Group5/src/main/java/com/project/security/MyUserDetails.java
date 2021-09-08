package com.project.security;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import com.project.user.IUser;

public class MyUserDetails implements UserDetails {
	// reference taken for spring security from
	// "https://www.codejava.net/frameworks/spring-boot/spring-security-custom-login-page"
	// for role based authentication "https://www.youtube.com/watch?v=_TrpJzuK-p4"
	private static final long serialVersionUID = 1L;
	private IUser user;

	public MyUserDetails(IUser user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		String role = user.getRole();
		List<SimpleGrantedAuthority> authorities = new ArrayList<SimpleGrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority(role));
		return authorities;
	}

	@Override
	public String getPassword() {
		return user.getPassword();
	}

	@Override
	public String getUsername() {
		return user.getUserName();
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
		return user.isEnabled();
	}

}
