package com.user.reg.dao;

public interface EntityEmailDao<T> {

	T getByEmail(String email);
	
}
