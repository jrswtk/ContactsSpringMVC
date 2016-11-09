package com.user.reg.dao;

import java.util.List;

import com.user.reg.domain.Profile;
import com.user.reg.domain.User;

public interface UserDao extends EntityDao<User>, EntityEmailDao<User> {

	List<User> getUsersByRole(Profile role);
	
}
