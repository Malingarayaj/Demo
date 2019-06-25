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

import com.xworkz.securepassword.dto.UserCredentialDTO;
import com.xworkz.securepassword.entity.UserCredentialEntity;

@Repository
public class UserCredentialRepositoryImpl implements UserCredentialRepository {
	private static final Logger logger = LoggerFactory.getLogger(UserCredentialRepositoryImpl.class);

	@Autowired
	private SessionFactory factory;

	public UserCredentialRepositoryImpl() {
		logger.info("created.....\t" + this.getClass().getSimpleName());
	}
@Override
	public UserCredentialEntity addCredential(UserCredentialEntity entity) {
		logger.debug("invoking addCredential in UserCredentialRepositoryImpl\t" + entity);
		Transaction transaction = null;
		try (Session session = factory.openSession()) {
			transaction = session.beginTransaction();
			session.save(entity);
			transaction.commit();
			return entity;

		} catch (Exception e) {
			logger.debug("Exception is occur in addCredential in UserCredentialRepositoryImpl\t" + e.getMessage());
		}
		return null;
	}
@Override
	public UserCredentialEntity checkUserCredentials(UserCredentialDTO dto) {
		logger.debug("invoking checkUserCredentials in UserCredentialRepositoryImpl\t" + dto);
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("checkUser");
			query.setParameter("aName", dto.getAppName());
			query.setParameter("user", dto.getLoggedUser());
			UserCredentialEntity entityFromDb = (UserCredentialEntity) query.uniqueResult();
			logger.debug("data from db\t" + entityFromDb);
			return entityFromDb;
		} catch (Exception e) {
			logger.debug(
					"Exception is occur in checkUserCredentials in UserCredentialRepositoryImpl\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public List<UserCredentialEntity> getAllCredential(String username) {
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("getAllCredentials");
			query.setParameter("userName", username);
			List<UserCredentialEntity> credentialEntity = (List<UserCredentialEntity>) query.list();
			return credentialEntity;

		} catch (Exception e) {
			logger.debug(
					"Exception is occur in checkUserCredentials in UserCredentialRepositoryImpl\t" + e.getMessage());

		}
		return null;
	}
	@Override

	public UserCredentialEntity editUserCredintialRepo(String appName, String userNamee) {
		logger.debug("invoking editUserCredintialRepo\t" + appName + userNamee);
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("checkUser");
			query.setParameter("aName", appName);
			query.setParameter("user", userNamee);
			UserCredentialEntity credentialEntity = (UserCredentialEntity) query.uniqueResult();
			return credentialEntity;
		} catch (Exception e) {
			logger.debug("Exception occur in editUserCredintialRepo\t" + e.getMessage());
		}
		return null;
	}

	@Override
	public UserCredentialEntity updateUserCredintialRepo(UserCredentialEntity entity) {
		logger.debug("invoking updateUserCredintialRepo\t" + entity);
		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.saveOrUpdate(entity);
			transaction.commit();
			return entity;

		} catch (Exception e) {
			logger.debug("Exception occur in  updateUserCredintialRepo" + e.getMessage());
		}
		return null;

	}

	@Override
	public void deleteCredintialRepo(String appName, String userName) {
		logger.debug("invoking updateUserCredintialRepo\t" + appName + userName);
		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Query query = session.createNamedQuery("deleteCredentials");
			query.setParameter("aName", appName);
			query.setParameter("userName", userName);
			query.executeUpdate();
			transaction.commit();
			return;

		} catch (Exception e) {
			logger.debug("Exception occur in  updateUserCredintialRepo" + e.getMessage());
		}
	}
}
