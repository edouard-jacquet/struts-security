package com.fr.struts.plugins.security.acl.bean;

import java.util.HashMap;
import java.util.Map;

public class AclDomain {

	private String name;
	private Map<String, AclRight> rights;

	public AclDomain(String name) {
		this.name = name;
		this.rights = new HashMap<String, AclRight>();
	}

	public AclDomain(String name, Map<String, AclRight> rights) {
		this.name = name;
		this.rights = rights;
	}

	public String getName() {
		return this.name;
	}

	public Map<String, AclRight> getRights() {
		return this.rights;
	}

	public AclRight getRight(String name) {
		return this.rights.get(name);
	}

}
