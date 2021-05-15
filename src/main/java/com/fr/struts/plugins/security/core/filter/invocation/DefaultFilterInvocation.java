package com.fr.struts.plugins.security.core.filter.invocation;

import com.fr.struts.plugins.security.core.filter.Filter;
import com.fr.struts.plugins.security.core.filter.stack.FilterStack;
import com.opensymphony.xwork2.ActionInvocation;

public class DefaultFilterInvocation extends AbstractFilterInvocation implements FilterInvocation {

	public DefaultFilterInvocation(FilterStack filterStack, ActionInvocation actionInvocation) {
		this.actionInvocation = actionInvocation;
		this.queue = filterStack.getStack().iterator();
	}

	@Override
	public String invoke() throws Exception {
		if (this.queue.hasNext()) {
			Filter filter = this.queue.next();
			return filter.invoke(this);
		} else {
			return this.actionInvocation.invoke();
		}
	}

}
