package com.micmiu.framework.web.v1.demo.action;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Controller
@RequestMapping(value = "/demo/i18n")
public class I18nDemo {

	private static final Logger logger = LoggerFactory.getLogger(I18nDemo.class);

	private static final String PREFIX = "demo.i18n";

	@RequestMapping(value = "index.do")
	public String index() {
		return PREFIX + ".index";

	}

	@RequestMapping(value = "change.do")
	@ResponseBody
	public String change(HttpServletRequest request) {
		logger.info("change to locale=:" + request.getParameter("locale"));
		return "success";
	}

	@RequestMapping(value = "show.do")
	public String show() {
		return PREFIX + ".show";

	}

}
