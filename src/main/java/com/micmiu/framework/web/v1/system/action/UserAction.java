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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.micmiu.framework.utils.MenuPermUtil;
import com.micmiu.framework.utils.StringUtil;
import com.micmiu.framework.web.GlobalConstant;
import com.micmiu.framework.web.v1.base.action.BasicAction;
import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.system.entity.Menu;
import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.entity.Role;
import com.micmiu.framework.web.v1.system.entity.User;
import com.micmiu.framework.web.v1.system.service.MenuService;
import com.micmiu.framework.web.v1.system.service.RoleService;
import com.micmiu.framework.web.v1.system.service.UserService;
import com.micmiu.framework.web.v1.system.vo.TreeNode;
import com.micmiu.framework.web.v1.system.vo.UserQuery;

/**
 * 用户管理
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Controller
@RequestMapping(value = "/system/user.do")
public class UserAction extends BasicAction<User, Long, UserQuery> {

	private static final String PREFIX = "system.user";

	private static final String REDIRECT = "redirect:/system/user.do?method=showList";

	@Autowired
	private UserService userService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private MessageSource messageSource;

	@Override
	public BasicService<User, Long> getBasicService() {
		return userService;
	}

	@Override
	public String getViewPrefix() {
		return PREFIX;
	}

	@Override
	public String getRedirectView() {
		return REDIRECT;
	}

	@Override
	protected Long[] parseDeleteIDS(HttpServletRequest request) {
		return StringUtil.parseIdstr(request.getParameter("ids"));
	}

	@Override
	protected void handler4CreateShow(HttpServletRequest request, User entity,
			Model model) {
		super.handler4CreateShow(request, entity, model);
		model.addAttribute("roleList", roleService.findAll());
	}

	@Override
	protected void handler4UpdateShow(HttpServletRequest request, User entity,
			Model model) {
		super.handler4UpdateShow(request, entity, model);
		model.addAttribute("roleList", roleService.findAll());
	}

	@Override
	protected boolean handler4Create(HttpServletRequest request, User entity) {
		String roleIdStr = request.getParameter("roleId");
		Role role = roleService.findById(Long.parseLong(roleIdStr));
		entity.getRoleList().add(role);
		return true;

	}

	@Override
	protected boolean handler4Update(HttpServletRequest request, User entity) {
		handler4Create(request, entity);
		User exit = userService.findById(entity.getId());
		entity.setPassword(exit.getPassword());
		return true;
	}

	@Override
	protected boolean allowDeleteData(Long[] ids, StringBuffer msgkey) {
		for (Long id : ids) {
			User user = this.userService.findById(id);
			if (null != user
					&& SecurityUtils.getSubject().getPrincipal().toString()
							.equals(user.getLoginName())) {
				msgkey.append(GlobalConstant.MSG_FORBID_DELETE_SELF);
				return false;
			}
		}
		return true;
	}

	@RequestMapping(params = { "method=checkLoginName" })
	@ResponseBody
	public String checkLoginName(
			@RequestParam("oldLoginName") String oldLoginName,
			@RequestParam("loginName") String loginName) {
		if (loginName.equals(oldLoginName)) {
			return "true";
		} else if (userService.getUserByLoginName(loginName) == null) {
			return "true";
		}

		return "false";
	}

	@RequestMapping(params = { "method=getUserMenu" })
	@ResponseBody
	public List<TreeNode> getUserTreeMenu(HttpServletRequest request) {
		String contextPath = request.getSession().getServletContext()
				.getContextPath();

		List<TreeNode> treeNodeList = new ArrayList<TreeNode>();

		String loginName = SecurityUtils.getSubject().getPrincipal().toString();
		User currUser = userService.getUserByLoginName(loginName);

		MenuPermUtil.parseUserMenu(menuService.getRootMenuByOrder(),
				treeNodeList, parseMenuIds(currUser.getRoleList()),
				contextPath, messageSource,
				RequestContextUtils.getLocale(request));

		return treeNodeList;
	}

	/**
	 * 获取角色的所有的菜单
	 * 
	 * @param roles
	 * @return
	 */
	private Set<Long> parseMenuIds(List<Role> roles) {
		Set<Long> ids = new HashSet<Long>();
		for (Role role : roles) {
			for (Permission perm : role.getPermssions()) {
				recParseMenuIds(ids, perm.getMenu());
			}
		}
		return ids;

	}

	/**
	 * 递归菜单
	 * 
	 * @param menuIds
	 * @param menu
	 */
	private void recParseMenuIds(Set<Long> menuIds, Menu menu) {
		menuIds.add(menu.getId());
		if (null != menu.getParent()) {
			recParseMenuIds(menuIds, menu.getParent());
		}
	}
}
