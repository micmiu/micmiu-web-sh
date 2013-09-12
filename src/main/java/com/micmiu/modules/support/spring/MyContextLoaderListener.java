package com.micmiu.modules.support.spring;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

/**
 * extend ContextLoaderListener
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class MyContextLoaderListener extends
		org.springframework.web.context.ContextLoaderListener {

	public void contextInitialized(ServletContextEvent event) {
		super.contextInitialized(event);

		ServletContext context = event.getServletContext();

		ApplicationContext ctx = WebApplicationContextUtils
				.getRequiredWebApplicationContext(context);
		SpringContextUtil.setApplicationContext(ctx);
		String contextName = context.getServletContextName();
		String realpath = context.getRealPath("/");
		String serverInfo = context.getServerInfo();
		SpringContextUtil.setContextName(contextName);
		SpringContextUtil.setRealPath(realpath);
		SpringContextUtil.setServerInfo(serverInfo);
	}

}
