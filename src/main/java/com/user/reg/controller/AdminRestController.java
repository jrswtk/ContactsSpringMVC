package com.user.reg.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.user.reg.domain.User;
import com.user.reg.domain.UserJson;
import com.user.reg.service.UserService;
import com.user.reg.utils.JsonConverterUtil;

@RestController
@RequestMapping("/rest")
public class AdminRestController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JsonConverterUtil<User, UserJson> userConverter;
	
	@RequestMapping("/admin/delete/{userId}")
	public void delteUser(@PathVariable("userId") long userId) {
		User user = userService.get(userId);
		userService.delete(user);
	}
	
	@RequestMapping("/admin/getall")
	public List<UserJson> getUsers() {
		List<UserJson> users = null;
		
		try {
			users = userConverter
					.convertToJsons(
						userService.getAll(), 
						UserJson.class);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return users;
	}
	
}
