package com.micmiu.framework.web.v1.system.dao;

import org.springframework.stereotype.Repository;

import com.micmiu.framework.web.v1.base.dao.impl.BasicDaoImpl;
import com.micmiu.framework.web.v1.system.entity.User;

@Repository
public class UserDao extends BasicDaoImpl<User, Long> {

}
