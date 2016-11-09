package com.user.reg.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.user.reg.dao.ProfileDao;
import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;

@Service
@Transactional
public class ProfileServiceImpl implements ProfileService {

	@Autowired
	private ProfileDao roleDao;
	
	@Override
	public long add(Profile t) {
		// TODO Auto-generated method stub
		return (long) roleDao.add(t);
	}

	@Override
	public void update(Profile t) {
		// TODO Auto-generated method stub
		roleDao.update(t);
	}

	@Override
	public void delete(Profile t) {
		// TODO Auto-generated method stub
		roleDao.delete(t);
	}

	@Override
	public Profile get(long id) {
		// TODO Auto-generated method stub
		return roleDao.get(id);
	}

	@Override
	public List<Profile> getAll() {
		// TODO Auto-generated method stub
		return roleDao.getAll();
	}

	@Override
	public Profile getProfileByRoleEnum(RoleEnum roleEnum) {
		// TODO Auto-generated method stub
		return roleDao.getProfileByRoleEnum(roleEnum);
	}

}
