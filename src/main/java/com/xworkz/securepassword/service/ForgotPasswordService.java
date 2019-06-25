package com.xworkz.securepassword.service;

import com.xworkz.securepassword.dto.SignUpDTO;
import com.xworkz.securepassword.entity.UserEntity;

public interface ForgotPasswordService {
	public boolean forgotPasswordService(String userName,String email,long mobileNo);

	public boolean resetPasswordUpdateService(UserEntity entity);
	
	public SignUpDTO findkUserByName(String userName);

}
