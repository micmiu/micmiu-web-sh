package com.micmiu.framework.web.v1.system.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.micmiu.framework.utils.MenuPermUtil;
import com.micmiu.framework.web.v1.system.entity.Menu;
import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.entity.Role;
import com.micmiu.framework.web.v1.system.entity.User;
import com.micmiu.framework.web.v1.system.service.MenuService;
import com.micmiu.framework.web.v1.system.service.UserService;
import com.micmiu.framework.web.v1.system.vo.TreeNode;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Controller
@RequestMapping(value = "/system/menu.do")
public class MenuAction {

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private MessageSource messageSource;

	@RequestMapping(params = { "method=getUserMenu" })
	@ResponseBody
	public List<TreeNode> getUserMenu(HttpServletRequest request) {

		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		User currUser = userService.getUserByLoginName(loginName);
		String contextPath = request.getSession().getServletContext()
				.getContextPath();

		MenuPermUtil.parseUserMenu(menuService.getRootMenuByOrder(),
				treeNodeList, parseMenuIds(currUser.getRoleList()),
				contextPath, messageSource,
				RequestContextUtils.getLocale(request));

		return treeNodeList;
	}

	private Set<Long> parseMenuIds(List<Role> roles) {
		Set<Long> ids = new HashSet<Long>();
		for (Role role : roles) {
			for (Permission perm : role.getPermssions()) {
				recParseMenuIds(ids, perm.getMenu());
			}
		}
		return ids;

	}

	private void recParseMenuIds(Set<Long> menuIds, Menu menu) {
		menuIds.add(menu.getId());
		if (null != menu.getParent()) {
			recParseMenuIds(menuIds, menu.getParent());
		}
	}

}
