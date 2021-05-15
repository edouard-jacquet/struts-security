package com.fr.struts.plugins.security.core.bean;

public abstract class AbstractAuthority implements Authority {

	private static final long serialVersionUID = -1817365947648396504L;

	protected int identifier;

	protected AbstractAuthority(int identifier) {
		this.identifier = identifier;
	}

	@Override
	public int getIdentifier() {
		return this.identifier;
	}

}
