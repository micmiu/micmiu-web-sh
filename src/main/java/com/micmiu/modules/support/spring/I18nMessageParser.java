package com.micmiu.modules.support.spring;

import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;

/**
 * 资源文件解析
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public class I18nMessageParser {

	private static final Logger logger = LoggerFactory
			.getLogger(I18nMessageParser.class);

	private MessageSource messageSource;

	private Locale locale;

	private static final class I18nMessageParserHolder {
		private static final I18nMessageParser instance = new I18nMessageParser();
	}

	private I18nMessageParser() {
		messageSource = (MessageSource) SpringContextUtil
				.getApplicationContext().getBean("messageSource");
	}

	public static I18nMessageParser getInstance() {
		return I18nMessageParserHolder.instance;
	}

	public Locale getLocale() {
		return locale;
	}

	public void setLocale(Locale locale) {
		this.locale = locale;
		logger.debug("set locale to=" + locale);
	}

	public String getMessage(String key) {
		try {
			return messageSource.getMessage(key, null, locale);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			return key;
		}
	}

}
