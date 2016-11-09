package com.user.reg.validation;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.user.reg.form.AccountForm;
import com.user.reg.service.RegistrationService;
import com.user.reg.service.UserService;

@Component
public class AccountFormValidator implements Validator {

	Logger logger = Logger.getLogger(AccountFormValidator.class);
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private RegistrationService registrationService;
	
	@Override
	public boolean supports(Class<?> cls) {
		// TODO Auto-generated method stub
		return AccountForm.class.isAssignableFrom(cls);
	}
	

	@Override
	public void validate(Object object, Errors errors) {
		// TODO Auto-generated method stub
		AccountForm accountForm = (AccountForm) object;
		
		if(!validatePass(accountForm.getPass(), accountForm.getRepeatPass())) {
			errors.rejectValue("pass", "accountForm.passnotidentical.error");
		}
		
		if(validateEmail(accountForm.getEmail())) {
			errors.rejectValue("email", "accountForm.emailexists.error");
		}
		
		if(validateRegistration(accountForm.getEmail())) {
			errors.rejectValue("email", "accountForm.notacitved.error");
		}
	}
	
	private boolean validatePass(String pass, String repeatPass) {
		return pass.equals(repeatPass);
	}
	
	private boolean validateEmail(String email) {
		return userService.getByEmail(email) != null;
	}

	private boolean validateRegistration(String email) {
		return registrationService.getByEmail(email) != null;
	}	
	
}
