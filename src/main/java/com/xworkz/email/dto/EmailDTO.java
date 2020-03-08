package com.xworkz.email.dto;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import lombok.Data;
@Component
@Data
public class EmailDTO {
	private static Logger logger = Logger.getLogger(EmailDTO.class);
	
	private String emailId;
	private String subject;
	private String body;
	
	public EmailDTO() {
		logger.info("inside getMessage()...Invoked :" + this.getClass().getSimpleName());
	}

}

