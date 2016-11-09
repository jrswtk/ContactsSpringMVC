package com.user.reg.dao;

import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;

public interface ProfileDao extends EntityDao<Profile> {
	
	Profile getProfileByRoleEnum(RoleEnum roleEnum);
	
}
