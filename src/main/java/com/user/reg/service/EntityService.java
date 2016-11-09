package com.user.reg.service;

import java.util.List;

public interface EntityService<T> {

	long add(T t);
	void update(T t);
	void delete(T t);
	T get(long id);
	List<T> getAll();
	
}
