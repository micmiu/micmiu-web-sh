package com.micmiu.framework.web.v1.base.dao;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.micmiu.modules.orm.OrderType;

/**
 * 通用泛型DAO：实现基础CRUD <br>
 * get*方法:一般返回唯一的实体类E <br>
 * find*方法:一般返回的查询结果集List
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <E> 实体类
 * @param <PK> 主键
 */
public interface GenericDao<E, PK extends Serializable> {

	/**
	 * 保存实体
	 * 
	 * @param entity 实体对象
	 */
	public void save(E entity);

	/**
	 * @param entity 实体对象
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 根据主键删除实体
	 * 
	 * @param pk 主键
	 */
	public void delete(PK pk);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void delete(E entity);

	/**
	 * 修改实体
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * 根据注解获取实体
	 * 
	 * @param pk
	 * @return 实体
	 */
	public E getById(PK pk);

	/**
	 * 查找全部实体
	 * 
	 * @return 所有实体的列表
	 */
	public List<E> findAll();

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