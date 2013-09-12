package com.micmiu.framework.web.v1.system.service;

import java.util.List;

import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.system.entity.Menu;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public interface MenuService extends BasicService<Menu, Long> {

	List<Menu> getRootMenuByOrder();

}
