package com.user.reg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.dao.ContactDao;
import com.user.reg.domain.Contact;

@Service
@Transactional
public class ContactServiceImpl implements ContactService {

	@Autowired
	private ContactDao contactDao;
	
	@Override
	public long add(Contact t) {
		// TODO Auto-generated method stub
		return (long) contactDao.add(t);
	}

	@Override
	public void update(Contact t) {
		// TODO Auto-generated method stub
		contactDao.update(t);
	}

	@Override
	public void delete(Contact t) {
		// TODO Auto-generated method stub
		contactDao.delete(t);
	}

	@Override
	public Contact get(long id) {
		// TODO Auto-generated method stub
		return contactDao.get(id);
	}

	@Override
	public List<Contact> getAll() {
		// TODO Auto-generated method stub
		return contactDao.getAll();
	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		contactDao.deleteAll();
	}

	@Override
	public List<Contact> getContactsByUserId(long userId) {
		// TODO Auto-generated method stub
		return contactDao.getContactsByUserId(userId);
	}

}
