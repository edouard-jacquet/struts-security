package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand l'application n'est pas sécurisée.
 */
public class UnsecuredApplicationException extends SecurityException {

	private static final long serialVersionUID = 1158126688902292460L;

	public UnsecuredApplicationException() {
		super();
	}

	public UnsecuredApplicationException(String trace) {
		super(trace);
	}

}
