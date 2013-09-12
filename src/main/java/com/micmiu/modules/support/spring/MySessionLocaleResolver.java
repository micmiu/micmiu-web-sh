package com.micmiu.modules.support.spring;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.i18n.SessionLocaleResolver;

/**
 * 设置Locale 系统引用
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public class MySessionLocaleResolver extends SessionLocaleResolver {

	@Override
	public void setLocale(HttpServletRequest request,
			HttpServletResponse response, Locale locale) {
		super.setLocale(request, response, locale);
		I18nMessageParser.getInstance().setLocale(locale);
	}

}
