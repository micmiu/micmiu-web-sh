package com.micmiu.framework.web.v1.base.service;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.Criterion;

import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <E>
 * @param <ID>
 */
public interface BasicService<E, ID extends Serializable> {

	/**
	 * 保存实体
	 * 
	 * @param entity
	 * @return 保存后得到的id
	 */
	public ID save(E entity);

	/**
	 * 
	 * @param entity
	 * @return 保存后得到的id
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void delete(ID id);

	/**
	 * 删除实体
	 * 
	 * @param entity
	 */
	public void delete(E entity);

	/**
	 * 根据ID批量删除实体
	 * 
	 * @param ids
	 */
	public void delete(ID[] ids);

	/**
	 * 修改实体
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * 
	 * 通过名字查找
	 * 
	 * @param id
	 * @return 找到的实体
	 */
	public E findById(ID id);

	/**
	 * 查找全部实体
	 * 
	 * @return 所有实体的列表
	 */
	public List<E> findAll();

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @return List
	 */
	public List<E> pageQuery(AbstractPagedQuery<E> page);

	/**
	 * 按属性查找对象列表.
	 * 
	 * @param criterions
	 */

	public List<E> findList(final String propertyName, final Object value);

	/**
	 * 按属性查找唯一的对象.
	 * 
	 * @param criterions
	 */
	public E findUnique(final String propertyName, final Object value);

	/**
	 * 按HQL、可变参数列表 查询对象列表.
	 * 
	 * @param params
	 */
	public List<E> findList(final String hql, final Object... params);

	/**
	 * 按HQL、可变参数列表 查询对象列表.
	 * 
	 * @param paramMap 命名参数,按名称绑定.
	 */
	public List<E> findList(final String hql, final Map<String, ?> paramMap);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param params
	 */
	public E findUnique(final String hql, final Object... params);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param paramMap 命名参数,按名称绑定.
	 */
	public E findUnique(final String hql, final Map<String, ?> paramMap);

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 */
	public E findUnique(final Criterion... criterions);

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 */

	public List<E> findList(final Criterion... criterions);

	/**
	 * 导出查询
	 * 
	 * @param query
	 * @return List
	 */
	public List<E> exportPageQuery(AbstractPagedQuery<E> page);

}
