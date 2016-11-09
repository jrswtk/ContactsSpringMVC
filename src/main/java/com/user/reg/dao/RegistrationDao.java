package com.user.reg.dao;

import com.user.reg.domain.Registration;

public interface RegistrationDao extends EntityDao<Registration>, EntityEmailDao<Registration> {
	
	Registration getByEmail(String email);

}
