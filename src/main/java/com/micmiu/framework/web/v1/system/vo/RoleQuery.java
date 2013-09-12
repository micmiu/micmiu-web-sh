package com.micmiu.framework.web.v1.system.vo;

import java.util.HashMap;

import org.apache.commons.lang.StringUtils;

import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;
import com.micmiu.framework.web.v1.system.entity.Role;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
public class RoleQuery extends AbstractPagedQuery<Role> {

	private String roleName;

	@Override
	public String buildSQL(HashMap<String, Object> queryParams,
			boolean isQueryTotalCount) {
		StringBuilder sql = new StringBuilder();

		if (isQueryTotalCount) {// 查询总数
			sql.append("select count(*) from Role t where (1=1)");
		} else {
			sql.append("select t from Role t where (1=1)");
		}

		if (StringUtils.isNotBlank(roleName)) {
			queryParams.put("roleName", "%" + this.roleName + "%");
			sql.append(" and (t.roleName like:roleName)");
		}

		return sql.toString();
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

}
