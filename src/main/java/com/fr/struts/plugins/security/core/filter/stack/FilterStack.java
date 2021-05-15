package com.fr.struts.plugins.security.core.filter.stack;

import java.util.List;

import com.fr.struts.plugins.security.core.filter.Filter;

public interface FilterStack {

	/**
	 * Retourne la pile des filtres.
	 *
	 * @return la pile d'exécution.
	 */
	public List<Filter> getStack();

}
