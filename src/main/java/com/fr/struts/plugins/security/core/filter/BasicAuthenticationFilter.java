package com.fr.struts.plugins.security.core.filter;

import java.lang.reflect.Method;

import com.fr.struts.plugins.security.core.annotation.Anonymous;
import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;
import com.fr.struts.plugins.security.core.evaluator.AuthenticationEvaluator;
import com.fr.struts.plugins.security.core.evaluator.BasicAuthenticationEvaluator;
import com.fr.struts.plugins.security.core.exception.UnauthorizedException;
import com.fr.struts.plugins.security.core.filter.invocation.FilterInvocation;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * Filtre contrôlant si l'utilisateur est authentifié.
 */
public class BasicAuthenticationFilter implements Filter {

	private final AuthenticationEvaluator evaluator = new BasicAuthenticationEvaluator();

	@Override
	public String invoke(FilterInvocation filterInvocation) throws Exception {
		if (this.applyFilter(filterInvocation.getActionInvocation())) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			if (!this.evaluator.evaluate(authentication)) {
				throw new UnauthorizedException();
			}
		}

		return filterInvocation.invoke();
	}

	/**
	 * Détermine si le filtre doit être appliqué.
	 *
	 * @param actionInvocation l'action originelle.
	 * @return true si applicable.
	 * @throws NoSuchMethodException
	 */
	private boolean applyFilter(ActionInvocation actionInvocation) throws NoSuchMethodException {
		String methodName = actionInvocation.getProxy().getMethod();
		Class<? extends Object> classAction = actionInvocation.getAction().getClass();
		Method methodAction = classAction.getDeclaredMethod(methodName);

		return (methodAction.getAnnotation(Anonymous.class) == null);
	}

}
