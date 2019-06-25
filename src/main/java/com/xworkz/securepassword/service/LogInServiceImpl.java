package com.xworkz.securepassword.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.dto.LogInDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.LogInServiceException;
import com.xworkz.securepassword.repository.LogInRepository;
import com.xworkz.securepassword.util.EcryptionDecryption;

@Service
public class LogInServiceImpl implements LogInService {
	private static final Logger logger = LoggerFactory.getLogger(LogInServiceImpl.class);
	@Autowired
	private LogInRepository logInRepository;
	@Autowired
	private EcryptionDecryption ecryptionDecryption;

	public LogInServiceImpl() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	/*
	 * public static void main(String[] args) { EcryptionDecryption
	 * ecryptionDecryption=new EcryptionDecryption();
	 * System.out.println(ecryptionDecryption.decrypt("O1oxm/Qko4hurGo79582DQ=="));
	 * }
	 */

	@Override
	public UserEntity logIn(LogInDTO dto) throws LogInServiceException {
		logger.debug("invoked login method in service with args\t" + dto);
		try {
			UserEntity entityFromDb = logInRepository.logIn(dto);
			if (entityFromDb != null) {
				String pwdBydecrypt = ecryptionDecryption.decrypt(entityFromDb.getPassword());
				if (pwdBydecrypt.equals(dto.getPassword())) {
					logger.debug("user found and will log in successfully");
					return entityFromDb;

				} else {
					return null;
				}
			} else {
				return null;
			}

		} catch (Exception e) {
			logger.debug("Exception occured in logIn controller\t" + e.getMessage());
			throw new LogInServiceException("Exception thrown in logIn controller\t" + e.getMessage());
		}

	}

}
