package com.fr.struts.plugins.security.core.authentication.token;

import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.bean.Authority;

public class BasicAuthenticationToken extends AbstractAuthenticationToken implements Authentication {

	private static final long serialVersionUID = -822715745752245362L;

	private final transient Object principal;
	private transient Object credentials;

	public BasicAuthenticationToken(Object principal, Object credentials) {
		this(principal, credentials, null);
	}

	public BasicAuthenticationToken(Object principal, Object credentials, Authority authority) {
		super(authority);
		this.principal = principal;
		this.credentials = credentials;
		this.setAuthenticated(true);
	}

	@Override
	public Object getPrincipal() {
		return this.principal;
	}

	@Override
	public Object getCredentials() {
		return this.credentials;
	}

}
