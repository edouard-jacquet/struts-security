package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand l'utilisateur n'a pas les bon droits d'accès.
 */
public class ForbiddenException extends SecurityException {

	private static final long serialVersionUID = -4197991365897025375L;

	public ForbiddenException() {
		super();
	}

	public ForbiddenException(String trace) {
		super(trace);
	}

}
