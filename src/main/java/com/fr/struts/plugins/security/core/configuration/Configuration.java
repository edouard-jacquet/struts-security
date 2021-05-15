package com.fr.struts.plugins.security.core.configuration;

import java.util.Map;

import com.fr.struts.plugins.security.core.authentication.AuthenticationManager;
import com.fr.struts.plugins.security.core.filter.stack.FilterStack;

public class Configuration {

	private FilterStack filterStack;
	private Map<String, ExceptionConfiguration> exceptionMappings;
	private AuthenticationManager authenticationManager;

	public Configuration() {

	}

	/**
	 * Retourne la pile d'exécution des filtres.
	 *
	 * @return la pile.
	 */
	public FilterStack getFilterStack() {
		return this.filterStack;
	}

	/**
	 * Défini la pile d'exécution des filtres.
	 *
	 * @param filterStack la pile.
	 */
	public void setFilterStack(FilterStack filterStack) {
		this.filterStack = filterStack;
	}

	/**
	 * Retourne le mapping associé aux exceptions.
	 *
	 * @return le mapping
	 */
	public Map<String, ExceptionConfiguration> getExceptionMappings() {
		return this.exceptionMappings;
	}

	/**
	 * Défini le mapping associé aux exceptions. .
	 *
	 * @param exceptionMappings le mapping.
	 */
	public void setExceptionMappings(Map<String, ExceptionConfiguration> exceptionMappings) {
		this.exceptionMappings = exceptionMappings;
	}

	/**
	 * Retourne le manager pour l'authentification.
	 *
	 * @return le manager.
	 */
	public AuthenticationManager getAuthenticationManager() {
		return this.authenticationManager;
	}

	/**
	 * Défini le manager pour l'authentification.
	 *
	 * @param authenticationManager le manager.
	 */
	public void setAuthenticationManager(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

}
