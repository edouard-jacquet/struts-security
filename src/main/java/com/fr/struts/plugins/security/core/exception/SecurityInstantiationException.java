package com.fr.struts.plugins.security.core.exception;

/**
 * Remontée quand l'instanciation filtre n'est pas possible.
 */
public class SecurityInstantiationException extends SecurityException {

	private static final long serialVersionUID = 4601927566030525878L;

	public SecurityInstantiationException() {
		super();
	}

	public SecurityInstantiationException(String message) {
		super(message);
	}

}
