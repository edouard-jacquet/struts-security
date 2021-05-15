package com.fr.struts.plugins.security.core.evaluator;

import com.fr.struts.plugins.security.core.authentication.Authentication;

public class BasicAuthenticationEvaluator implements AuthenticationEvaluator {

	@Override
	public boolean evaluate(Authentication authentication) throws Exception {
		return (authentication != null && authentication.isAuthenticated());
	}
}
