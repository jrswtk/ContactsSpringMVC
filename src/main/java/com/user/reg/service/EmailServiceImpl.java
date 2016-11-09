package com.user.reg.service;

import java.text.MessageFormat;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.user.reg.domain.Registration;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired
    private JavaMailSender mailSender;
    
    @Resource(name = "registerMessage")
    private SimpleMailMessage registerMessage;
    private final String BODY_TEMPLATE = "Aktywacja konta:\r\n\r\n{0}/confirm?email={1}&token={2}";

    @Async
    public void sendConfirmationMail(Registration registration, String baseUrl) {
        SimpleMailMessage message = createEmailMessage(registration, baseUrl);
        mailSender.send(message);
    }

    private final SimpleMailMessage createEmailMessage(Registration registration, String baseUrl) {
        registerMessage.setTo(registration.getEmail());
        registerMessage.setText(MessageFormat.format(BODY_TEMPLATE, 
        		baseUrl, 
        		registration.getEmail(), 
        		registration.getToken()));
        return registerMessage;
    }
}
