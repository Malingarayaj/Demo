package com.xworkz.securepassword.validator;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.xworkz.securepassword.constants.ApplicationConstants;
import com.xworkz.securepassword.dto.SignUpDTO;
@Service
public class SignUpValidateImpl implements SignUpValidation {

	private Pattern pattern;
	private Matcher matcher;
	private static Logger logger = LoggerFactory.getLogger(SignUpValidateImpl.class);

	public SignUpValidateImpl() {
		logger.info("object is created for" + this.getClass().getSimpleName());
	}

	public HashMap<String, String> Validate(SignUpDTO dto) {
		HashMap<String, String> map = new HashMap<String, String>();
		logger.info("entering the validation method in validate class");
		try {
			if (dto.getUserName() == null || dto.getUserName().isEmpty()) {
				map.put("emptyName", "Please enter the all fieldss");
				logger.info("invoked emptyName");
				return map;
			} else if (dto.getUserName() != null) {
				logger.info("invoked username checking");
				pattern = pattern.compile(ApplicationConstants.USERNAME_PATTERN);

				Matcher b = pattern.matcher(dto.getUserName());
				if (!b.matches()) {
					logger.info("invoked nameLength");
					map.put("nameLength", "Name must be min 4 or max 8 letters");
					return map;
				}
			} else if (dto.getMobileNo() == null || dto.getMobileNo() <= 0) {
				logger.info("invoked EmptyNo");
				map.put("EmptyNo", "Please enter the mobile number");
				return map;
			} else if (dto.getMobileNo() != 0) {
				logger.info("invoked EmptyNo");
				pattern = Pattern.compile(ApplicationConstants.MOBILENO_PATTERN);
				String s = dto.getMobileNo().toString();
				Matcher m = pattern.matcher(s);
				boolean b = m.find() && m.group().equals(s);
                if (!b) {
                	logger.info("invoked invalidNo");
					map.put("invalidNo", "Enter the valid  mobile number");
					return map;
				}
			} else if (dto.getEmail().isEmpty()) {
				logger.info("invoked emptyMail");
				map.put("emptyMail", "please enter the mail id");
				return map;
			} else if (dto.getEmail() != null) {
				pattern = Pattern.compile(ApplicationConstants.EMAIL_PATTERN);
				matcher = pattern.matcher(dto.getEmail());
				if (!matcher.matches()) {
					logger.info("invoked wrongEmail");
					map.put("wrongEmail", "please valid email id");
					return map;
				}
			} else {
				return map;
			}
		} catch (Exception e) {
			System.out.println("exception in signUp validation \t" + e.getMessage());
		}
		return map;
	}

}
