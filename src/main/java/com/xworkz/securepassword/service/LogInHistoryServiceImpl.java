package com.xworkz.securepassword.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.dto.LogInHistoryDTO;
import com.xworkz.securepassword.entity.LogInHistoryEntity;
import com.xworkz.securepassword.repository.LogInHistoryRepository;

@Service
public class LogInHistoryServiceImpl implements LogInHistoryService {

	private static final Logger logger = LoggerFactory.getLogger(LogInHistoryServiceImpl.class);
	@Autowired
	private LogInHistoryRepository repository;

	public LogInHistoryServiceImpl() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public void saveLogInDateService(LogInHistoryDTO historyDTO) {
		logger.debug("invoking saveLogInDateService with args\t" + historyDTO);
		try {
			LogInHistoryEntity historyEntity = new LogInHistoryEntity();
			historyEntity.setUserName(historyDTO.getUserName());
			historyEntity.setLastLogIn(historyDTO.getLastLogIn());
			repository.saveLogInDateRepo(historyEntity);

		} catch (Exception e) {
			logger.debug("Exception occur in saveLogInDateService in LogInHistoryServiceImpl" + e.getMessage());
		}

	}

	@Override
	public LogInHistoryDTO getLogInDateService(String userName) {
		logger.debug("invoking getLogInDateRepo with args\t" + userName);
		try {
			LogInHistoryEntity historyEntity=repository.getLogInDateRepo(userName);
			LogInHistoryDTO dto=new LogInHistoryDTO();
			dto.setLastLogIn(historyEntity.getLastLogIn());
			dto.setUserName(historyEntity.getUserName());
			return dto;
			
		} catch (Exception e) {
			logger.debug("Exception occur in saveLogInDateService in LogInHistoryServiceImpl" + e.getMessage());
		}
		return null;
	}
}
