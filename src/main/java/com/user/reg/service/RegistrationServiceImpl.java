package com.user.reg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.dao.RegistrationDaoImpl;
import com.user.reg.domain.Registration;

@Service
public class RegistrationServiceImpl implements RegistrationService {

	@Autowired
	private RegistrationDaoImpl registrationDao;
	
	@Override
	public long add(Registration t) {
		// TODO Auto-generated method stub
		return registrationDao.add(t);
	}

	@Override
	public void update(Registration t) {
		// TODO Auto-generated method stub
		registrationDao.update(t);
	}

	@Override
	public void delete(Registration t) {
		// TODO Auto-generated method stub
		registrationDao.delete(t);
	}

	@Override
	public Registration get(long id) {
		// TODO Auto-generated method stub
		return registrationDao.get(id);
	}
	
	@Override
	public Registration getByEmail(String email) {
		// TODO Auto-generated method stub
		return registrationDao.getByEmail(email);
	}

	@Override
	public List<Registration> getAll() {
		// TODO Auto-generated method stub
		return registrationDao.getAll();
	}

}
