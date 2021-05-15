package com.fr.struts.plugins.security.core.evaluator;

import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.bean.Permission;

public interface PermissionEvaluator extends Evaluator {

	/**
	 * Evalue si l'utilisateur possède la bonne permission.
	 *
	 * @param authentication    l'authentification associée.
	 * @param genericPermission la permission à évaluer.
	 * @return true si correct.
	 */
	public boolean evaluate(Authentication authentication, Permission genericPermission);

}
