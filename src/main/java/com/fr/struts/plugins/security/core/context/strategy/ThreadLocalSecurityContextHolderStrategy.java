package com.fr.struts.plugins.security.core.context.strategy;

import com.fr.struts.plugins.security.core.context.BasicSecurityContext;
import com.fr.struts.plugins.security.core.context.SecurityContext;

public final class ThreadLocalSecurityContextHolderStrategy implements SecurityContextHolderStrategy {

	private static final ThreadLocal<SecurityContext> contextHolder = new ThreadLocal<SecurityContext>();

	@Override
	public SecurityContext getContext() {
		SecurityContext securityContext = ThreadLocalSecurityContextHolderStrategy.contextHolder.get();

		if (securityContext == null) {
			securityContext = this.createEmptyContext();
			ThreadLocalSecurityContextHolderStrategy.contextHolder.set(securityContext);
		}

		return securityContext;
	}

	@Override
	public void setContext(SecurityContext securityContext) {
		ThreadLocalSecurityContextHolderStrategy.contextHolder.set(securityContext);
	}

	@Override
	public void clearContext() {
		ThreadLocalSecurityContextHolderStrategy.contextHolder.remove();
	}

	@Override
	public SecurityContext createEmptyContext() {
		return new BasicSecurityContext();
	}

}
