package com.fr.struts.plugins.security.core.context.repository;

import com.fr.struts.plugins.security.core.context.HttpHolder;
import com.fr.struts.plugins.security.core.context.SecurityContext;

public interface SecurityContextRepository {

	/**
	 * Charge le contexte de sécurité.
	 *
	 * @param httpHolder le holder contenant les informations HTTP.
	 * @return le contexte.
	 */
	public SecurityContext loadContext(HttpHolder httpHolder);

	/**
	 * Sauvegarde le contexte de sécurité.
	 *
	 * @param securityContext le contexte.
	 * @param httpHolder      le holder contenant les informations HTTP.
	 */
	public void saveContext(SecurityContext securityContext, HttpHolder httpHolder);

}
