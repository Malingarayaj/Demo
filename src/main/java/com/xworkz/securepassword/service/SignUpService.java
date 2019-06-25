package com.xworkz.securepassword.service;

import java.util.HashMap;

import org.hibernate.service.spi.ServiceException;

import com.xworkz.securepassword.dto.SignUpDTO;

public interface SignUpService {
	public HashMap<String, String> saveSignUp(SignUpDTO signUpDTO)throws ServiceException;

}
