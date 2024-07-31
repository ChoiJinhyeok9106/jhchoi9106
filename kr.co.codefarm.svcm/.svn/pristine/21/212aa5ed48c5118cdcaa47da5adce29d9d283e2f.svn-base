package kr.co.codefarm.svcm.commons.service.mail;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonMailService {

	@Autowired
	private EmailService emailService;
	
	public void send(String to[], String from, String subject, String content) throws MessagingException {
		emailService.sendMail(from, to, subject, content);
	}
}
