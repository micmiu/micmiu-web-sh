package com.micmiu.framework.web.v1.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.service.impl.BasicServiceImpl;
import com.micmiu.framework.web.v1.system.dao.PermissionDao;
import com.micmiu.framework.web.v1.system.entity.Permission;
import com.micmiu.framework.web.v1.system.service.PermissionService;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Service("permissionService")
public class PermissionServiceImpl extends BasicServiceImpl<Permission, Long>
		implements PermissionService {

	private PermissionDao permissionDao;

	@Override
	public BasicDao<Permission, Long> getBasicDao() {
		return this.permissionDao;
	}

	@Autowired
	public void setPermissionDao(PermissionDao permissionDao) {
		this.permissionDao = permissionDao;
	}

}
