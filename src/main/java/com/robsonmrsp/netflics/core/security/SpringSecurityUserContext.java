package com.robsonmrsp.netflics.core.security;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import com.robsonmrsp.netflics.model.User;
import com.robsonmrsp.netflics.core.model.Tenant;

/**
 * generated by JSetup v0.95 : at 4 de jan de 2022 23:12:56
 **/
@Component
public class SpringSecurityUserContext implements UserContext {
	private Collection<? extends GrantedAuthority> credentials;

	@Override
	public String getCurrentUserName() {
		User currentUser = getCurrentUser();
		if (currentUser != null) {
			return currentUser.getUsername();
		}
		return "USER_NOT_IN_SESSION";
	}

	public User getCurrentUser() {
		SecurityContext context = SecurityContextHolder.getContext();
		Authentication authentication = context.getAuthentication();
		if (authentication == null) {
			return null;
		}
		User user = (User) authentication.getPrincipal();

		return user;
	}

	@Override
	public void setCurrentUser(User user) {
		if (user == null) {
			throw new IllegalArgumentException("user cannot be null");
		}
		Collection<? extends GrantedAuthority> authorities = UserAuthorityUtils.createAuthorities(user);
		setCredentials(authorities);

		UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(user, user.getPassword(), authorities);
		SecurityContextHolder.getContext().setAuthentication(authentication);
	}

	private void setCredentials(Collection<? extends GrantedAuthority> credentials) {
		this.credentials = credentials;
	}

	public Collection<? extends GrantedAuthority> getUserCredentials() {
		return credentials;
	}
	
	public Tenant getTenant() {
		if (getCurrentUser() != null) {
			return getCurrentUser().getTenant();
		}
		
		return null;
	}
	
}
