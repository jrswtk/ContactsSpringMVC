package com.user.reg.dao;

import java.util.List;

import com.user.reg.domain.Contact;

public interface ContactDao extends EntityDao<Contact> {

	void deleteAll();	
	List<Contact> getContactsByUserId(long userId);
	
}
