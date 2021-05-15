package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand l'utilisateur n'est pas connecté.
 */
public class UnauthorizedException extends SecurityException {

	private static final long serialVersionUID = 932999862188381706L;

	public UnauthorizedException() {
		super();
	}

	public UnauthorizedException(String trace) {
		super(trace);
	}

}
