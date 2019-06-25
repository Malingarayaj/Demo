package com.xworkz.securepassword.repository;

import com.xworkz.securepassword.entity.LogInHistoryEntity;

public interface LogInHistoryRepository {
	public void saveLogInDateRepo(LogInHistoryEntity historyEntity);
	public LogInHistoryEntity getLogInDateRepo(String userName);
}
