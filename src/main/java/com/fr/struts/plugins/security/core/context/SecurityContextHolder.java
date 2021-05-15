package com.fr.struts.plugins.security.core.context;

import com.fr.struts.plugins.security.core.context.strategy.SecurityContextHolderStrategy;
import com.fr.struts.plugins.security.core.context.strategy.ThreadLocalSecurityContextHolderStrategy;

public final class SecurityContextHolder {

	private static SecurityContextHolderStrategy strategy;

	private SecurityContextHolder() {

	}

	static {
		SecurityContextHolder.initialize();
	}

	/**
	 * Récupère le contexte de sécurité.
	 *
	 * @return le contexte.
	 */
	public static SecurityContext getContext() {
		return SecurityContextHolder.strategy.getContext();
	}

	/**
	 * Défini le contexte de sécurité.
	 *
	 * @param securityContext le contexte.
	 */
	public static void setContext(SecurityContext securityContext) {
		SecurityContextHolder.strategy.setContext(securityContext);
	}

	/**
	 * Nettoie le contexte de sécurité.
	 */
	public static void clearContext() {
		SecurityContextHolder.strategy.clearContext();
	}

	/**
	 * Créer un nouveau contexte de sécurité.
	 *
	 * @return le nouveau contexte.
	 */
	public static SecurityContext createEmptyContext() {
		return SecurityContextHolder.strategy.createEmptyContext();
	}

	private static void initialize() {
		SecurityContextHolder.strategy = new ThreadLocalSecurityContextHolderStrategy();
	}

}
