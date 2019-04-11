package com.cour.ebenus.maven.ebenus.dao.IDao;

import java.util.List;
public interface IDao<T> {
	
	 public List<T> findAll();

	    public T findById(int id);

	    public List<T> findByCriteria(String criteria, Object valueCriteria);

	    public T create(T t);

	    public T update(T t);

	    public boolean delete(T t);

	    public List<T> sendQuery(String query, T t);
}
