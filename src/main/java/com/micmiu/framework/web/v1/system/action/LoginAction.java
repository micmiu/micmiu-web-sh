package com.micmiu.framework.web.v1.system.action;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.LocaleResolver;

/**
 * LoginAction负责打开登录页面(GET请求)和登录出错页面(POST请求)， 真正登录的POST请求由Filter完成,
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Controller
public class LoginAction {

	@Autowired
	private LocaleResolver localeResolver;

	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginShow(HttpServletRequest req, HttpServletResponse resp) {

		Subject currentUser = SecurityUtils.getSubject();
		if (currentUser.isAuthenticated()) {
			return "redirect:/index.do";
		}
		Locale loc = localeResolver.resolveLocale(req);
		localeResolver.setLocale(req, resp, loc);
		return "login";
	}

	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String fail(
			@RequestParam(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM) String userName,
			Model model) {
		model.addAttribute(FormAuthenticationFilter.DEFAULT_USERNAME_PARAM,
				userName);
		return "login";
	}

}
