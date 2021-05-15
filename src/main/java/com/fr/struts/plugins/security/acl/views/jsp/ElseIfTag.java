package com.fr.struts.plugins.security.acl.views.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.fr.struts.plugins.security.acl.components.ElseIf;
import com.opensymphony.xwork2.util.ValueStack;

public class ElseIfTag extends ComponentTagSupport {

	private static final long serialVersionUID = 8087665207703856086L;

	protected String control;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new ElseIf(stack);
	}

	@Override
	protected void populateParams() {
		ElseIf bean = ((ElseIf) this.component);
		bean.setControl(this.control);
	}

	public void setControl(String control) {
		this.control = control;
	}

}
