package com.fr.struts.plugins.security.core.filter.stack;

import java.util.ArrayList;
import java.util.List;

import com.fr.struts.plugins.security.core.filter.Filter;

public abstract class AbstractFilterStack implements FilterStack {

	protected List<Filter> stack = new ArrayList<Filter>();

	@Override
	public List<Filter> getStack() {
		return this.stack;
	}

}
