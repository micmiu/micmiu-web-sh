package com.micmiu.framework.web.v1.system.service;

import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.system.entity.User;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public interface UserService extends BasicService<User, Long> {
	User getUserByLoginName(String loginName);

	String getPassword(String loginName);
}
