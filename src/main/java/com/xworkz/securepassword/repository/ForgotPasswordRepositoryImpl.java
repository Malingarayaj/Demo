package com.xworkz.securepassword.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.securepassword.entity.UserEntity;

@SuppressWarnings("deprecation")
@Repository
public class ForgotPasswordRepositoryImpl implements ForgotPasswordRepository {

	private static Logger logger = LoggerFactory.getLogger(ForgotPasswordRepositoryImpl.class);

	@Autowired
	private SessionFactory factory;

	@Override
	public UserEntity checkUserByNameEmailMobileNo(String userName, String email, long mobileNo) {
		logger.info("inside checkUserByNameEmailMobileNo method");
		Session session = null;
		try {
			session = factory.openSession();
			Query qry = session.getNamedQuery("fetchByNameMailMobileNo");
			qry.setParameter("username", userName);
			qry.setParameter("email", email);
			qry.setParameter("mobileNo", mobileNo);
			UserEntity entity = (UserEntity) qry.uniqueResult();
			return entity;

		} catch (Exception e) {
			logger.debug("Exception occur in forgotPasswordRepository\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public void setFalseFirstLog(UserEntity entity) {
		logger.info("inside forgot password");
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
			logger.debug("Exception occur in forgotPasswordRepository\t" + e.getMessage());
		} finally {
			session.close();
		}

	}

	@Override
	public boolean resetPasswordUpdateRepository(UserEntity entity) {
		logger.info("inside resetPasswordUpdateRepository method");
		Session session = null;
		Transaction tx = null;
		try {
			session = factory.openSession();
			tx = session.beginTransaction();
			session.saveOrUpdate(entity);
			tx.commit();
			return true;
		} catch (Exception e) {
			tx.rollback();
		}
		return false;
	}

	@Override
	public UserEntity findkUserByName(String userName) {
		logger.info("inside findkUserByName method");
		Session session = null;
		try {
			session = factory.openSession();
			Query qry = session.getNamedQuery("fetchUserByName");
			qry.setParameter("username", userName);
			UserEntity entity = (UserEntity) qry.uniqueResult();
			return entity;

		} catch (Exception e) {
			logger.debug("Exception occur in forgotPasswordRepository\t" + e.getMessage());
		}
		return null;
	}

}
