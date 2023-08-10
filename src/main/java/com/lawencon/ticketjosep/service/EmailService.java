package com.lawencon.ticketjosep.service;

public interface EmailService {
	void sendEmail(String subject, String body, String toEmail);
}
