package com.user.reg.service;

import com.user.reg.domain.Profile;
import com.user.reg.security.RoleEnum;

public interface ProfileService extends EntityService<Profile> {

	Profile getProfileByRoleEnum(RoleEnum roleEnum);
	
}
