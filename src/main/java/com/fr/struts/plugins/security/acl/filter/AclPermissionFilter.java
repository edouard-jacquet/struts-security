package com.fr.struts.plugins.security.acl.filter;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.fr.struts.plugins.security.acl.annotation.Permission;
import com.fr.struts.plugins.security.acl.annotation.Permissions;
import com.fr.struts.plugins.security.acl.bean.AclPermission;
import com.fr.struts.plugins.security.acl.evaluator.AclPermissionEvaluator;
import com.fr.struts.plugins.security.core.annotation.Anonymous;
import com.fr.struts.plugins.security.core.authentication.Authentication;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;
import com.fr.struts.plugins.security.core.evaluator.PermissionEvaluator;
import com.fr.struts.plugins.security.core.exception.ForbiddenException;
import com.fr.struts.plugins.security.core.filter.Filter;
import com.fr.struts.plugins.security.core.filter.invocation.FilterInvocation;
import com.opensymphony.xwork2.ActionInvocation;

/**
 * Filtre contrôlant si l'utilisateur poss^ède les bonnes permissions.
 */
public class AclPermissionFilter implements Filter {

	private final PermissionEvaluator evaluator = new AclPermissionEvaluator();

	@Override
	public String invoke(FilterInvocation filterInvocation) throws Exception {
		if (this.applyFilter(filterInvocation.getActionInvocation())) {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

			List<AclPermission> permissions = this.getRequired(filterInvocation.getActionInvocation());

			for (AclPermission permission : permissions) {
				if (!this.evaluator.evaluate(authentication, permission)) {
					throw new ForbiddenException();
				}
			}
		}

		return filterInvocation.invoke();
	}

	/**
	 * Détermine si le filtre doit être appliqué.
	 *
	 * @param actionInvocation l'action originelle.
	 * @return true si applicable.
	 * @throws NoSuchMethodException
	 */
	private boolean applyFilter(ActionInvocation actionInvocation) throws NoSuchMethodException {
		String methodName = actionInvocation.getProxy().getMethod();
		Class<? extends Object> classAction = actionInvocation.getAction().getClass();
		Method methodAction = classAction.getDeclaredMethod(methodName);

		return (methodAction.getAnnotation(Anonymous.class) == null);
	}

	/**
	 * Retourne les permissions nécessaires.
	 *
	 * @param actionInvocation l'action originelle.
	 * @return la liste des permissions.
	 * @throws NoSuchMethodException
	 */
	private List<AclPermission> getRequired(ActionInvocation actionInvocation) throws NoSuchMethodException {
		List<AclPermission> required = new ArrayList<AclPermission>();

		String methodName = actionInvocation.getProxy().getMethod();
		Method method = actionInvocation.getAction().getClass().getDeclaredMethod(methodName);

		Permissions permissions = method.getAnnotation(Permissions.class);
		if (permissions != null) {
			for (Permission permission : permissions.value()) {
				required.add(new AclPermission(permission.domain(), permission.right(), permission.value()));
			}
		}

		Permission permission = method.getAnnotation(Permission.class);
		if (permission != null) {
			required.add(new AclPermission(permission.domain(), permission.right(), permission.value()));
		}

		return required;
	}

}
