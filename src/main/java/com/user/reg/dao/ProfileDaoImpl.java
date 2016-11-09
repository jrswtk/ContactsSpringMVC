package com.user.reg.dao;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;
import com.user.reg.utils.SessionUtil;

@Repository
public class ProfileDaoImpl implements ProfileDao {
	
	@Autowired
	private SessionUtil sessionUtil;
	
	@Override
	public long add(Profile role) {
		// TODO Auto-generated method stub
		return (long) getSession().save(role);
	}

	@Override
	public void update(Profile role) {
		// TODO Auto-generated method stub
		getSession().update(role);
	}

	@Override
	public void delete(Profile role) {
		// TODO Auto-generated method stub
		getSession().delete(role);
	}

	@Override
	public Profile get(long profile_id) {
		// TODO Auto-generated method stub
		return (Profile) getSession().get(Profile.class, profile_id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Profile> getAll() {
		// TODO Auto-generated method stub
		String query = "FROM Profile p ORDER BY p.profile_id";
		return (List<Profile>) getSession().createQuery(query).list();
	}
	
	@Override
	public Profile getProfileByRoleEnum(RoleEnum roleEnum) {
		// TODO Auto-generated method stub
		String query = "FROM Profile p WHERE p.role = :role";
		Query createQuery = getSession().createQuery(query);
		createQuery.setParameter("role", roleEnum);
		return (Profile) createQuery.uniqueResult();
	}
	
	private Session getSession() {
		return sessionUtil.getSession();
	}

}
