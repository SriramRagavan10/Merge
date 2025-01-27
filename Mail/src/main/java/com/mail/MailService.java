package com.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class MailService {

	@Autowired
	private JavaMailSender emailSender;

	@Scheduled(fixedRate = 10000) // Run every minute
	public void sendEmail() {
		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo("ramsri078@gmail.com");
		message.setSubject("Form the New World");
		message.setText("This is a test email from Spring Boot.");

		emailSender.send(message);
	}

}
