package com.user.reg.service;

import java.util.List;

import com.user.reg.domain.Contact;

public interface ContactService extends EntityService<Contact> {

	void deleteAll();
	List<Contact> getContactsByUserId(long userId);
	
}
