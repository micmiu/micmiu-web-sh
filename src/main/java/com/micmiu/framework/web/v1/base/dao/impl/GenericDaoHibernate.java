package com.micmiu.framework.web.v1.base.dao.impl;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Required;

import com.micmiu.framework.web.v1.base.dao.GenericDao;
import com.micmiu.modules.orm.OrderType;

/**
 * Hibernate 实现通用的Dao <br>
 * get*方法:一般返回唯一的实体类E <br>
 * find*方法:一般返回的查询结果集List
 * 
 * @author <a href="http://www.micmiu.com">Michael Sun</a>
 * @param <E> 实体类
 * @param <PK> 主键
 */
public abstract class GenericDaoHibernate<E, PK extends Serializable>
		implements GenericDao<E, PK> {

	private static final Logger logger = LoggerFactory
			.getLogger(GenericDaoHibernate.class);
	/**
	 * 实体Class.
	 */
	private final Class<E> clazz;

	private SessionFactory sessionFactory;

	@Autowired
	@Required
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	/**
	 * 构造函数，确定实体类型.
	 */
	@SuppressWarnings("unchecked")
	public GenericDaoHibernate() {
		this.clazz = (Class<E>) ((ParameterizedType) getClass()
				.getGenericSuperclass()).getActualTypeArguments()[0];
	}

	@Override
	public void save(E entity) {
		getSession().save(entity);

	}

	@Override
	public void saveOrUpdate(E entity) {
		getSession().saveOrUpdate(entity);

	}

	@Override
	public void delete(PK pk) {
		E entity = this.getById(pk);
		if (null != entity) {
			getSession().delete(entity);
		} else {
			logger.warn("delete entity:" + clazz + " but not exit with pk="
					+ pk);
		}

	}

	@Override
	public void delete(E entity) {
		getSession().delete(entity);

	}

	@Override
	public void update(E entity) {
		getSession().update(entity);

	}

	@SuppressWarnings("unchecked")
	@Override
	public E getById(PK pk) {
		return (E) getSession().get(clazz, pk);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<E> findAll() {
		return (List<E>) getSession().createCriteria(clazz).list();
	}

	@Override
	public E get(String filedName, Object filedValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> find(String filedName, Object filedValue) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> find(Map<String, Object> queryParams) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<E> find(Map<String, Object> queryParams,
			LinkedHashMap<String, OrderType> orderParams) {
		// TODO Auto-generated method stub
		return null;
	}

}
