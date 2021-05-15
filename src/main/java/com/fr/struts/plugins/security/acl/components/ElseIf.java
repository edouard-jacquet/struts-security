package com.fr.struts.plugins.security.acl.components;

import java.io.Writer;
import java.util.Map;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;
import org.apache.struts2.views.annotations.StrutsTagAttribute;

import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;
import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "elseif", tldTagClass = "com.fr.struts.plugins.security.acl.views.jsp.ElseIfTag", description = "Tag ElseIf")
public class ElseIf extends Component {

	protected String control;
	protected Boolean answer;

	public ElseIf(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		Map<String, Object> context = this.stack.getContext();
		Boolean ifResult = (Boolean) context.get(If.ANSWER);

		if ((ifResult == null) || (ifResult.booleanValue())) {
			return Boolean.FALSE.booleanValue();
		}

		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		this.answer = Utils.evaluate(authentication, this.control);

		if (this.answer.booleanValue()) {
			this.stack.getContext().put(If.ANSWER, this.answer);
		}

		return this.answer.booleanValue();
	}

	@Override
	public boolean end(Writer writer, String body) {
		if (this.answer == null) {
			this.answer = Boolean.FALSE;
		}

		if (this.answer.booleanValue()) {
			this.stack.getContext().put(If.ANSWER, this.answer);
		}

		return super.end(writer, "");
	}

	@StrutsTagAttribute(description = "Liste des permissions requises pour que le corps du tag soit affiché", type = "String", required = true)
	public void setControl(String control) {
		this.control = control;
	}

}
