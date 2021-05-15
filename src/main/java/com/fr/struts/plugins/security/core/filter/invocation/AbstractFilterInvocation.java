package com.fr.struts.plugins.security.core.filter.invocation;

import java.util.Iterator;

import com.fr.struts.plugins.security.core.filter.Filter;
import com.opensymphony.xwork2.ActionInvocation;

public abstract class AbstractFilterInvocation implements FilterInvocation {

	protected ActionInvocation actionInvocation;
	protected Iterator<Filter> queue;

	@Override
	public ActionInvocation getActionInvocation() {
		return this.actionInvocation;
	}

}
