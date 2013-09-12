package com.micmiu.framework.web.v1.base.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.micmiu.modules.orm.OrderType;

/**
 * 扩展基础CRUD的DAO增加多种查询方法
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * 
 * @param <E> 实体类
 * @param <PK> 主键
 */
public interface GenericQueryDao<E, PK extends Serializable> extends
		GenericDao<E, PK> {

	/**
	 * 按属性查找唯一的实体对象.
	 * 
	 * @param filedName 字段名
	 * @param filedValue 字段值
	 * @return E 实体对象
	 */
	public E get(String filedName, Object filedValue);

	/**
	 * 按属性查找实体对象集合.
	 * 
	 * @param filedName 字段名
	 * @param filedValue 字段值
	 * @return List<E> 实体对象集合
	 */
	public List<E> find(String filedName, Object filedValue);

	/**
	 * 按字段查找实体列表.
	 * 
	 * @param queryParams 查询字段 <字段名,字段值>
	 * @return List<实体列表>
	 */
	public List<E> find(Map<String, Object> queryParams);

	/**
	 * 根据查询条件和排序字段返回查询列表
	 * 
	 * @param queryParams 查询字段 <字段名,字段值>
	 * @param orderParams 排序字段 <字段名,正序/倒序>
	 * @return List<实体列表>
	 */
	public List<E> find(Map<String, Object> queryParams,
			LinkedHashMap<String, OrderType> orderParams);

}
