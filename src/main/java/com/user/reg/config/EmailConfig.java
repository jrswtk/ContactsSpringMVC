package com.user.reg.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {

    private final String USERNAME = "aplikacjakontakty@gmail.com";
    private final String PASSWORD = "QwErTy1234";
    private final String TITLE = "Rejestracja uzytkownika w aplikacji Kontakty.";

    @Bean
    public JavaMailSender mailSender() {
        JavaMailSenderImpl sender = new JavaMailSenderImpl();
        sender.setHost("smtp.gmail.com");
        sender.setPort(587);
        sender.setProtocol("smtp");
        sender.setUsername(USERNAME);
        sender.setPassword(PASSWORD);
        Properties p = new Properties();
        p.setProperty("mail.smtp.auth", "true");
        p.setProperty("mail.smtp.starttls.enable", "true");
        p.setProperty("mail.smtp.quitwait", "false");
        sender.setJavaMailProperties(p);
        return sender;
    }

    @Bean(name = "registerMessage")
    public SimpleMailMessage registerMessage(){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setSubject(TITLE);
        return message;
    }
	
}
