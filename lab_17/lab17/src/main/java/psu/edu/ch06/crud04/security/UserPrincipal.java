package psu.edu.ch06.crud04.security;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import psu.edu.ch06.crud04.model.User;

/**
 * Adapter: makes our User record look like the UserDetails that Spring Security expects.
 */
public class UserPrincipal implements UserDetails {

	private static final long serialVersionUID = 1L;

	private final User user;

	public UserPrincipal(User user) {
		this.user = user;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority("USER"));
	}

	@Override
	public String getPassword() {
		return user.password();
	}

	@Override
	public String getUsername() {
		return user.username();
	}

}
