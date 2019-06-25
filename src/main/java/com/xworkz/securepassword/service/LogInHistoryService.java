package com.xworkz.securepassword.service;

import com.xworkz.securepassword.dto.LogInHistoryDTO;

public interface LogInHistoryService {
	public LogInHistoryDTO getLogInDateService(String userName);

	public void saveLogInDateService(LogInHistoryDTO historyDTO);

}
