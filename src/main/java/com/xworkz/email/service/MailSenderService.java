package com.xworkz.email.service;

import com.xworkz.email.exceptions.ServiceException;

public interface MailSenderService {
	public boolean sendMailToUser(String emailId, String subject, String body) throws ServiceException;

}
