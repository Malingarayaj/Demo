package com.xworkz.securepassword.service;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.dto.ChangePasswordDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.ChangePasswordServiceException;
import com.xworkz.securepassword.repository.ChangePasswordRepository;
import com.xworkz.securepassword.util.EcryptionDecryption;

@Service
public class ChangePasswordServiceImpl implements ChangePasswordService {
	private static Logger logger = LoggerFactory.getLogger(ChangePasswordServiceImpl.class);
	private ChangePasswordRepository passwordRepository;

	public ChangePasswordServiceImpl() {
		logger.info("object is created:" + this.getClass().getSimpleName());
	}

	@Autowired
	private EcryptionDecryption encryptDecrypt;

	@Override
	public HashMap<String, String> changePassword(ChangePasswordDTO dto) throws ChangePasswordServiceException {
		logger.debug("invoked changePassword in service\t" + dto);
		UserEntity entityFromDb = new UserEntity();
		HashMap<String, String> map = new HashMap<String, String>();
		try {
			if (!StringUtils.isEmpty(dto.getUserName()) && !StringUtils.isEmpty(dto.getOldPassword())
					&& !StringUtils.isEmpty(dto.getNewPassword()) && !StringUtils.isEmpty(dto.getConfirmPassword())
					&& !StringUtils.isEmpty(dto.getSecurePhrase())) {
				if (dto.getNewPassword().equals(dto.getConfirmPassword())) {
					boolean valid = validatePassword(dto);
					if (valid) {
						entityFromDb = passwordRepository.findUserByNamePwd(dto);
						if (entityFromDb != null) {
							if (entityFromDb.isFirstLog() == false) {
								String oldPwd = dto.getOldPassword();
								String decryptedPwd = encryptDecrypt.decrypt(entityFromDb.getPassword());
								if (oldPwd.equals(decryptedPwd)) {
									entityFromDb.setFirstLog(true);
									entityFromDb.setPassword(encryptDecrypt.encrypt(dto.getNewPassword()));
									entityFromDb.setSecurePhrase(encryptDecrypt.encrypt(dto.getSecurePhrase()));
									boolean updatefromdb = passwordRepository.changePassword(entityFromDb);
									if (updatefromdb) {
										map.put("updateSuccess", "Successfully updated password");
										return map;
									}
								} else {
									map.put("wrongPwd",
											"you!...Entered wrong password! please enter coorect password we sent in email");
								}
							} else {
								map.put("AlredyUpdated",
										"sory...! you already updated your password again you can't change the password here");
								return map;
							}
						} else {
							map.put("userNull", "User does Not exist......dont change the username");
						}
					} else {
						map.put("pwdLenth",
								"password length should be 8 and should contains uppercase,lowercase and number");
						return map;
					}

				} else {
					map.put("pwdNotSame", "new password and old password shoulb be same");

					return map;
				}
			} else {
				map.put("emptyFields", "please enter all the fields");
				return map;
			}
		} catch (Exception e) {
			logger.debug("Exception occured in ChangePasswordServiceImpl\t" + e.getMessage());
			throw new ChangePasswordServiceException(
					"Exception  thrown in ChangePasswordServiceImpl\t" + e.getMessage());
		}
		return map;
	}

	public static boolean validatePassword(ChangePasswordDTO dto) throws ChangePasswordServiceException {
		logger.debug("invoked validating pwassword");
		try {
			Pattern p = Pattern.compile(ApplicationConstants.PASSWORD_PATTERN);
			Matcher m = p.matcher(dto.getNewPassword());
			if (m.matches()) {
				return true;

			} else {
				return false;
			}
		} catch (Exception e) {
			logger.debug("Exception occured in ChangePasswordServiceImpl\t" + e.getMessage());
			throw new ChangePasswordServiceException(
					"Exception  thrown in ChangePasswordServiceImpl\t" + e.getMessage());
		}

	}

	@Override
	public UserEntity checkUserByUserName(String userName) throws ServiceException {
		logger.debug("entering checkUserByUserName in service\t" + userName);
		try {

			UserEntity userFromDb = passwordRepository.checkUserByUserName(userName);
			return userFromDb;

		} catch (Exception e) {
			logger.debug("Exception in SignUpService\t" + e.getMessage());
			throw new ServiceException("Exception thrown in Service\t" + e.getMessage());
		}

	}

}
