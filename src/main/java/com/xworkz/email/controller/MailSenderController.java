package com.xworkz.email.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.xworkz.email.exceptions.ControllerException;
import com.xworkz.email.exceptions.ServiceException;
import com.xworkz.email.service.MailSenderService;

@Controller
@RequestMapping("/")
public class MailSenderController extends  ControllerException {
	private static final Logger logger = Logger.getLogger(MailSenderController.class);

	public MailSenderController() {
		logger.info(this.getClass().getSimpleName() + "object created");

	}

	@Autowired
	private MailSenderService mailSenderService;
	
	@RequestMapping("/sendMsg.do")
	public ModelAndView sendMail(String emailId,String subject, String body) {
		try {
		logger.info("------inside sendMail() of class:" +this.getClass().getSimpleName()+"--------");
		logger.info("Email Id :" +emailId);
		logger.info("Subject: " + subject);
		logger.info("Body: " + body);
		ModelAndView mv=new  ModelAndView("home.jsp");
		try {
			mailSenderService.sendMailToUser(emailId,subject,body);
		} catch (ServiceException e) {
			logger.error(e.getMessage(), e);
		}
		mv.addObject("key", "Mail Sent Succesfully");

		return mv;
	} catch (Exception c) {
			try {
				throw new ControllerException(c.getMessage());
			} catch (ControllerException e) {
					logger.error("Exception:"+e.getMessage(),e);
				
			}
	}
	return null;

}
}