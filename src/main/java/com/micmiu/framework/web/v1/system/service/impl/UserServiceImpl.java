package com.micmiu.framework.web.v1.system.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.service.impl.BasicServiceImpl;
import com.micmiu.framework.web.v1.system.dao.UserDao;
import com.micmiu.framework.web.v1.system.entity.User;
import com.micmiu.framework.web.v1.system.service.UserService;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@Service("userService")
public class UserServiceImpl extends BasicServiceImpl<User, Long> implements
		UserService {

	private UserDao userDao;

	@Override
	public BasicDao<User, Long> getBasicDao() {
		return this.userDao;
	}

	@Override
	public User getUserByLoginName(String loginName) {
		return userDao.findUnique("loginName", loginName);
	}

	@Override
	public String getPassword(String loginName) {
		return getUserByLoginName(loginName).getPassword();
	}

	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

}
