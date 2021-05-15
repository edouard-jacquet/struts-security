package com.fr.struts.plugins.security.core.filter;

import com.fr.struts.plugins.security.core.filter.invocation.FilterInvocation;

public interface Filter {

	/**
	 * Exécute le filtre.
	 *
	 * @param filterInvocation la pile d'exécution.
	 * @return l'action à exécuter.
	 * @throws Exception
	 */
	public String invoke(FilterInvocation filterInvocation) throws Exception;

}
