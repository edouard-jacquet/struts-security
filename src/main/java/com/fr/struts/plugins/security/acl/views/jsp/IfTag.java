package com.fr.struts.plugins.security.acl.views.jsp;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.jsp.ComponentTagSupport;

import com.fr.struts.plugins.security.acl.components.If;
import com.opensymphony.xwork2.util.ValueStack;

public class IfTag extends ComponentTagSupport {

	private static final long serialVersionUID = 8556849905433307812L;

	protected String control;

	@Override
	public Component getBean(ValueStack stack, HttpServletRequest request, HttpServletResponse response) {
		return new If(stack);
	}

	@Override
	protected void populateParams() {
		If bean = ((If) this.component);
		bean.setControl(this.control);
	}

	public void setControl(String control) {
		this.control = control;
	}

}
