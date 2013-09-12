package com.micmiu.framework.web.v1.base.action;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.ui.Model;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.micmiu.framework.anno.RefAnnoTools;
import com.micmiu.framework.web.GlobalConstant;
import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;
import com.micmiu.framework.web.v1.demo.entity.Blog;

/**
 * 对AbstractBasicAction 简单实现
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 * @param <E>
 * @param <ID>
 * @param <PQ>
 */
public abstract class BasicAction<E, ID extends Serializable, PQ extends AbstractPagedQuery<E>>
		extends AbstractBasicAction<E, ID, PQ> {

	public BasicAction() {
		super();
	}

	@Override
	protected void checkAuth(String operation) {
		Subject currentUser = SecurityUtils.getSubject();
		currentUser.checkPermission(this.getPermssionName() + ":" + operation);

	}

	/**
	 * 以小写类名作为权限别名
	 */
	@Override
	protected String getPermssionName() {
		return this.clazz.getSimpleName().toLowerCase();
	}

	/**
	 * 以小写类名作为form属性
	 */
	@Override
	protected String getModelAttr() {
		return this.clazz.getSimpleName().toLowerCase();
	}

	@Override
	protected boolean handler4Create(HttpServletRequest request, E entity) {
		return true;
	}

	@Override
	protected void handler4CreateShow(HttpServletRequest request, E entity,
			Model model) {

	}

	@Override
	protected void handler4UpdateShow(HttpServletRequest request, E entity,
			Model model) {

	}

	@Override
	protected boolean handler4Save(HttpServletRequest request, E entity) {
		return true;
	}

	@Override
	protected boolean handler4Update(HttpServletRequest request, E entity) {
		return true;
	}

	@Override
	protected boolean allowDeleteData(ID id, StringBuffer msgkey) {
		return true;
	}

	@Override
	protected boolean allowDeleteData(ID[] ids, StringBuffer msgkey) {
		return true;
	}

	@Override
	protected void handler4Export(HttpServletRequest request,
			Map<String, Object> model) {
		model.put(GlobalConstant.EXPORT_FILENAME, messageSource.getMessage(
				this.clazz.getSimpleName().toLowerCase() + "."
						+ GlobalConstant.EXPORT_FILENAME, null,
				RequestContextUtils.getLocale(request)));
		model.put(GlobalConstant.EXPORT_SHEETNAME, messageSource.getMessage(
				this.clazz.getSimpleName().toLowerCase() + "."
						+ GlobalConstant.EXPORT_SHEETNAME, null,
				RequestContextUtils.getLocale(request)));
		model.put(GlobalConstant.EXPORT_TITLE, messageSource.getMessage(
				this.clazz.getSimpleName().toLowerCase() + "."
						+ GlobalConstant.EXPORT_TITLE, null,
				RequestContextUtils.getLocale(request)));

		Map<String, String> showMap = new LinkedHashMap<String, String>();
		RefAnnoTools.getBeanAnnoMap(Blog.class, showMap, messageSource,
				RequestContextUtils.getLocale(request));

		model.put(GlobalConstant.EXPORT_COLUMN_MAP, showMap);

	}

}
