package com.fr.struts.plugins.security.acl.bean;

import com.fr.struts.plugins.security.acl.ACL;

public class AclRight {

	private String name;
	private ACL value;

	public AclRight(String name, ACL value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return this.name;
	}

	public ACL getValue() {
		return this.value;
	}

}
