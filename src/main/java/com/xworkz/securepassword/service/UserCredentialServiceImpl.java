package com.xworkz.securepassword.service;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.dto.UserCredentialDTO;
import com.xworkz.securepassword.entity.UserCredentialEntity;
import com.xworkz.securepassword.repository.UserCredentialRepository;
import com.xworkz.securepassword.util.EcryptionDecryption;

@Service
public class UserCredentialServiceImpl implements UserCredentialService {
	private static final Logger logger = LoggerFactory.getLogger(UserCredentialServiceImpl.class);
	@Autowired
	private EcryptionDecryption ecryptionDecryption;

	@Autowired
	private UserCredentialRepository repository;
	public UserCredentialServiceImpl() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}
	
	public boolean addCredential(UserCredentialDTO dto)
	{
		logger.debug("invoking addCredential with args\t" + dto);
		try {
			UserCredentialEntity entityFromDb = repository.checkUserCredentials(dto);
			logger.debug("data from db to service\t" + entityFromDb);
			if (entityFromDb != null) {
				return false;
			} else {

				UserCredentialEntity credentialEntity = new UserCredentialEntity();
				BeanUtils.copyProperties(dto, credentialEntity);
				credentialEntity.setAppPassword(ecryptionDecryption.encrypt(dto.getAppPassword()));
				UserCredentialEntity entityfromdbAfterSave = repository.addCredential(credentialEntity);
				return true;
			}

		} catch (Exception e) {
			logger.debug("exception occur in addCredential in UserCredentialServiceImpl" + e.getMessage());
		}
		return false;


	}
	
	
	@Override
	public List<UserCredentialDTO> getAllCredential(String username) {
		try {
			List<UserCredentialDTO> credentialDTOs=new ArrayList<UserCredentialDTO>();
			List<UserCredentialEntity> credentialEntity =repository.getAllCredential(username);
			logger.debug("credentialEntity\t" + credentialEntity);
			for(UserCredentialEntity userCredentialEntity:credentialEntity)
			{
				UserCredentialDTO dto = new UserCredentialDTO();
				BeanUtils.copyProperties(userCredentialEntity, dto);
				dto.setAppPassword(ecryptionDecryption.decrypt(dto.getAppPassword()));
				credentialDTOs.add(dto);
				}
			logger.debug("credentialDTOs\t" + credentialDTOs);
			return credentialDTOs;

		} catch (Exception e) {
			logger.debug("exception occur in addCredential in UserCredentialServiceImpl" + e.getMessage());
		}
		return null;
	}
	@Override
	public UserCredentialDTO editUserCredintialService(String appName, String userNmae) {
		logger.debug("invoking editUserCredintialService\t" + appName + userNmae);
		System.out.println("invoking editUserCredintialService\t" + appName + userNmae);
		try {
			UserCredentialEntity entityFromDb = repository.editUserCredintialRepo(appName, userNmae);
			UserCredentialDTO dtos = new UserCredentialDTO();
			BeanUtils.copyProperties(entityFromDb, dtos);
			dtos.setAppPassword(ecryptionDecryption.decrypt(dtos.getAppPassword()));
			return dtos;

		} catch (Exception e) {
			logger.debug("Exception occur in editUserCredintialService\t" + e.getMessage());
		}
		return null;

	}
	@Override
	public UserCredentialDTO updateUserCredintialService(UserCredentialDTO dto) {
		System.out.println("invoking editUserCredintialService\t" + dto);
		logger.debug("invoking updateUserCredintialService\t" + dto);
		try {
			UserCredentialEntity entity = new UserCredentialEntity();
			BeanUtils.copyProperties(dto, entity);
			entity.setAppPassword(ecryptionDecryption.encrypt(entity.getAppPassword()));
			repository.updateUserCredintialRepo(entity);
			return dto;

		} catch (Exception e) {
			logger.debug("Exception occure in updateUserCredintialService\t" + e.getMessage());
		}
		return null;

	}

	@Override
	public void deleteCredintialService(String appName, String userName) {
		logger.debug("invoking deleteCredintialService\t" + appName + userName);
		try {
			repository.deleteCredintialRepo(appName, userName);
			return;

		} catch (Exception e) {
			logger.debug("Exception occure in deleteCredintialService\t" + e.getMessage());
		}
	}
}
	