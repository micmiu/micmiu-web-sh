package com.micmiu.framework.web.v1.base.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * 统一定义id的entity基类.
 * 
 * 基类统一定义id的属性名称、数据类型、列名映射及生成策略. <br>
 * 子类可重载getId()函数重定义id的列名映射和生成策略.
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 */
@MappedSuperclass
public abstract class IdEntity {

	protected Long id;

	@Id
	@Column(name = "ID")
	@GeneratedValue
	// @GeneratedValue(strategy = GenerationType.SEQUENCE)
	// @GeneratedValue(generator = "system-uuid")
	// @GenericGenerator(name = "system-uuid", strategy = "uuid")
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
