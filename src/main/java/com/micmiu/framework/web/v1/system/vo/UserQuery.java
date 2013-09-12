package com.micmiu.framework.web.v1.system.vo;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;
import com.micmiu.framework.web.v1.system.entity.User;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class UserQuery extends AbstractPagedQuery<User> {

	private String loginName;

	private String name;

	@Override
	public String buildSQL(HashMap<String, Object> queryParams,
			boolean isQueryTotalCount) {
		StringBuilder sql = new StringBuilder();

		if (isQueryTotalCount) {// 查询总数
			sql.append("select count(*) from User t where (1=1)");
		} else {
			sql.append("select t from User t where (1=1)");
		}

		if (StringUtils.isNotBlank(loginName)) {
			queryParams.put("loginName", "%" + this.loginName + "%");
			sql.append(" and (t.loginName like:loginName)");
		}

		if (StringUtils.isNotBlank(name)) {
			queryParams.put("name", "%" + this.name + "%");
			sql.append(" and (t.name like:name)");
		}

		return sql.toString();
	}

	public String getLoginName() {
		return loginName;
	}

	public String getName() {
		return name;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public void setName(String name) {
		this.name = name;
	}

}
