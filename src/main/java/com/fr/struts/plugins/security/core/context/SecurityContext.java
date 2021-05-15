package com.fr.struts.plugins.security.core.context;

import java.io.Serializable;

import com.fr.struts.plugins.security.core.authentication.Authentication;

public interface SecurityContext extends Serializable {

	/**
	 * Retourne l'authentification liée à l'utilisateur.
	 *
	 * @return l'authentification liée.
	 */
	public Authentication getAuthentication();

	/**
	 * Défini l'authentification liée à l'utilisateur.
	 *
	 * @param authentication l'authentification à lier.
	 */
	public void setAuthentication(Authentication authentication);

}
