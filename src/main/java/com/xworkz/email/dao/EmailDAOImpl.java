package com.xworkz.email.dao;

import java.io.Serializable;
import java.util.Objects;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.email.entity.EmailEntity;
@Repository
public class EmailDAOImpl implements EmailDAO {

	private static Logger logger = Logger.getLogger(EmailDAOImpl.class);
	
	@Autowired
	private SessionFactory factory;

	public void setFactory(SessionFactory factory) {
		this.factory = factory;
	}
	public EmailDAOImpl() {
		logger.info("inside getMessage()...Invoked :" + this.getClass().getSimpleName());
	}


	public Serializable save(EmailEntity entity) {
		
		logger.info("inside getMessage()...Invoking save method from DAOImpl");
		Session session = null;
		try {

			session = factory.openSession();

			session.beginTransaction();

			session.save(entity);

			session.getTransaction().commit();
			logger.info("inside getMessage()...Data saved in DataBase succesfully.");
			
		} catch (Exception e) {
			logger.info("INFO:"+e.getMessage());
		} finally {
			if (Objects.nonNull(session)) {
				session.close();
				logger.info("inside getMessage()...Resources closed.");
			}

		}
		return entity;
	}

}