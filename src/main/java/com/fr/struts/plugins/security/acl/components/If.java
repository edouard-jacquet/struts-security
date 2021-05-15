package com.fr.struts.plugins.security.acl.components;

import java.io.Writer;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "if", tldTagClass = "com.fr.struts.plugins.security.acl.views.jsp.IfTag", description = "Tag If")
public class If extends Component {

	public static final String ANSWER = "struts.security.if.anwser";
	protected String control;
	protected Boolean answer;

	public If(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		this.answer = Utils.evaluate(authentication, this.control);

		this.stack.getContext().put(If.ANSWER, this.answer);

		return this.answer.booleanValue();
	}

	@Override
	public boolean end(Writer writer, String body) {
		this.stack.getContext().put(If.ANSWER, this.answer);

		return super.end(writer, body);
	}

	@StrutsTagAttribute(description = "Liste des permissions requises pour que le corps du tag soit affiché", type = "String", required = true)
	public void setControl(String control) {
		this.control = control;
	}

}
