package com.xworkz.email.util;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Controller;

import com.xworkz.email.controller.MailSenderController;
import com.xworkz.email.exceptions.UtilException;
@Controller
public class MailSenderUtilImpl implements MailSenderUtil {
	@Autowired
	private MailSender mailSender;
	
	//@Autowired
	//private EmailService service;
	private static final Logger logger = Logger.getLogger(MailSenderUtilImpl.class);

	public MailSenderUtilImpl() {
		logger.info(this.getClass().getSimpleName() + "object created");
	}

	public boolean sendMail(String toEmailId, String subject, String body) throws UtilException {
		logger.info("---------inside sendMail" + this.getClass().getSimpleName() + "object created");
		logger.info("invoked sendMail()...");

		boolean status = false;

		SimpleMailMessage message = new SimpleMailMessage();
		message.setTo(toEmailId);
		message.setSubject(subject);
		message.setText(body);
		mailSender.send(message);

		return true;
	}

}