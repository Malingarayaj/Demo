package com.xworkz.securepassword.repository;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.RepositoryException;

@Repository
public class SignUpRepositoryImpl implements SignUpRepository {
	@Autowired
	private SessionFactory factory;
	private static Logger logger = LoggerFactory.getLogger(SignUpRepositoryImpl.class);

	public SignUpRepositoryImpl() {
		logger.debug("object created.....\t" + this.getClass().getSimpleName());
	}

	public String saveSignUp(UserEntity entity) throws RepositoryException {
		logger.debug("invoked signUp\t" + entity);
		Transaction tx = null;
		Session session = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			String primarykey = (String) session.save(entity);
			tx.commit();
			logger.debug("data was successfully saved\t" + entity);
			return primarykey;

		} catch (Exception e) {
			logger.debug("Exception in Repository\t" + e.getMessage());
			throw new RepositoryException("Exception thrown in SignUpRepository\t" + e.getMessage());

		} finally {
			session.close();
		}

	}

	@SuppressWarnings("deprecation")
	public List<UserEntity> findUserByNameEmailMno(UserEntity entity) throws RepositoryException {
		logger.debug("checkMail method invoked\t" + entity);
		Session session = null;
		try {
			session = factory.openSession();
			Query qry = session.getNamedQuery("fetchByNameMailMno");
			qry.setParameter("sEmail", entity.getEmail());
			qry.setParameter("sMobileNo", entity.getMobileNo());
			qry.setParameter("sName", entity.getUserName());
			List<UserEntity> entityFromDb = (List<UserEntity>) qry.list();
			logger.debug("entityFromDb:{}", entityFromDb);
			return entityFromDb;

		} catch (Exception e) {
			logger.debug("Exception occure in findUserByNameEmailMno  in Repository\t" + e.getMessage());
			throw new RepositoryException("Exception thrown in SignUpRepository\t" + e.getMessage());

		}
		finally
		{
			session.close();
		}

	}

}
