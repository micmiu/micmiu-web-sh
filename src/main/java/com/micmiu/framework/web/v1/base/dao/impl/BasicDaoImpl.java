package com.micmiu.framework.web.v1.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.util.Assert;

import com.micmiu.framework.web.v1.base.dao.BasicDao;
import com.micmiu.framework.web.v1.base.vo.AbstractPagedQuery;

/**
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <T>
 * @param <ID>
 */
@SuppressWarnings("unchecked")
public abstract class BasicDaoImpl<T, ID extends Serializable> implements
		BasicDao<T, ID> {

	/**
	 * 实体Class.
	 */
	private final Class<T> clazz;

	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 构造函数，确定实体类型.
	 */
	public BasicDaoImpl() {
		this.clazz = (Class<T>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	/**
	 * 按ID返回实体对象.
	 * 
	 * @param id
	 * @return T 按返回实体。
	 */
	@Override
	public T findById(ID id) {
		return (T) getSession().get(clazz, id);
	}

	/**
	 * 查询所有的实体
	 * 
	 * @param clazz
	 * @return
	 */
	@Override
	public List<T> findAll() {
		return createCriteria().list();
	}

	/**
	 * 保存实体对象
	 * 
	 * @param entity
	 */
	@Override
	public ID save(final T entity) {
		return (ID) getSession().save(entity);
	}

	/**
	 * 更新实体对象.
	 * 
	 * @param entity
	 */
	@Override
	public void update(T entity) {
		getSession().merge(entity);
	}

	/**
	 * 保存或更新实体对象.
	 * 
	 * @param entity
	 */
	@Override
	public void saveOrUpdate(T entity) {
		getSession().saveOrUpdate(entity);
	}

	/**
	 * 删除实体对象
	 * 
	 * @param entityId
	 */
	@Override
	public void delete(ID id) {
		T e = findById(id);
		if (null != e) {
			getSession().delete(e);
			e = null;
		}
	}

	/**
	 * 删除实体对象.
	 * 
	 * @param entity
	 */
	@Override
	public void delete(final T entity) {
		Assert.notNull(entity, "entity不能为空");
		getSession().delete(entity);
	}

	/**
	 * 分页查询
	 * 
	 * @param query
	 * @return List<T> 返回分页查询结果
	 */
	public List<T> pageQuery(AbstractPagedQuery<T> page) {
		if (page.getTotalCount() == -1) {// 查询总数
			Query query = page.buildQuery(this.getSession(), true);
			long totalCount = (Long) query.uniqueResult();
			page.setTotalCount(totalCount);
		}
		Query query = page.buildQuery(this.getSession(), false);
		query.setFirstResult(page.getStart());
		query.setMaxResults(page.getRp());
		List<T> list = query.list();
		page.setQueryResults(list);
		return page.getQueryResults();
	}

	@Override
	public List<T> exportPageQuery(AbstractPagedQuery<T> page) {
		Query query = page.buildQuery(this.getSession(), false);
		List<T> list = query.list();
		page.setQueryResults(list);
		return page.getQueryResults();
	}

	/**
	 * 按属性查找对象列表, 匹配方式为相等.
	 */
	public List<T> findList(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "查询属性不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return findList(criterion);
	}

	/**
	 * 按属性查找唯一对象, 匹配方式为相等.
	 */
	public T findUnique(final String propertyName, final Object value) {
		Assert.hasText(propertyName, "查询属性不能为空");
		Criterion criterion = Restrictions.eq(propertyName, value);
		return (T) findUnique(criterion);
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values
	 */
	@Override
	public List<T> findList(final String hql, final Object... values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	@Override
	public List<T> findList(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values
	 */
	@Override
	public T findUnique(final String hql, final Object... values) {
		return (T) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param values 命名参数,按名称绑定.
	 */
	@Override
	public T findUnique(final String hql, final Map<String, ?> values) {
		return (T) createQuery(hql, values).uniqueResult();
	}

	/**
	 * 按Criteria查询对象列表.
	 * 
	 * @param criterions
	 */
	@Override
	public List<T> findList(final Criterion... criterions) {
		return createCriteria(criterions).list();
	}

	/**
	 * 按Criteria查询唯一对象.
	 * 
	 * @param criterions
	 */
	@Override
	public T findUnique(final Criterion... criterions) {
		return (T) createCriteria(criterions).uniqueResult();
	}

	/**
	 * 执行HQL操作.
	 * 
	 * @param values
	 * @return 更新记录数.
	 */
	@Override
	public int executeHQL(final String hql, final Object... values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 执行HQL操作.
	 * 
	 * @param values
	 * @return 更新记录数.
	 */
	@Override
	public int executeHQL(final String hql, final Map<String, ?> values) {
		return createQuery(hql, values).executeUpdate();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param params
	 */
	public <X> List<X> findListObject(final String hql, final Object... params) {
		return createQuery(hql, params).list();
	}

	/**
	 * 按HQL查询对象列表.
	 * 
	 * @param params
	 */
	public <X> List<X> findListObject(final String hql,
			final Map<String, ?> params) {
		return createQuery(hql, params).list();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param params 数量可变的参数,按顺序绑定.
	 */
	public <X> X findUniqueObject(final String hql, final Object... params) {
		return (X) createQuery(hql, params).uniqueResult();
	}

	/**
	 * 按HQL查询唯一对象.
	 * 
	 * @param paramMap 命名参数,按名称绑定.
	 */
	public <X> X findUniqueObject(final String hql,
			final Map<String, ?> paramMap) {
		return (X) createQuery(hql, paramMap).uniqueResult();
	}

	/**
	 * 根据Criterion条件创建Criteria.
	 * 
	 * @param criterions
	 */
	private Criteria createCriteria(final Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(clazz);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
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
