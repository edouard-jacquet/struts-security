package com.fr.struts.plugins.security.core.servlet.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.struts2.dispatcher.Dispatcher;
import org.apache.struts2.dispatcher.InitOperations;
import org.apache.struts2.dispatcher.PrepareOperations;
import org.apache.struts2.dispatcher.filter.FilterHostConfig;
import org.apache.struts2.dispatcher.mapper.ActionMapping;

import com.fr.struts.plugins.security.core.context.HttpHolder;
import com.fr.struts.plugins.security.core.context.SecurityContext;
import com.fr.struts.plugins.security.core.context.SecurityContextHolder;
import com.fr.struts.plugins.security.core.context.repository.SecurityContextRepository;
import com.fr.struts.plugins.security.core.context.repository.SessionSecurityContextRepository;

public class SecurityContextPersistenceFilter implements Filter {

	private Logger logger = LogManager.getLogger(SecurityContextPersistenceFilter.class);
	private SecurityContextRepository repository;
	private PrepareOperations prepare;

	public SecurityContextPersistenceFilter() {

	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		InitOperations initalizer = new InitOperations();

		try {
			if (filterConfig.getInitParameter("repository") == null) {
				this.repository = new SessionSecurityContextRepository();
			} else {
				Class<?> clazz = Class.forName(filterConfig.getInitParameter("repository"));
				this.repository = (SecurityContextRepository) clazz.newInstance();
			}

			FilterHostConfig configuration = new FilterHostConfig(filterConfig);
			Dispatcher dispatcher = initalizer.initDispatcher(configuration);

			this.prepare = new PrepareOperations(dispatcher);

			this.logger.info("Success to initialize Struts Security");
		} catch (Exception exception) {
			this.logger.error("Unable to initialize Struts Security", exception);
		} finally {
			initalizer.cleanup();
		}
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		HttpHolder holder = new HttpHolder(request, response);

		try {
			this.prepare.setEncodingAndLocale(request, response);
			this.prepare.createActionContext(request, response);
			this.prepare.assignDispatcherToThread();

			request = this.prepare.wrapRequest(request);

			ActionMapping mapping = this.prepare.findActionMapping(request, response, true);

			if (mapping == null) {
				chain.doFilter(request, response);
			} else {
				SecurityContext securityContext = this.repository.loadContext(holder);

				try {
					SecurityContextHolder.setContext(securityContext);

					chain.doFilter(request, response);
				} finally {
					securityContext = SecurityContextHolder.getContext();

					SecurityContextHolder.clearContext();

					this.repository.saveContext(securityContext, holder);
				}
			}
		} finally {
			this.prepare.cleanupRequest(request);
		}
	}

	@Override
	public void destroy() {
		this.prepare.cleanupDispatcher();

		this.logger.info("Success to destroy Struts Security");
	}

}
