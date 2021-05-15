package com.fr.struts.plugins.security.core.context.repository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.fr.struts.plugins.security.core.context.HttpHolder;
import com.fr.struts.plugins.security.core.context.SecurityContext;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;

public class SessionSecurityContextRepository implements SecurityContextRepository {

	public static final String CONTEXT = "struts.security.context";

	@Override
	public SecurityContext loadContext(HttpHolder holder) {
		HttpServletRequest request = holder.getRequest();
		HttpSession session = request.getSession();

		SecurityContext context = (SecurityContext) session.getAttribute(SessionSecurityContextRepository.CONTEXT);

		if (!(context instanceof SecurityContext)) {
			context = SecurityContextHolder.createEmptyContext();
		}

		return context;
	}

	@Override
	public void saveContext(SecurityContext context, HttpHolder holder) {
		HttpServletRequest request = holder.getRequest();
		HttpSession session = request.getSession();

		if (context.getAuthentication() != null) {
			session.setAttribute(SessionSecurityContextRepository.CONTEXT, context);
		}
	}

}
