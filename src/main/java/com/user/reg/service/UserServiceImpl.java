package com.user.reg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.dao.UserDao;
import com.user.reg.domain.Profile;
import com.user.reg.domain.User;

@Service
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Override
	public long add(User t) {
		// TODO Auto-generated method stub
		return (long) userDao.add(t);
	}

	@Override
	public void update(User t) {
		// TODO Auto-generated method stub
		userDao.update(t);
	}

	@Override
	public void delete(User t) {
		// TODO Auto-generated method stub
		userDao.delete(t);
	}

	@Override
	public User get(long id) {
		// TODO Auto-generated method stub
		return userDao.get(id);
	}

	@Override
	public List<User> getAll() {
		// TODO Auto-generated method stub
		return userDao.getAll();
	}

	@Override
	public User getByEmail(String email) {
		// TODO Auto-generated method stub
		return userDao.getByEmail(email);
	}

	@Override
	public List<User> getUsersByRole(Profile role) {
		// TODO Auto-generated method stub
		return userDao.getUsersByRole(role);
	}

}
