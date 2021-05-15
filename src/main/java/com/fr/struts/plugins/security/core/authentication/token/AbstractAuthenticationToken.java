package com.fr.struts.plugins.security.core.authentication.token;

import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.bean.Authority;

public abstract class AbstractAuthenticationToken implements Authentication {

	private static final long serialVersionUID = -4789471215265340128L;

	protected transient Object details;
	protected Authority authority;
	protected boolean authenticated;

	protected AbstractAuthenticationToken() {
		this.authenticated = false;
	}

	protected AbstractAuthenticationToken(Authority authority) {
		this();
		this.setAuthority(authority);
	}

	@Override
	public Object getDetails() {
		return this.details;
	}

	@Override
	public void setDetails(Object details) {
		this.details = details;
	}

	@Override
	public Authority getAuthority() {
		return this.authority;
	}

	@Override
	public void setAuthority(Authority authority) {
		this.authority = authority;
	}

	@Override
	public boolean isAuthenticated() {
		return this.authenticated;
	}

	@Override
	public void setAuthenticated(boolean authenticated) {
		this.authenticated = authenticated;
	}

}
