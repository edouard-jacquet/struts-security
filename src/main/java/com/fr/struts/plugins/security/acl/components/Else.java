package com.fr.struts.plugins.security.acl.components;

import java.io.Writer;
import java.util.Map;

import org.apache.struts2.components.Component;
import org.apache.struts2.views.annotations.StrutsTag;

import com.opensymphony.xwork2.util.ValueStack;

@StrutsTag(name = "else", tldTagClass = "com.fr.struts.plugins.security.acl.views.jsp.ElseTag", description = "Tag Else")
public class Else extends Component {

	public Else(ValueStack stack) {
		super(stack);
	}

	@Override
	public boolean start(Writer writer) {
		Map<String, Object> context = this.stack.getContext();
		Boolean ifResult = (Boolean) context.get(If.ANSWER);

		context.remove(If.ANSWER);

		return !((ifResult == null) || (ifResult.booleanValue()));
	}

}
