package com.aobfilho.cursomc.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;

import com.aobfilho.cursomc.service.DBService;
import com.aobfilho.cursomc.service.email.EmailService;
import com.aobfilho.cursomc.service.email.SmtpEmailService;

@Configuration
@Profile("dev")
@PropertySource({"file://${HOME}/cursomc-mail.properties"})
public class DevConfig {

	@Autowired
	private DBService dbService;
	
	@Value("${spring.jpa.hibernate.ddl-auto}")
	private String strategy;
	
	@Autowired
	private Environment env;
	
	@Bean
	public boolean instantiateDatabase() throws ParseException {
		if (!"create".equals(strategy)) {
			return false;
		}
		dbService.instantiateTestDatabase();  
		return true;
	}
	
	@Bean
	public EmailService emailService() {
		System.setProperty("spring.mail.username", env.getProperty("username"));
		System.setProperty("spring.mail.password", env.getProperty("password"));
		return new SmtpEmailService();
	}
}
