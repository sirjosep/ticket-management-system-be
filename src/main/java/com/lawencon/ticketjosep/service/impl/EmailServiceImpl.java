package com.lawencon.ticketjosep.service.impl;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.lawencon.ticketjosep.service.EmailService;

@Service
public class EmailServiceImpl implements EmailService{
    private JavaMailSender javaMailSender;

	EmailServiceImpl(JavaMailSender javaMailSender) {
		this.javaMailSender = javaMailSender;
	}
	
	@Override
	public void sendEmail(String subject, String body, String toEmail) {
        final SimpleMailMessage msg = new SimpleMailMessage();
        msg.setTo(toEmail);
        msg.setSubject(subject);
        msg.setText(body);

        javaMailSender.send(msg);
	}
}
