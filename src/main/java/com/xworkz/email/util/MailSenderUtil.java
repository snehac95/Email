package com.xworkz.email.util;

import com.xworkz.email.exceptions.UtilException;

public interface MailSenderUtil {
	public boolean sendMail(String toEmailId, String subject, String body) throws UtilException;

}
