package com.micmiu.framework.web.v1.system.service;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AccountException;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.SimplePrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.entity.Role;
import com.micmiu.framework.web.v1.system.entity.User;
import com.micmiu.modules.captcha.CaptchaServlet;
import com.micmiu.modules.support.shiro.CaptchaException;
import com.micmiu.modules.support.shiro.UsernamePasswordCaptchaToken;

/**
 * 演示用户和权限的认证，使用默认 的SimpleCredentialsMatcher
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class ShiroDbRealm extends AuthorizingRealm {

	private static final Logger logger = Logger.getLogger(ShiroDbRealm.class);

	private UserService userService;

	/**
	 * 认证回调函数, 登录时调用.
	 */
	@Override
	protected AuthenticationInfo doGetAuthenticationInfo(
			AuthenticationToken authcToken) throws AuthenticationException {
		UsernamePasswordCaptchaToken token = (UsernamePasswordCaptchaToken) authcToken;

		String username = token.getUsername();
		if (username == null) {
			throw new AccountException(
					"Null usernames are not allowed by this realm.");
		}
		// 增加判断验证码逻辑
		String captcha = token.getCaptcha();
		String exitCode = (String) SecurityUtils.getSubject().getSession()
				.getAttribute(CaptchaServlet.KEY_CAPTCHA);
		if (null == captcha || !captcha.equalsIgnoreCase(exitCode)) {
			throw new CaptchaException("验证码错误");
		}

		User user = userService.getUserByLoginName(username);
		if (null == user) {
			throw new UnknownAccountException("No account found for user ["
					+ username + "]");
		}
		return new SimpleAuthenticationInfo(new ShiroUser(user.getLoginName(),
				user.getName()), user.getPassword(), getName());

	}

	/**
	 * 授权查询回调函数, 进行鉴权但缓存中无用户的授权信息时调用.
	 */
	@Override
	protected AuthorizationInfo doGetAuthorizationInfo(
			PrincipalCollection principals) {
		ShiroUser shiroUser = (ShiroUser) principals.fromRealm(getName())
				.iterator().next();
		User user = userService.getUserByLoginName(shiroUser.getLoginName());
		if (user != null) {
			SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
			for (Role role : user.getRoleList()) {
				List<Permission> permssions = role.getPermssions();
				List<String> permStrs = new ArrayList<String>();
				for (Permission p : permssions) {
					permStrs.add(p.getShiroPerm());
					logger.debug(">>>Permssion:" + p.getShiroPerm());
				}
				// 基于Permission的权限信息
				info.addStringPermissions(permStrs);
			}
			return info;
		} else {
			return null;
		}
	}

	/**
	 * 更新用户授权信息缓存.
	 */
	public void clearCachedAuthorizationInfo(String principal) {
		SimplePrincipalCollection principals = new SimplePrincipalCollection(
				principal, getName());
		clearCachedAuthorizationInfo(principals);
	}

	/**
	 * 清除所有用户授权信息缓存.
	 */
	public void clearAllCachedAuthorizationInfo() {
		Cache<Object, AuthorizationInfo> cache = getAuthorizationCache();
		if (cache != null) {
			for (Object key : cache.keys()) {
				cache.remove(key);
			}
		}
	}

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 自定义Authentication对象，使得Subject除了携带用户的登录名外还可以携带更多信息.
	 */
	public static class ShiroUser implements Serializable {

		private static final long serialVersionUID = -1748602382963711884L;
		private String loginName;
		private String name;

		public ShiroUser(String loginName, String name) {
			this.loginName = loginName;
			this.name = name;
		}

		public String getLoginName() {
			return loginName;
		}

		/**
		 * 本函数输出将作为默认的<shiro:principal/>输出.
		 */
		@Override
		public String toString() {
			return loginName;
		}

		public String getName() {
			return name;
		}
	}
}
