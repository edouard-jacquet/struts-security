package com.fr.struts.plugins.security.core.filter.invocation;

import com.opensymphony.xwork2.ActionInvocation;

public interface FilterInvocation {

	/**
	 * Exécute la pile de filtres.
	 *
	 * @return l'action à exécuter.
	 * @throws Exception
	 */
	public String invoke() throws Exception;

	/**
	 * Retourne l'action originelle.
	 *
	 * @return l'action originelle.
	 */
	public ActionInvocation getActionInvocation();

}
