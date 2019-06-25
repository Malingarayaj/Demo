package com.xworkz.securepassword.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.dto.SignUpDTO;
import com.xworkz.securepassword.email.JavaMailSending;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.JavaMailSendingException;
import com.xworkz.securepassword.repository.ForgotPasswordRepository;
import com.xworkz.securepassword.util.EcryptionDecryption;
@Service
public class ForgotPasswordServiceImpl implements ForgotPasswordService {
	private static final Logger logger = LoggerFactory.getLogger(ForgotPasswordServiceImpl.class);
	@Autowired
	private ForgotPasswordRepository forgotPasswordrepo;
	@Autowired
	private JavaMailSending javaMailSending;

	@Autowired
	private EcryptionDecryption encryption;

	@Override
	public boolean forgotPasswordService(String userName, String email, long mobileNo) {
		logger.info("invoked forgotPasswordService");
		try {
			UserEntity entity = forgotPasswordrepo.checkUserByNameEmailMobileNo(userName, email, mobileNo);

			if (entity != null) {

				javaMailSending.forgotPassword(entity);
				entity.setFirstLog(false);
				forgotPasswordrepo.setFalseFirstLog(entity);
				return true;
			} else {
				return false;
			}

		} catch (JavaMailSendingException e) {
			logger.debug("Exception occur in forgotPasswordService\t" + e.getMessage());
			e.printStackTrace();
		}

		return false;
	}

	@Override
	public boolean resetPasswordUpdateService(UserEntity entity) {
		logger.info("inside reset resetPasswordUpdateService method");
		try {
			UserEntity userentity = entity;
			userentity.setPassword(encryption.encrypt(userentity.getPassword()));
			userentity.setSecurePhrase(encryption.encrypt(userentity.getSecurePhrase()));
			userentity.setFirstLog(true);
			boolean resetsuccess=forgotPasswordrepo.resetPasswordUpdateRepository(userentity);
		return resetsuccess;
		} catch (Exception e) {
			// TODO: handle exception
		}
		return false;
	}

	@Override
	public SignUpDTO findkUserByName(String userName) {
		logger.info("inside findkUserByName method");
		try {
			UserEntity entity=forgotPasswordrepo.findkUserByName(userName);
			SignUpDTO dto=new SignUpDTO();
			BeanUtils.copyProperties(entity, dto);
			return dto;
		} catch (Exception e) {
			logger.debug("Exception occur in forgotPasswordService\t"+e.getMessage());
		}
		return null;
	}

}
