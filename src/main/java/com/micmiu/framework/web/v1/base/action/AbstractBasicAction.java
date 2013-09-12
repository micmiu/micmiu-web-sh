package com.micmiu.framework.web.v1.base.action;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.support.RequestContextUtils;

import com.micmiu.framework.anno.RefAnnoTools;
import com.micmiu.framework.web.GlobalConstant;
import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;
import com.micmiu.framework.web.v1.base.vo.OperationType;
import com.micmiu.modules.support.easyui.BeanConvert;
import com.micmiu.modules.support.easyui.PropertyGridData;
import com.micmiu.modules.support.springmvc.view.CsvView;
import com.micmiu.modules.support.springmvc.view.JxlExcelView;
import com.micmiu.modules.support.springmvc.view.PdfiText5View;
import com.micmiu.modules.support.springmvc.view.PoiExcelView;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 */
public abstract class AbstractBasicAction<E, ID extends Serializable, PQ extends AbstractPagedQuery<E>> {

	@Autowired
	protected MessageSource messageSource;

	/**
	 * 实体Class.
	 */
	protected final Class<E> clazz;

	@SuppressWarnings("unchecked")
	public AbstractBasicAction() {
		this.clazz = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 获取当前的service
	 * 
	 * @return
	 */
	public abstract BasicService<E, ID> getBasicService();

	/**
	 * 权限检查
	 * 
	 * @param operation
	 */
	protected abstract void checkAuth(String operation);

	/**
	 * 获取模块的权限名称
	 * 
	 * @return
	 */
	protected abstract String getPermssionName();

	/**
	 * 视图前缀
	 * 
	 * @return
	 */
	protected abstract String getViewPrefix();

	/**
	 * 返回重定向view
	 * 
	 * @return
	 */
	protected abstract String getRedirectView();

	/**
	 * 页面form attr 属性名称
	 * 
	 * @return
	 */
	protected abstract String getModelAttr();

	/**
	 * 判断数据是否可以删除
	 * 
	 * @param id
	 * @param msgkey
	 * @return true -> 可以删除 false->不能删除
	 */
	protected abstract boolean allowDeleteData(ID id, StringBuffer msgkey);

	/**
	 * 判断数据是否可以删除
	 * 
	 * @param ids
	 * @param msgkey
	 * @return true -> 可以删除 false->不能删除
	 */
	protected abstract boolean allowDeleteData(ID[] ids, StringBuffer msgkey);

	/**
	 * 转化批量删除的主键
	 * 
	 * @param request
	 * @param entity
	 */
	protected abstract ID[] parseDeleteIDS(HttpServletRequest request);

	/**
	 * 添加显示前自定义处理过程
	 * 
	 * @param request
	 * @param entity
	 */
	protected abstract void handler4CreateShow(HttpServletRequest request,
			E entity, Model model);

	/**
	 * 更新显示前自定义处理过程
	 * 
	 * @param request
	 * @param entity
	 */
	protected abstract void handler4UpdateShow(HttpServletRequest request,
			E entity, Model model);

	/**
	 * 
	 * 
	 * @param request
	 * @param entity
	 */
	/**
	 * 保存（添加或者更新）自定义处理过程
	 * 
	 * @param request
	 * @param entity
	 * @return 如果是true 父类中执行更新实体类 false 父类中不执行更新实体类
	 */
	protected abstract boolean handler4Save(HttpServletRequest request, E entity);

	/**
	 * 添加操作的自定义处理过程
	 * 
	 * @param request
	 * @param entity
	 * @return 如果是true 父类中执行添加实体类 false 父类中不执行添加实体类
	 */
	protected abstract boolean handler4Create(HttpServletRequest request,
			E entity);

	/**
	 * 更新操作的自定义处理过程
	 * 
	 * @param request
	 * @param entity
	 * @return 如果是true 父类锺更新实体类 false 父类中不在更新实体类
	 * 
	 */
	protected abstract boolean handler4Update(HttpServletRequest request,
			E entity);

	/**
	 * 导出前自定义处理过程
	 * 
	 * @param request
	 * @param model
	 */
	protected abstract void handler4Export(HttpServletRequest request,
			Map<String, Object> model);

	/**
	 * 获取空实体对象
	 * 
	 * @return
	 */
	protected E getNewInstance() {
		try {
			return clazz.newInstance();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * 显示列表页面
	 * 
	 * @return
	 */
	@RequestMapping(params = { "method=showList" })
	public String showList() {
		checkAuth(OperationType.READ.getValue());
		return getViewPrefix() + ".list";
	}

	@RequestMapping(params = { "method=pageQuery" })
	@ResponseBody
	public Map<String, Object> pageQuery(Model model, PQ pageQuery) {
		checkAuth(OperationType.READ.getValue());
		List<E> list = getBasicService().pageQuery(pageQuery);
		Map<String, Object> retMap = new HashMap<String, Object>();
		retMap.put("total", pageQuery.getTotalCount());
		retMap.put("rows", list);
		return retMap;
	}

	@RequestMapping(params = { "method=read" })
	public String read(ID id, Model model) {
		checkAuth(OperationType.READ.getValue());
		model.addAttribute(getModelAttr(), getBasicService().findById(id));
		return getViewPrefix() + ".view";
	}

	@RequestMapping(params = { "method=getViewData" })
	@ResponseBody
	public Map<String, Object> getViewData(ID id, HttpServletRequest request) {
		checkAuth(OperationType.READ.getValue());
		Map<String, String> showMap = new HashMap<String, String>();
		RefAnnoTools.getBeanAnnoMap(clazz, showMap, messageSource,
				RequestContextUtils.getLocale(request));

		Map<String, Object> retMap = new HashMap<String, Object>();
		List<PropertyGridData> list = BeanConvert.convertPropertyGridData(
				getBasicService().findById(id), showMap);
		retMap.put("total", list.size());
		retMap.put("rows", list);
		return retMap;
	}

	@RequestMapping(params = { "method=showForm" })
	public String showForm(HttpServletRequest request, ID id, Model model) {
		E entity;
		if (null != id) {
			checkAuth(OperationType.UPDATE.getValue());
			entity = getBasicService().findById(id);
			this.handler4UpdateShow(request, entity, model);
			model.addAttribute("showFormType", "update");
		} else {
			checkAuth(OperationType.CREATE.getValue());
			entity = getNewInstance();
			this.handler4CreateShow(request, entity, model);
			model.addAttribute("showFormType", "create");
		}
		model.addAttribute(this.getModelAttr(), entity);
		return getViewPrefix() + ".form";
	}

	@RequestMapping(params = { "method=saveOrUpdate" })
	@ResponseBody
	public String saveOrUpdate(E entity, HttpServletRequest request) {
		checkAuth(OperationType.CREATE.getValue());
		if (this.handler4Save(request, entity)) {
			getBasicService().saveOrUpdate(entity);
		}
		String message = messageSource.getMessage(GlobalConstant.MSG_SUCC,
				null, RequestContextUtils.getLocale(request));
		return message;
	}

	@RequestMapping(params = { "method=create" })
	@ResponseBody
	public String create(E entity, HttpServletRequest request) {
		checkAuth(OperationType.CREATE.getValue());
		if (this.handler4Create(request, entity)) {
			getBasicService().saveOrUpdate(entity);
		}
		String message = messageSource.getMessage(GlobalConstant.MSG_SUCC,
				null, RequestContextUtils.getLocale(request));
		return message;
	}

	@RequestMapping(params = { "method=update" })
	@ResponseBody
	public String update(E entity, HttpServletRequest request) {
		checkAuth(OperationType.UPDATE.getValue());
		if (this.handler4Update(request, entity)) {
			getBasicService().update(entity);
		}
		String message = messageSource.getMessage(GlobalConstant.MSG_SUCC,
				null, RequestContextUtils.getLocale(request));
		return message;
	}

	@RequestMapping(params = "method=deleteBatch")
	@ResponseBody
	public String deleteBatch(HttpServletRequest request) {
		checkAuth(OperationType.DELETE.getValue());
		String message = null;
		try {
			ID[] ids = this.parseDeleteIDS(request);
			StringBuffer msgkey = new StringBuffer();
			if (this.allowDeleteData(ids, msgkey)) {
				getBasicService().delete(ids);
				message = messageSource.getMessage(GlobalConstant.MSG_SUCC,
						null, RequestContextUtils.getLocale(request));
			} else {
				message = messageSource.getMessage(msgkey.toString(), null,
						RequestContextUtils.getLocale(request));
			}
		} catch (Exception e) {
			message = messageSource.getMessage(GlobalConstant.MSG_FAILED, null,
					RequestContextUtils.getLocale(request));
		}
		return message;
	}

	@RequestMapping(params = { "method=delete" })
	public String delete(ID id, RedirectAttributes redirectAttributes,
			HttpServletRequest request) {
		checkAuth(OperationType.DELETE.getValue());
		StringBuffer msgkey = new StringBuffer();
		String message = null;
		if (allowDeleteData(id, msgkey)) {
			getBasicService().delete(id);
			message = messageSource.getMessage(GlobalConstant.MSG_SUCC, null,
					RequestContextUtils.getLocale(request));
		} else {
			message = messageSource.getMessage(msgkey.toString(), null,
					RequestContextUtils.getLocale(request));
		}
		redirectAttributes.addFlashAttribute("message", message);
		return this.getRedirectView();
	}

	@RequestMapping(params = { "method=export" }, method = RequestMethod.POST)
	public ModelAndView export(HttpServletRequest request, PQ pageQuery,
			String exportType) {
		checkAuth(OperationType.EXPORT.getValue());
		getBasicService().exportPageQuery(pageQuery);
		List<E> list = pageQuery.getQueryResults();
		Map<String, Object> model = new HashMap<String, Object>();
		this.handler4Export(request, model);

		this.handler4Export(request, model);
		model.put(GlobalConstant.EXPORT_ROW_DATA, list);
		if ("POI".equals(exportType)) {
			return new ModelAndView(new PoiExcelView(), model);
		} else if ("JXL".equals(exportType)) {
			return new ModelAndView(new JxlExcelView(), model);
		} else if ("CSV".equals(exportType)) {
			return new ModelAndView(new CsvView(), model);
		}
		return new ModelAndView(new PdfiText5View(), model);
	}

}
