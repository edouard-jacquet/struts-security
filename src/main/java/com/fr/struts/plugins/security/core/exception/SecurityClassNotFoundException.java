package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand la classe associée à un filtre n'existe pas.
 */
public class SecurityClassNotFoundException extends SecurityException {

	private static final long serialVersionUID = -3524159687889432610L;

	public SecurityClassNotFoundException() {
		super();
	}

	public SecurityClassNotFoundException(String message) {
		super(message);
	}

}
