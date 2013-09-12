package com.micmiu.framework.web.v1.system.action;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.micmiu.framework.utils.MenuPermUtil;
import com.micmiu.framework.utils.StringUtil;
import com.micmiu.framework.web.v1.base.action.BasicAction;
import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.entity.Role;
import com.micmiu.framework.web.v1.system.service.MenuService;
import com.micmiu.framework.web.v1.system.service.PermissionService;
import com.micmiu.framework.web.v1.system.service.RoleService;
import com.micmiu.framework.web.v1.system.vo.RoleQuery;
import com.micmiu.framework.web.v1.system.vo.TreeNode;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Controller
@RequestMapping(value = "/system/role.do")
public class RoleAction extends BasicAction<Role, Long, RoleQuery> {

	private static final String PREFIX = "system.role";

	private static final String REDIRECT = "redirect:/system/role.do?method=showList";

	@Autowired
	private RoleService roleService;

	@Autowired
	private MenuService menuService;

	@Autowired
	private PermissionService permissionService;

	@Override
	public BasicService<Role, Long> getBasicService() {
		return roleService;
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
	protected boolean handler4Create(HttpServletRequest request, Role entity) {
		super.handler4Create(request, entity);
		Long id = null;
		String idstr = request.getParameter("id");
		String roleName = request.getParameter("roleName");
		String nodes = request.getParameter("nodes");
		if (null != idstr && !"".equals(idstr)) {
			id = Long.parseLong(idstr);
		}
		entity.setRoleName(roleName);
		String[] nodeArr = nodes.split(",");
		List<Permission> perms = new ArrayList<Permission>();
		for (String node : nodeArr) {
			if (!node.startsWith("perm:")) {
				continue;
			}
			Long permId = Long.parseLong(node.split("\\:")[1]);
			Permission perm = permissionService.findById(permId);
			if (null != perm) {
				perms.add(perm);
			}
		}
		entity.setPermssions(perms);
		return true;
	}

	@Override
	protected boolean handler4Update(HttpServletRequest request, Role entity) {
		return this.handler4Create(request, entity);
	}

	@RequestMapping(params = { "method=checkRoleName" })
	@ResponseBody
	public String checkRoleName(
			@RequestParam("oldRoleName") String oldRoleName,
			@RequestParam("roleName") String roleName) {
		if (roleName.equals(oldRoleName)) {
			return "true";
		} else if (roleService.getRoleByName(roleName) == null) {
			return "true";
		}

		return "false";
	}

	@RequestMapping(params = { "method=getPermTree" })
	@ResponseBody
	public List<TreeNode> getPermTree(Long id) {
		List<TreeNode> permTree = new ArrayList<TreeNode>();
		Set<String> hasPerm = new HashSet<String>();
		if (null != id) {
			Role role = roleService.findById(id);
			hasPerm = MenuPermUtil.parsePermissionStrs(role.getPermssions());
		}
		MenuPermUtil.parseMenuPermTree(menuService.getRootMenuByOrder(),
				permTree, hasPerm);

		return permTree;
	}

}
