package com.micmiu.framework.web.v1.system.service;

import com.micmiu.framework.web.v1.base.service.BasicService;
import com.micmiu.framework.web.v1.system.entity.Role;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public interface RoleService extends BasicService<Role, Long> {

	Role getRoleByName(String name);
}
