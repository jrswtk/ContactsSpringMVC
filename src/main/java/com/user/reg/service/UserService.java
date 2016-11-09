package com.user.reg.service;

import java.util.List;

import com.user.reg.domain.Profile;
import com.user.reg.domain.User;

public interface UserService extends EntityService<User>, EntityEmailService<User> {

	List<User> getUsersByRole(Profile role);
	
}
