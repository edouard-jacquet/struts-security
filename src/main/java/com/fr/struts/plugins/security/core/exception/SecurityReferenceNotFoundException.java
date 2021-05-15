package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand un filtre référencé n'existe pas.
 */
public class SecurityReferenceNotFoundException extends SecurityException {

	private static final long serialVersionUID = 1471816866789397678L;

	public SecurityReferenceNotFoundException() {
		super();
	}

	public SecurityReferenceNotFoundException(String message) {
		super(message);
	}

}
