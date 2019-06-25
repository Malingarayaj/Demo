package com.xworkz.securepassword.service;

import java.util.HashMap;
import java.util.List;
import java.util.Objects;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.xworkz.securepassword.dto.SignUpDTO;
import com.xworkz.securepassword.email.JavaMailSending;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.repository.SignUpRepository;
import com.xworkz.securepassword.util.EcryptionDecryption;
import com.xworkz.securepassword.util.GenerateRandomPassword;
import com.xworkz.securepassword.validator.SignUpValidation;

@Service
public class SignUpServiceImpl implements SignUpService {

	private static Logger logger = LoggerFactory.getLogger(SignUpServiceImpl.class);
	@Autowired
	private SignUpValidation signupvalidation;

	@Autowired
	private SignUpRepository signuprepository;

	@Autowired
	private EcryptionDecryption encryptDecrypt;

	@Autowired
	private GenerateRandomPassword randomPassword;

	@Autowired
	private JavaMailSending javaMailSending;

	public HashMap<String, String> saveSignUp(SignUpDTO signUpDTO) throws ServiceException {
		HashMap<String, String> map = new HashMap<String, String>();
		logger.debug("invoked saveSignUp method in service with args\t" + signUpDTO);

		try {
			if (!StringUtils.isEmpty(signUpDTO.getEmail()) && !StringUtils.isEmpty(signUpDTO.getUserName())
					&& Objects.nonNull(signUpDTO.getMobileNo())) {
				map = signupvalidation.Validate(signUpDTO);
				if (map.isEmpty()) {
					UserEntity entity = new UserEntity();
					BeanUtils.copyProperties(signUpDTO, entity);
					List<UserEntity> entityFromDb = signuprepository.findUserByNameEmailMno(entity);
					logger.debug("entityFromDb:{}", entityFromDb);
					if (entityFromDb.isEmpty()) {
						String password = randomPassword.generatePassword();
						logger.debug("password generated \t" + password);
						String pwdfromEncrypt = encryptDecrypt.encrypt(password);
						logger.debug("password encrypted\t" + pwdfromEncrypt);
						entity.setPassword(pwdfromEncrypt);
						entity.setFirstLog(false);
						String pkFromDbAfterSignUp = signuprepository.saveSignUp(entity);
						logger.debug("after save data to db\t" + pkFromDbAfterSignUp);

						if (pkFromDbAfterSignUp != null) {

							logger.debug("invoked check the condition pk is null or not");
							javaMailSending.mailSending(entity);
							map.put("success", "Account created successfully");
							return map;
						}
					} else {
						map.put("exist", "You have already created account");
						return map;
					}
				} else {
					return map;
				}

			} else {
				map.put("emptyField", "Enter All the  Fields");
				return map;
			}

		} catch (Exception e) {
			logger.debug("Exception in SignUpService\t" + e.getMessage());
			throw new ServiceException("Exception thrown in Service\t" + e.getMessage());
		}
		return map;

	}
}