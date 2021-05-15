package com.fr.struts.plugins.security.core.evaluator;

import com.fr.struts.plugins.security.core.authentication.Authentication;

public interface AuthenticationEvaluator extends Evaluator {

	/**
	 * Evalue si l'utilisateur est correctement authentifié.
	 *
	 * @param authentication l'authentification à évaluer.
	 * @return true si correct.
	 * @throws Exception
	 */
	public boolean evaluate(Authentication authentication) throws Exception;

}
