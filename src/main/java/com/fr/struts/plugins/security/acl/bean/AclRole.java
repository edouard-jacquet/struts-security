package com.fr.struts.plugins.security.acl.bean;

import java.util.Map;

import com.fr.struts.plugins.security.core.bean.AbstractAuthority;

public class AclRole extends AbstractAuthority {

	private static final long serialVersionUID = 4830519607778998971L;

	private String name;
	private transient Map<String, AclDomain> domains;

	public AclRole(Integer identifier, String name, Map<String, AclDomain> domains) {
		super(identifier);
		this.name = name;
		this.domains = domains;
	}

	public String getName() {
		return this.name;
	}

	public Map<String, AclDomain> getDomains() {
		return this.domains;
	}

	public AclDomain getDomain(String name) {
		return this.domains.get(name);
	}

}
