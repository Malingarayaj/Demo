package com.xworkz.securepassword.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import com.xworkz.securepassword.dto.ChangePasswordDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.ChangePasswordRepoException;
import com.xworkz.securepassword.service.ChangePasswordServiceImpl;

@Repository
public class ChangePasswordRepositoryImpl implements ChangePasswordRepository {
	private static Logger logger = LoggerFactory.getLogger(ChangePasswordRepositoryImpl.class);

	private SessionFactory factory;

	@SuppressWarnings("deprecation")
	@Override
	public UserEntity findUserByNamePwd(ChangePasswordDTO dto) throws ChangePasswordRepoException {
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("findUserByNamePwd");
			query.setParameter("newName", dto.getUserName());
			UserEntity entity = (UserEntity) query.uniqueResult();
			logger.debug("checking password................");
			return entity;

		} catch (Exception e) {
			logger.debug("Exception occured in ChangePasswordRepositoryImpl\t" + e.getMessage());
			throw new ChangePasswordRepoException(
					"Exception thrown in ChangePasswordRepositoryImpl\t" + e.getMessage());
		}
	}

	@Override
	public boolean changePassword(UserEntity entity) throws ChangePasswordRepoException {
		logger.debug("invoked changePassword in repo\t" + entity);
		Transaction tx = null;
		try (Session session = factory.openSession()) {
			tx = session.beginTransaction();
			session.update(entity);
			tx.commit();
			logger.debug("password update successfully");
			return true;
		} catch (Exception e) {
			tx.rollback();
			logger.debug("Exception occured in ChangePasswordRepositoryImpl\t" + e.getMessage());
			throw new ChangePasswordRepoException(
					"Exception thrown in ChangePasswordRepositoryImpl\t" + e.getMessage());

		}

	}

	@Override
	public UserEntity checkUserByUserName(String userName) throws ChangePasswordRepoException {
		logger.debug("entering to checkUserByUserName in repository\t" + userName);
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("fetchUserByName");
			query.setParameter("username", userName);
			UserEntity userFromDb = (UserEntity) query.uniqueResult();
			logger.debug("data fetching from db\t" + userFromDb);
			return userFromDb;
		} catch (Exception e) {
			logger.debug("Exception in Repository\t" + e.getMessage());
			throw new ChangePasswordRepoException("Exception thrown in changepassword Repository\t" + e.getMessage());

		}

	}

}
