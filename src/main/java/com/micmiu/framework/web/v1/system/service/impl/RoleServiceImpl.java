package com.micmiu.framework.web.v1.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.service.impl.BasicServiceImpl;
import com.micmiu.framework.web.v1.system.dao.RoleDao;
import com.micmiu.framework.web.v1.system.entity.Role;
import com.micmiu.framework.web.v1.system.service.RoleService;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Service("roleService")
public class RoleServiceImpl extends BasicServiceImpl<Role, Long> implements
		RoleService {

	private RoleDao roleDao;

	@Override
	public BasicDao<Role, Long> getBasicDao() {
		return this.roleDao;
	}

	@Autowired
	public void setRoleDao(RoleDao roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public Role getRoleByName(String name) {
		return roleDao.findUnique("roleName", name);
	}
}
