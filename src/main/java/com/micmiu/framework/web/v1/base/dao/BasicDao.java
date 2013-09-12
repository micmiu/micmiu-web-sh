package com.micmiu.framework.web.v1.base.dao;

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
public interface BasicDao<E, ID extends Serializable> {

	/**
	 * 按ID返回实体对象.
	 * 
	 * @param id
	 * @return T 按返回实体。
	 */
	public E findById(ID id);

	/**
	 * 查询所有的实体
	 * 
	 * @param entityClass
	 * @return
	 */
	public List<E> findAll();

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 */
	public ID save(final E entity);

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 */
	public void update(E entity);

	/**
	 * 保存或更新实体对象.
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(E entity);

	/**
	 * 删除实体对象
	 * 
	 * @param entityId
	 */
	public void delete(ID id);

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 */
	public void delete(final E entity);

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @return List<E> 返回分页查询结果
	 */
	public List<E> pageQuery(AbstractPagedQuery<E> page);

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @return List<E> 返回分页查询结果
	 */
	public List<E> exportPageQuery(AbstractPagedQuery<E> page);

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
	 * 执行HQL操作.
	 * 
	 * @param params
	 * @return 更新记录数.
	 */
	public int executeHQL(final String hql, final Object... params);

	/**
	 * 执行HQL操作.
	 * 
	 * @param paramMap
	 * @return 更新记录数.
	 */
	public int executeHQL(final String hql, final Map<String, ?> paramMap);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param params 数量可变的参数,按顺序绑定.
	 */
	public <X> List<X> findListObject(final String hql, final Object... params);

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param paramMap 命名参数,按名称绑定.
	 */
	public <X> List<X> findListObject(final String hql,
			final Map<String, ?> paramMap);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param params 数量可变的参数,按顺序绑定.
	 */
	public <X> X findUniqueObject(final String hql, final Object... params);

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param paramMap 命名参数,按名称绑定.
	 */
	public <X> X findUniqueObject(final String hql,
			final Map<String, ?> paramMap);

}
