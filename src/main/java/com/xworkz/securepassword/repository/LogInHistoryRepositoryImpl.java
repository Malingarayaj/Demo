package com.xworkz.securepassword.repository;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.xworkz.securepassword.entity.LogInHistoryEntity;

@Repository
public class LogInHistoryRepositoryImpl implements LogInHistoryRepository {
	private static final Logger logger = LoggerFactory.getLogger(LogInHistoryRepositoryImpl.class);

	@Autowired
	private SessionFactory factory;

	public LogInHistoryRepositoryImpl() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public void saveLogInDateRepo(LogInHistoryEntity historyEntity) {
		logger.debug("invoking saveLogInDateRepo with args\t" + historyEntity);
		try (Session session = factory.openSession()) {
			Transaction transaction = session.beginTransaction();
			session.save(historyEntity);
			transaction.commit();

		} catch (Exception e) {
			logger.debug("Exception occur in setLogInDate in LogInHistoryRepositoryImpl\t" + e.getMessage());
		}

	}

	@Override
	public LogInHistoryEntity getLogInDateRepo(String userName) {
		logger.debug("invoking getLogInDateRepowith args\t" + userName);
		try (Session session = factory.openSession()) {
			Query query = session.getNamedQuery("getLogInHistory");
			/*
			 * String hql="from LogInHistoryEntity where lId=(" +"select max(lId)"
			 * +"from LogInHistoryEntity" +"where userName=:userName"; Query
			 * query=session.createQuery(hql);
			 */
			query.setParameter("userName", userName);
			LogInHistoryEntity historyEntity = (LogInHistoryEntity) query.uniqueResult();
			return historyEntity;

		} catch (Exception e) {
			logger.debug("Exception occur in getLogInDateRepo in LogInHistoryRepositoryImpl\t" + e.getMessage());
		}
		return null;

	}

}
