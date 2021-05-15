package com.fr.struts.plugins.security.core.context.strategy;

import com.fr.struts.plugins.security.core.context.SecurityContext;

public interface SecurityContextHolderStrategy {

	/**
	 * Récupère le contexte de sécurité.
	 *
	 * @return le contexte.
	 */
	public SecurityContext getContext();

	/**
	 * Défini le contexte de sécurité.
	 *
	 * @param securityContext le contexte.
	 */
	public void setContext(SecurityContext securityContext);

	/**
	 * Nettoie le contexte de sécurité.
	 */
	public void clearContext();

	/**
	 * Créer un nouveau contexte de sécurité.
	 *
	 * @return le nouveau contexte.
	 */
	public SecurityContext createEmptyContext();

}
