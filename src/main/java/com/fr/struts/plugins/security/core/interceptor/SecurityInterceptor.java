package com.fr.struts.plugins.security.core.interceptor;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fr.struts.plugins.security.core.annotation.SkipSecurity;
import com.fr.struts.plugins.security.core.configuration.Configuration;
import com.fr.struts.plugins.security.core.configuration.SecurityConfigurator;
import com.fr.struts.plugins.security.core.exception.UnsecuredApplicationException;
import com.fr.struts.plugins.security.core.filter.invocation.DefaultFilterInvocation;
import com.fr.struts.plugins.security.core.filter.invocation.FilterInvocation;
import com.fr.struts.plugins.security.core.filter.stack.FilterStack;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

/**
 * Intercepteur principal du framework de sécurité.
 */
public class SecurityInterceptor extends AbstractInterceptor {

	private static final long serialVersionUID = 7836707753066144010L;

	private transient Logger logger = LogManager.getLogger(SecurityInterceptor.class);
	private transient FilterStack filterStack;
	private Boolean secured;

	public SecurityInterceptor() {
		super();
		this.secured = Boolean.FALSE;
	}

	@Override
	public void init() {
		this.logger.info("Initialize Struts Security");

		try {
			Configuration configuration = SecurityConfigurator.initialize("/struts-security.xml");
			this.filterStack = configuration.getFilterStack();
			this.secured = Boolean.TRUE;

			this.logger.info("Success to initialize Struts SecurityInterceptor");
		} catch (Exception exception) {
			this.logger.error("Unable to initialize Struts SecurityInterceptor", exception);
		}
	}

	@Override
	public String intercept(ActionInvocation actionInvocation) throws Exception {
		if (!Boolean.TRUE.equals(this.secured)) {
			throw new UnsecuredApplicationException();
		}

		if (this.applyInterceptor(actionInvocation)) {
			FilterInvocation filterInvocation = new DefaultFilterInvocation(this.filterStack, actionInvocation);
			return filterInvocation.invoke();
		}

		return actionInvocation.invoke();
	}

	/**
	 * Détermine si l'intercepteur doit être appliqué.
	 *
	 * @param actionInvocation l'action originelle.
	 * @return true si applicable.
	 * @throws NoSuchMethodException
	 */
	private boolean applyInterceptor(ActionInvocation actionInvocation) throws NoSuchMethodException {
		String methodName = actionInvocation.getProxy().getMethod();
		Class<? extends Object> classAction = actionInvocation.getAction().getClass();
		Method methodAction = classAction.getDeclaredMethod(methodName);

		return (classAction.getAnnotation(SkipSecurity.class) == null
				&& methodAction.getAnnotation(SkipSecurity.class) == null);
	}

}
