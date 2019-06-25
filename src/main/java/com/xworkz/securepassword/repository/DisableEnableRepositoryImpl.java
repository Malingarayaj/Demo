package com.xworkz.securepassword.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

public class DisableEnableRepositoryImpl implements DisableEnableRepository {
	private static final Logger logger = LoggerFactory.getLogger(DisableEnableRepositoryImpl.class);
	@Autowired
	private SessionFactory factory;

	public DisableEnableRepositoryImpl() {
		logger.info("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public void disableUserAccountRepo(String userName) {
		logger.debug("invoking the disableUserAccountRepo");
		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Query query=session.getNamedQuery("disableAccount");
			query.setParameter("username", userName);
			query.executeUpdate();
			transaction.commit();

		} catch (Exception e) {
			logger.debug("exception occur in disableUserAccountRepo in DisableOrLogOutRepoImpl\t" + e.getMessage());
		}
		
	}

	@Override
	public void enableUserAccountRepo(String userName) {
		logger.debug("invoking the enableUserAccountRepo");
		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			Query query=session.getNamedQuery("enableAccount");
			query.setParameter("username", userName);
			query.executeUpdate();
			transaction.commit();
		} catch (Exception e) {
			logger.debug("exception occur in enableUserAccountRepo in DisableOrLogOutRepoImpl\t" + e.getMessage());
		}

	}
}
