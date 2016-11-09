package com.user.reg.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.reg.domain.Profile;
import com.user.reg.domain.User;
import com.user.reg.utils.SessionUtil;

@Repository
public class UserDaoImpl implements UserDao {
	
	@Autowired
	private SessionUtil sessionUtil;
	
	@Override
	public long add(User user) {
		// TODO Auto-generated method stub
		return (long) getSession().save(user);
	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub
		getSession().update(user);
	}

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return (User) getSession().get(User.class, id);
	}
	
	@Override
	public void delete(User user) {
		// TODO Auto-generated method stub
		getSession().delete(user);
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		String query = "FROM User u WHERE u.email = :email";
		return (User) getSession().createQuery(query)
				.setString("email", email)
				.uniqueResult();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		String query = "FROM User u ORDER BY u.id";
		return (List<User>) getSession().createQuery(query)
				.list();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsersByRole(Profile role) {
		// TODO Auto-generated method stub
		return (List<User>) getSession().createCriteria(User.class)
				.add(Restrictions.eq("role.id", role.getId()))
				.list();
	}
	
	private Session getSession() {
		return sessionUtil.getSession();
	}

}
