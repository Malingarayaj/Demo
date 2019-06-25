package com.xworkz.securepassword.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.securepassword.dto.LogInDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.LogInRepoException;

@Repository
public class LogInRepositoryImpl implements LogInRepository {
	private static final Logger logger = LoggerFactory.getLogger(LogInRepositoryImpl.class);

	@Autowired
	private SessionFactory factory;

	public LogInRepositoryImpl() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public UserEntity logIn(LogInDTO dto) throws LogInRepoException {
		logger.info("invoked login method in rpository with args\t" + dto);
		try (Session session = factory.openSession()) {
			logger.info("starting login \t" + dto);
			Query query=session.getNamedQuery("logInBYNamePwd");
			query.setParameter("usrname", dto.getUserName());
			UserEntity entityFromDb = (UserEntity) query.uniqueResult();
			return entityFromDb;



		}catch (Exception e) {
			logger.info("Exception occured in logIn controller\t" + e.getMessage());
			throw new LogInRepoException("Exception thrown in logIn controller\t" + e.getMessage());		}
		
	}

}
