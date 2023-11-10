package org.jsp.busreservationapiapp.service;

import org.jsp.busreservationapiapp.dto.EmailConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class ReservationApiEmailService {
	@Autowired
	private JavaMailSender mailSender;
	
	@PostMapping(value="/send")
	public String sendEmail(EmailConfiguration configuration) {
		MimeMessage message=mailSender.createMimeMessage();
		MimeMessageHelper helper=new MimeMessageHelper(message);
		try {
			helper.setTo(configuration.getTo());
			helper.setText(configuration.getText());
			helper.setSubject(configuration.getSubject());
			mailSender.send(message);
			return "Mail Send";
		}
		catch(MessagingException e) {
			e.printStackTrace();	
			return "Unable to send Email";

			}
	}

}
