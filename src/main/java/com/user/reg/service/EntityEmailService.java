package com.user.reg.service;

public interface EntityEmailService<T> {

	T getByEmail(String email);
	
}
