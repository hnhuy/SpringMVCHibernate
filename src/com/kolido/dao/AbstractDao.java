package com.kolido.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class AbstractDao<PK extends Serializable, T> {

	private Class<T> persistentClass;

	@Autowired
	private SessionFactory sessionFactory;

	@SuppressWarnings("unchecked")
	public AbstractDao() {
		this.persistentClass = (Class<T>) ((ParameterizedType) this.getClass().getGenericSuperclass())
				.getActualTypeArguments()[1];
	}
	
	protected Session getCurrentSession(){
		return sessionFactory.getCurrentSession();
	}
	
	
	public T getByKey(PK id){
		return (T) getCurrentSession().get(persistentClass, id);
	}
	
	public void persist(T entity){
		getCurrentSession().persist(entity);
	}
	
	public void delete(T entity){
		getCurrentSession().delete(entity);
	}
	
	
	public Criteria createEntityCriteria(){
		return getCurrentSession().createCriteria(persistentClass);
	}
}



















