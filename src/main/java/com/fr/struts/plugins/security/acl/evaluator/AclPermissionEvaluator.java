package com.fr.struts.plugins.security.acl.evaluator;

import com.fr.struts.plugins.security.acl.bean.AclDomain;
import com.fr.struts.plugins.security.acl.bean.AclPermission;
import com.fr.struts.plugins.security.acl.bean.AclRight;
import com.fr.struts.plugins.security.acl.bean.AclRole;
import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.bean.Permission;
import com.fr.struts.plugins.security.core.evaluator.PermissionEvaluator;

public class AclPermissionEvaluator implements PermissionEvaluator {

	@Override
	public boolean evaluate(Authentication authentication, Permission genericPermission) {
		AclPermission permission = (AclPermission) genericPermission;
		AclRole role = (AclRole) authentication.getAuthority();
		AclDomain domain = role.getDomain(permission.getDomain());

		if (domain != null) {
			AclRight right = domain.getRight(permission.getRight());

			if (right != null && right.getValue().compareTo(permission.getValue()) >= 0) {
				return true;
			}
		}

		return false;
	}

}
