package com.micmiu.framework.web.v1.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.criterion.Criterion;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.util.Assert;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public class BaseCRUDDao<T, ID extends Serializable> extends
		HibernateDaoSupport {

	/**
	 * 实体Class.
	 */
	private final Class<T> entityClass;

	/**
	 * 构造函数，确定实体类型.
	 */
	public BaseCRUDDao() {
		this.entityClass = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 按ID返回实体对象.
	 * 
	 * @param id
	 * @return T 按返回实体。
	 */
	public T get(ID id) {
		return (T) getSession().get(entityClass, id);
	}

	/**
	 * 查询所有的实体
	 * 
	 * @param entityClass
	 * @return
	 */
	public List<T> findAll(Class<T> entityClass) {
		return getHibernateTemplate().loadAll(entityClass);
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 */
	public void save(final T entity) {
		getHibernateTemplate().save(entity);
	}

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 */
	public void update(T entity) {
		getHibernateTemplate().merge(entity);
	}

	/**
	 * 保存或更新实体对象.
	 * 
	 * @param entity
	 */
	public void saveOrUpdate(T entity) {
		getHibernateTemplate().saveOrUpdate(entity);
	}

	/**
	 * 删除实体对象
	 * 
	 * @param entityId
	 */
	public void delete(ID id) {
		T t = get(id);
		if (null != t) {
			getHibernateTemplate().delete(t);
		}
	}

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 */
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getHibernateTemplate().delete(entity);
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 */

	public List<T> find(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 */
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * @param criterions
	 */
	private Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 */
	public <X> List<X> find(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> List<X> find(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 */
	public <X> X findUnique(final String hql, final Object... values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 *            命名参数,按名称绑定.
	 */
	public <X> X findUnique(final String hql, final Map<String, ?> values) {
		return (X) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 执行HQL操作.
	 * 
	 * @param values
	 * @return 更新记录数.
	 */
	public int executeHQL(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL操作.
	 * 
	 * @param values
	 * @return 更新记录数.
	 */
	public int executeHQL(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 */
	private Query createQuery(final String hql, final Object... values) {
		Assert.hasText(hql, "hql不能为空");
		Query query = getSession().createQuery(hql);
		if (values != null) {
			for (int i = 0; i < values.length; i++) {
				query.setParameter(i, values[i]);
			}
		}
		return query;
	}

	/**
	 * 根据查询HQL与参数列表创建Query对象.
	 * 
	 * @param values
	 */
	private Query createQuery(final String hql, final Map<String, ?> values) {
		Assert.hasText(hql, "HQL 不能为空");
		Query query = getSession().createQuery(hql);
		if (values != null) {
			query.setProperties(values);
		}
		return query;
	}
}
