package kr.co.codefarm.svcm.commons.service.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class EmailService {

	@Value("${mail.username}")
	private String MAIL_ID;

	
	@Autowired
	public JavaMailSender javaMailSender;

	public void sendMail(String sender, String[] receiver, String subject, String content) throws MessagingException {
		this.sendMailFilePath(null, sender, receiver, subject, content, null, null);
	}
	
	public void sendMail(String senderName, String sender, String[] receiver, String subject, String content) throws MessagingException {
		this.sendMailFilePath(senderName, sender, receiver, subject, content, null, null);
	}
	
	public void sendMail(String sender, String[] receiver, String subject, String content, String[] fileName, String[] filePath) throws MessagingException {
		this.sendMailFilePath(null, sender, receiver, subject, content, fileName, filePath);
	}
	
	public void sendMail(String senderName, String sender, String[] receiver, String subject, String content, String[] fileName, String[] filePath) throws MessagingException {
		this.sendMailFilePath(senderName, sender, receiver, subject, content, fileName, filePath);
	}
	
	public void sendMail(String sender, String[] receiver, String subject, String content, String[] fileName, File[] file) throws MessagingException {
		this.sendMailFile(null, sender, receiver, subject, content, fileName, file);
	}
	
	public void sendMail(String senderName, String sender, String[] receiver, String subject, String content, String[] fileName, File[] file) throws MessagingException {
		this.sendMailFile(senderName, sender, receiver, subject, content, fileName, file);
	}
	
	private void sendMailFile(String senderName, String sender, String[] receiver, String subject, String content, String[] fileName, File[] file) throws MessagingException {
		MimeMessage mimeMessage             = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		if (senderName != null && !"".equals(senderName)) {
			sender = senderName + " <" + sender + ">";
		}
		
		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setTo(receiver);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(content, true);
		
		if (file != null && fileName != null && file.length == fileName.length) {
			for (int idx = 0; idx < file.length; idx++) {
				mimeMessageHelper.addAttachment(fileName[idx], new FileSystemResource(file[idx]));
			}
		}
		
		javaMailSender.send(mimeMessage);
	}
	
	private void sendMailFilePath(String senderName, String sender, String[] receiver, String subject, String content, String[] fileName, String[] filePath) throws MessagingException {
		MimeMessage mimeMessage             = javaMailSender.createMimeMessage();
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true, "UTF-8");
		
		if (senderName != null && !"".equals(senderName)) {
			sender = "MEP" + " <" + MAIL_ID + ">";
		}

		mimeMessageHelper.setFrom(sender);
		mimeMessageHelper.setTo(receiver);
		mimeMessageHelper.setSubject(subject);
		mimeMessageHelper.setText(content, true);
		
		if (filePath != null && fileName != null && filePath.length == fileName.length) {
			for (int idx = 0; idx < filePath.length; idx++) {
				mimeMessageHelper.addAttachment(fileName[idx], new FileSystemResource(filePath[idx]));
			}
		}
		
		javaMailSender.send(mimeMessage);
	}

}
