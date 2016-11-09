package com.user.reg.dao;

import java.util.List;

import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.reg.domain.Registration;
import com.user.reg.utils.SessionUtil;

@Repository
public class RegistrationDaoImpl implements RegistrationDao {

	@Autowired
	private SessionUtil sessionUtil;
	
	@Override
	public long add(Registration t) {
		// TODO Auto-generated method stub
		return (long) getSession().save(t);
	}

	@Override
	public void update(Registration t) {
		// TODO Auto-generated method stub
		getSession().update(t);
	}

	@Override
	public void delete(Registration t) {
		// TODO Auto-generated method stub
		getSession().delete(t);
	}

	@Override
	public Registration get(long id) {
		// TODO Auto-generated method stub
		return (Registration) getSession().get(Registration.class, id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Registration> getAll() {
		// TODO Auto-generated method stub
		String query = "FROM Registration r ORDER BY r.id";
		return (List<Registration>) getSession().createQuery(query).list();
	}
	
	@Override
	public Registration getByEmail(String email) {
		// TODO Auto-generated method stub
		String query = "FROM Registration r WHERE r.email = :email";
		return (Registration) getSession().createQuery(query)
				.setString("email", email)
				.uniqueResult();
	}
	
	private Session getSession() {
		return sessionUtil.getSession();
	}

}
