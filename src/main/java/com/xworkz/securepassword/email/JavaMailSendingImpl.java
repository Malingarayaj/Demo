package com.xworkz.securepassword.email;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.JavaMailSendingException;
import com.xworkz.securepassword.util.EcryptionDecryption;
@Service
public class JavaMailSendingImpl implements JavaMailSending {

	private static Logger logger = LoggerFactory.getLogger(JavaMailSendingImpl.class);
	@Autowired
	private JavaMailSender javaMailSender;
	
	@Autowired
	private EcryptionDecryption ecryptionDecryption;


	public JavaMailSendingImpl() {
		logger.debug("created.....\t" + this.getClass().getSimpleName());
	}

	@Override
	public void mailSending(UserEntity entity) throws JavaMailSendingException {
		logger.debug("this message from java mail sender \t" + entity);
		SimpleMailMessage mailMessage = new SimpleMailMessage();
		try {
			mailMessage.setTo(entity.getEmail());
			mailMessage.setSubject("Registration confirmation mail");
			mailMessage.setText("Hi" + entity.getUserName() + "you have successfully register for secure password"
					+ "\n" + "with username\t" + entity.getUserName() + "\n" + "with password" + "\t"
					+ EcryptionDecryption.decrypt(entity.getPassword()) + "\n" + "This is for confirmation password.\n"
					+ "http://localhost:8089/secondbatchproject/changePassword?userName=" + entity.getUserName());

			javaMailSender.send(mailMessage);
			logger.debug("mail sent successfully");
			return;

		} catch (Exception e) {
			logger.debug("exception created in mailSending()\t" + e.getMessage());
			throw new JavaMailSendingException("Exception thrown in mailSending\t" + e.getMessage());

		}

	}
//	@Override
//	public void changePwdMailSending(UserEntity entity) throws JavaMailSendingException {
//
//	}

	@Override
	public void forgotPassword(UserEntity entity) throws JavaMailSendingException {
		logger.info("inside the forgotPassword method in ");
		try {
			SimpleMailMessage message=new SimpleMailMessage();
			message.setTo(entity.getEmail());
			message.setSubject("reset password");
			
			message.setText("hi"+entity.getUserName()+"This link to reset your user password\t"+"http://localhost:8089/SecurePassword/resetpassword?userName="+entity.getUserName());
			javaMailSender.send(message);
			logger.info("mail sent Successfully");
			return;
		} catch (Exception e) {
			logger.debug("exception created in forgotPassword()\t" + e.getMessage());
			throw new JavaMailSendingException("Exception thrown in forgotPassword\t" + e.getMessage());		}
		
	}

}
