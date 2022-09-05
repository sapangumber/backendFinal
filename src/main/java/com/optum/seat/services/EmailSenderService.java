package com.optum.seat.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailSenderService {
	
	private JavaMailSender mailSender;

    

	public EmailSenderService(JavaMailSender mailSender) {

	        

	        this.mailSender = mailSender;

	    }



	public void sendEmail(String toEmail, String subject, String message) {



	    var mailMessage = new SimpleMailMessage();



	    mailMessage.setTo(toEmail);

	    mailMessage.setSubject(subject);

	    mailMessage.setText(message);



	    mailMessage.setFrom("office@optum.com");

	    mailSender.send(mailMessage);



	}

}
