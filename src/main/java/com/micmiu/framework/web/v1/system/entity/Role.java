package com.micmiu.framework.web.v1.system.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.micmiu.framework.anno.ShowParam;
import com.micmiu.framework.web.v1.base.entity.IdEntity;
import com.micmiu.framework.web.v1.base.vo.OperationType;
import com.micmiu.modules.support.spring.I18nMessageParser;

@Entity
@Table(name = "T_SYS_ROLE")
public class Role extends IdEntity {

	@ShowParam("system.role.roleNamee")
	@Column(name = "ROLE_NAME")
	private String roleName;

	private List<Permission> permssions = new ArrayList<Permission>();

	@Column(name = "ROLE_NAME", length = 50)
	public String getRoleName() {
		return roleName;
	}

	/**
	 * 操作权限集合.
	 */
	@ManyToMany
	@JoinTable(name = "T_SYS_R2P", joinColumns = { @JoinColumn(name = "ROLE_ID") }, inverseJoinColumns = { @JoinColumn(name = "PERM_ID") })
	@Fetch(FetchMode.SUBSELECT)
	@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
	public List<Permission> getPermssions() {
		return permssions;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public void setPermssions(List<Permission> permssions) {
		this.permssions = permssions;
	}

	@ShowParam(value = "system.role.nodes", width = 500)
	@Transient
	public String getPermissionNames() {
		List<String> list = Lists.newArrayList();
		for (Permission permssion : getPermssions()) {
			try {
				String resName = I18nMessageParser.getInstance().getMessage(
						permssion.getResCnName());
				String oper = I18nMessageParser.getInstance().getMessage(
						OperationType.parse(permssion.getOperation())
								.getDisplay());
				list.add(resName + ":" + oper);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return StringUtils.join(list, ",");
	}

}
