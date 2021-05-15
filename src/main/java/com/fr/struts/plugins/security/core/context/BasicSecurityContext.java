package com.fr.struts.plugins.security.core.context;

import com.fr.struts.plugins.security.core.authentication.Authentication;

public class BasicSecurityContext implements SecurityContext {

	private static final long serialVersionUID = 8158637333182433058L;

	private Authentication authentication;

	public BasicSecurityContext() {

	}

	@Override
	public Authentication getAuthentication() {
		return this.authentication;
	}

	@Override
	public void setAuthentication(Authentication authentication) {
		this.authentication = authentication;
	}

}
