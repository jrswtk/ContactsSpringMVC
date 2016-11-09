package com.user.reg.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.user.reg.domain.Profile;
import com.user.reg.domain.Registration;
import com.user.reg.domain.User;
import com.user.reg.form.AccountForm;
import com.user.reg.security.RoleEnum;
import com.user.reg.service.EmailService;
import com.user.reg.service.ProfileService;
import com.user.reg.service.RegistrationServiceImpl;
import com.user.reg.service.TokenService;
import com.user.reg.service.UserService;
import com.user.reg.utils.PasswordUtil;
import com.user.reg.validation.AccountFormValidator;

@Controller
@RequestMapping
public class RegistrationController {
	
	private Logger logger = Logger.getLogger(RegistrationController.class);

	@Autowired
	private ProfileService roleService;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationServiceImpl registrationService;
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private PasswordUtil passwordUtil;
	
	@Autowired
	private TokenService tokenService;
	
	@Autowired
	private AccountFormValidator accountFormValidator;

	
	@InitBinder("accountForm")
	public void initBinder(WebDataBinder webDataBinder) {
		webDataBinder.addValidators(accountFormValidator);
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String getRegisterPage(@ModelAttribute("accountForm") AccountForm accountForm) {
		return "registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registerAccount(@ModelAttribute("accountForm") @Valid AccountForm accountForm,
			BindingResult result, HttpServletRequest request) {
	
		logger.info(accountForm);
		
		if(result.hasErrors()) {
			return "registration";
		}
		
		User user = createUser(accountForm);
		userService.add(user);
		
		String token = tokenService.generateToken();
		Registration registration = new Registration(accountForm, token);
		registrationService.add(registration);

		String baseURL = getBaseURL(request);
		emailService.sendConfirmationMail(registration, baseURL);
		
		return "redirect:/?confirm";
	}
	
	@RequestMapping("/confirm")
	public String confirmUser(@RequestParam String email, @RequestParam String token) {
		Registration registration = registrationService.getByEmail(email);
		
		if(!registration.getToken().equals(token)) {
			return "redirect:/?unsuccess";
		}
		
		registrationService.delete(registration);
		User user = userService.getByEmail(email);
		user.setConfirmed(true);
		userService.update(user);
		
		return "redirect:/?success";
	}
	
	private String getBaseURL(HttpServletRequest request) {
		String requestURL = request.getRequestURL().toString();
		String baseURL = requestURL.substring(0, requestURL
				.indexOf(request.getContextPath()) 
					+ request.getContextPath().length());
				
		return baseURL;
	}
				
	private User createUser(AccountForm accountForm) {
		User user = new User();
		user.setFirstName(accountForm.getFirstName());
		user.setLastName(accountForm.getLastName());
		user.setEmail(accountForm.getEmail());
		user.setPass(getEncryptPassword(accountForm.getPass()));
		user.setProfile(getRole(accountForm.getRoleEnum()));
		
		return user;
	}
	
	private String getEncryptPassword(String password) {
		return passwordUtil.getEncryptPassword(password);
	}
	
	private Profile getRole(RoleEnum roleEnum) {
		return roleService.getProfileByRoleEnum(roleEnum);
	}
	
}
