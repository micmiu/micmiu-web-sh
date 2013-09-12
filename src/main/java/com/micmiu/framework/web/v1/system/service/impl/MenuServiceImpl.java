package com.micmiu.framework.web.v1.system.service.impl;

import java.util.List;

import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.service.impl.BasicServiceImpl;
import com.micmiu.framework.web.v1.system.dao.MenuDao;
import com.micmiu.framework.web.v1.system.entity.Menu;
import com.micmiu.framework.web.v1.system.service.MenuService;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Service("menuService")
public class MenuServiceImpl extends BasicServiceImpl<Menu, Long> implements
		MenuService {

	private MenuDao menuDao;

	@Override
	public BasicDao<Menu, Long> getBasicDao() {
		return this.menuDao;
	}

	@Autowired
	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Menu> getRootMenuByOrder() {
		Criterion top = Restrictions.isNull("parent");
		return (List<Menu>) this.menuDao.getSession()
				.createCriteria(Menu.class).add(top)
				.addOrder(Order.asc("orderNum")).list();
	}

}
