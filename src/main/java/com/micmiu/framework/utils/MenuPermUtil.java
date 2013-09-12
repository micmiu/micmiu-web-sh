package com.micmiu.framework.utils;

import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import org.springframework.context.MessageSource;

import com.micmiu.framework.web.v1.base.vo.OperationType;
import com.micmiu.framework.web.v1.system.entity.Menu;
import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.vo.TreeNode;
import com.micmiu.modules.support.spring.I18nMessageParser;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class MenuPermUtil {

	/**
	 * 转化用户菜单为HTML格式
	 * 
	 * @param allMenus
	 * @param userMenus
	 * @param ids
	 * @param contextPath
	 */
	public static void parseUserMenu(List<Menu> allMenus,
			List<TreeNode> userMenus, Set<Long> ids, String contextPath,
			MessageSource messageSource, Locale locale) {
		for (Menu menu : allMenus) {
			if (ids.contains(menu.getId())) {
				userMenus.add(recParseMenu(ids, menu, contextPath,
						messageSource, locale));
			}
		}
	}

	/**
	 * 递归菜单转化为HTML
	 * 
	 * @param ids
	 * @param menu
	 * @param contextPath
	 * @param messageSource
	 * @param locale
	 * @return
	 */
	private static TreeNode recParseMenu(Set<Long> ids, Menu menu,
			String contextPath, MessageSource messageSource, Locale locale) {
		TreeNode vo = new TreeNode();
		vo.setId(menu.getId() + "");
		String text = "";
		String menuName = messageSource.getMessage(menu.getMenuName(), null,
				menu.getMenuName(), locale);
		if (null != menu.getMenuURL() && !"".equals(menu.getMenuURL())) {

			if (menu.getMenuURL().startsWith("/")) {
				text = "<a href ='" + contextPath + menu.getMenuURL() + "'>"
						+ menuName + "</a>";
			} else {
				text = "<a href ='" + contextPath + "/" + menu.getMenuURL()
						+ "'>" + menuName + "</a>";
			}
		} else {
			text = menuName;
		}
		vo.setText(text);
		if (!menu.getChildren().isEmpty()) {
			for (Menu childMenu : menu.getChildren()) {
				if (ids.contains(childMenu.getId())) {
					vo.getChildren().add(
							recParseMenu(ids, childMenu, contextPath,
									messageSource, locale));
				}
			}
		}
		return vo;
	}

	/**
	 * 转化已有的权限ID
	 * 
	 * @param permssions
	 * @return
	 */
	public static Set<String> parsePermissionStrs(List<Permission> permssions) {
		Set<String> permStrs = new HashSet<String>();
		for (Permission permssion : permssions) {
			permStrs.add("perm:" + permssion.getId());
		}
		return permStrs;
	}

	/**
	 * 转化已有角色的权限树形结构
	 * 
	 * @param allMenus
	 * @param permTree
	 * @param hasPerms
	 */
	public static void parseMenuPermTree(List<Menu> allMenus,
			List<TreeNode> permTree, Set<String> hasPerms) {
		for (Menu menu : allMenus) {
			permTree.add(recMenuPermTree(menu, hasPerms));

		}
	}

	/**
	 * 递归解析权限树型
	 * 
	 * @param menu
	 * @param hasPerms
	 * @return
	 */
	private static TreeNode recMenuPermTree(Menu menu, Set<String> hasPerms) {
		TreeNode vo = new TreeNode();
		vo.setId("menu:" + menu.getId());
		vo.setText(I18nMessageParser.getInstance().getMessage(
				menu.getMenuName()));
		if (!menu.getChildren().isEmpty()) {
			for (Menu childMenu : menu.getChildren()) {
				vo.getChildren().add(recMenuPermTree(childMenu, hasPerms));
			}
		} else {
			for (Permission perm : menu.getPermssionList()) {
				TreeNode permNode = new TreeNode();
				String nodeId = "perm:" + perm.getId();
				permNode.setId(nodeId);
				if (null != hasPerms && hasPerms.contains(nodeId)) {
					permNode.setChecked(true);
				}
				permNode.setText(I18nMessageParser.getInstance().getMessage(
						OperationType.parse(perm.getOperation()).getDisplay()));

				vo.getChildren().add(permNode);
			}
		}
		return vo;
	}
}
