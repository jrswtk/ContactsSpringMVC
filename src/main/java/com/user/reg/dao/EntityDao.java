package com.user.reg.dao;

import java.util.List;

public interface EntityDao<T> {

	long add(T t);
	void update(T t);
	void delete(T t);
	T get(long id);
	List<T> getAll();
	
}
