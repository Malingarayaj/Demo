package com.xworkz.securepassword.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.xworkz.securepassword.repository.DisableEnableRepository;

public class DisableEnableServiceImpl implements DisableEnableService {
	private static final Logger logger = LoggerFactory.getLogger(DisableEnableServiceImpl.class);
	@Autowired
	private DisableEnableRepository disableEnableRepo;

	public DisableEnableServiceImpl() {
		logger.info("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public void disableUserAccountService(String userName) {
		logger.debug("invoking the disableUserAccountService");
		try {
			disableEnableRepo.disableUserAccountRepo(userName);

		} catch (Exception e) {
			logger.debug("exception occur in disableUserAccountService in DisableEnableLogOutServiceImpl\t" + e.getMessage());
		}

	}
	
	@Override
	public void enableUserAccountService(String userName) {
		logger.debug("invoking the enableUserAccountService");
		try {
			

		} catch (Exception e) {
			logger.debug("exception occur in enableUserAccountService in DisableEnableLogOutServiceImpl\t" + e.getMessage());
		}

	}
}
