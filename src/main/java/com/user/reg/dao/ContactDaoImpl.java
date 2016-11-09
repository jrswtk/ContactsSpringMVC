package com.user.reg.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.reg.domain.Contact;
import com.user.reg.utils.SessionUtil;

@Repository
public class ContactDaoImpl implements ContactDao {

	@Autowired
	private SessionUtil sessionUtil;
	
	@Override
	public long add(Contact t) {
		// TODO Auto-generated method stub
		return (long) getSession().save(t);
	}

	@Override
	public void update(Contact t) {
		// TODO Auto-generated method stub
		getSession().update(t);
	}

	@Override
	public void delete(Contact t) {
		// TODO Auto-generated method stub
		getSession().delete(t);
	}
	
	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub
		String query = "DELETE FROM Contact";
		getSession().createQuery(query).executeUpdate();
	} 

	@Override
	public Contact get(long id) {
		// TODO Auto-generated method stub
		return (Contact) getSession().get(Contact.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getAll() {
		// TODO Auto-generated method stub
		String query = "FROM Contact c ORDER BY c.id";
		return (List<Contact>) getSession().createQuery(query)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Contact> getContactsByUserId(long userId) {
		// TODO Auto-generated method stub
		return (List<Contact>) getSession().createCriteria(Contact.class)
				.add(Restrictions.eq("user.id", userId))
				.list();
	}
	
	private Session getSession() {
		return sessionUtil.getSession();
	}

}
