package com.fr.struts.plugins.security.acl.views.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.fr.struts.plugins.security.acl.components.Else;
import com.opensymphony.xwork2.util.ValueStack;

public class ElseTag extends ComponentTagSupport {

	private static final long serialVersionUID = -4943707781833182985L;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new Else(stack);
	}

}
