package com.fr.struts.plugins.security.core.filter;

import com.fr.struts.plugins.http.ResponseUtils;
import com.fr.struts.plugins.security.core.configuration.ExceptionConfiguration;
import com.fr.struts.plugins.security.core.configuration.SecurityConfigurator;
import com.fr.struts.plugins.security.core.exception.SecurityException;
import com.fr.struts.plugins.security.core.filter.invocation.FilterInvocation;

/**
 * Filtre gérant les exceptions émises par le framework de sécurité.
 */
public class BasicExceptionFilter implements Filter {

	@Override
	public String invoke(FilterInvocation filterInvocation) throws Exception {
		try {
			return filterInvocation.invoke();
		} catch (SecurityException exception) {
			String className = exception.getClass().getCanonicalName();
			ExceptionConfiguration configuration = SecurityConfigurator.getConfiguration().getExceptionMappings()
					.get(className);

			if (configuration == null || configuration.getResult() == null) {
				throw exception;
			}

			if (configuration.getStatus() != null) {
				ResponseUtils.setStatus(Integer.parseInt(configuration.getStatus()));
			}

			return configuration.getResult();
		}
	}

}
