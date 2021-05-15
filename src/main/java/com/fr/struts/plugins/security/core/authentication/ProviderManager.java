package com.fr.struts.plugins.security.core.authentication;

import com.fr.struts.plugins.security.core.context.SecurityContextHolder;

public class ProviderManager implements AuthenticationManager {

	@Override
	public Authentication authenticate(Authentication authentication) {
		SecurityContextHolder.getContext().setAuthentication(authentication);

		return authentication;
	}

	@Override
	public Authentication refresh(Authentication authentication) {
		return this.authenticate(authentication);
	}

	@Override
	public void invalidate() {
		SecurityContextHolder.getContext().setAuthentication(null);
		SecurityContextHolder.clearContext();
	}

	@Override
	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

}
