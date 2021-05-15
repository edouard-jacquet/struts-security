package com.fr.struts.plugins.security.acl.bean;

import com.fr.struts.plugins.security.acl.ACL;
import com.fr.struts.plugins.security.core.bean.Permission;

public class AclPermission implements Permission {

	private String domain;
	private String right;
	private ACL value;

	public AclPermission(String domain, String right, ACL value) {
		this.domain = domain;
		this.right = right;
		this.value = value;
	}

	public String getDomain() {
		return this.domain;
	}

	public void setDomain(String domain) {
		this.domain = domain;
	}

	public String getRight() {
		return this.right;
	}

	public void setRight(String right) {
		this.right = right;
	}

	public ACL getValue() {
		return this.value;
	}

	public void setValue(ACL value) {
		this.value = value;
	}

}
