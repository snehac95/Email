package com.xworkz.email.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.email.controller.MailSenderController;
import com.xworkz.email.dao.EmailDAO;
import com.xworkz.email.entity.EmailEntity;
import com.xworkz.email.exceptions.ServiceException;
import com.xworkz.email.exceptions.UtilException;
import com.xworkz.email.util.MailSenderUtil;
@Service
public class MailSenderServiceImpl  implements  MailSenderService {
	
	private static  final Logger logger = Logger.getLogger(MailSenderServiceImpl.class);
	
	public MailSenderServiceImpl() {
		logger.info(this.getClass().getSimpleName() +"object created");
	}
	@Autowired
	private MailSenderUtil  mailSenderUtil;
	@Autowired
	private EmailDAO dao; 


	public boolean sendMailToUser(String emailId, String subject, String body) throws ServiceException {
		logger.info("sendMailToUser() Invoked...");
		boolean valid = false;

		if (emailId != null && emailId.length() > 10 && emailId.contains("@")) {
			logger.info("Entered Email Id length is valid");
			valid = true;
			if (valid && subject != null && subject.length() >= 4) {
				logger.info("Entered subject is length is valid");
				valid = true;
				if (valid && body != null && body.length() >= 5) {
					logger.info("Entered body length is valid");
					valid = true;
				} else {
					if (body == null && body.length() < 4) {
						logger.info("Not Valid:Body length is less than 4");
					}
				}
			} else {
				if (subject == null && subject.length() < 4) {
					logger.info("Not Valid:Subject length is less than 4");
				}
			}
		} else {
			if (emailId == null && emailId.length() < 10) {
				logger.info("Not Valid:Email Id length is less than 4");
			}
		}

		if (valid) {
			logger.info("inside getMessage()...Data is valid,Can Save in Database");

			EmailEntity emailEntity = new EmailEntity();
			emailEntity.setEmailId(emailId);
			emailEntity.setSubject(subject);
			emailEntity.setBody(body);
			dao.save(emailEntity);

			try {
				valid = mailSenderUtil.sendMail(emailId, body, subject);
			} catch (UtilException e) {
				logger.info("Exception: " + e.getMessage());
			}

		} else {
			logger.info("inside getMessage()...Data is not saved in Database");
			valid = false;
		}

		return valid;

	}

}
	

