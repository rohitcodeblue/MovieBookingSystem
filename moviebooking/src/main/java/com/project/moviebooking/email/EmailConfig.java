package com.project.moviebooking.email;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class EmailConfig {
	
	@Value("${spring.mail.username}")
	private String emailId;
	@Value("${spring.mail.password}")
	private String password;
	@Value("${spring.mail.host}")
	private String host;
	@Value("${spring.mail.port}")
	private int port;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost("your-smtp-server.com");
        mailSender.setPort(587);
        mailSender.setUsername("your-email@example.com");
        mailSender.setPassword("your-email-password");

        return mailSender;
    }
}

