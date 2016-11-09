package com.user.reg.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.user.reg.domain.User;
import com.user.reg.service.UserService;

@Service("userDetailsService")
public class UserSecurity implements UserDetailsService {

	@Autowired
	private UserService userService;
			
	@Override
	public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		
		User user = userService.getByEmail(userEmail);
		
		if(!user.isConfirmed()) {
			return null;
		}
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		authorities.add(new SimpleGrantedAuthority(user.getProfile().getRole().toString()));
		
		org.springframework.security.core.userdetails.User userDetails = 
				new org.springframework.security.core.userdetails.User(
						user.getEmail(), 
						user.getPass(), 
						true, 
						true, 
						true, 
						true, 
						authorities);
		
		return userDetails;
	}
	
}
