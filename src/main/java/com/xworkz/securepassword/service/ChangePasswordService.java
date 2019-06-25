package com.xworkz.securepassword.service;

import java.util.HashMap;

import com.xworkz.securepassword.dto.ChangePasswordDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.ChangePasswordServiceException;

public interface ChangePasswordService {
	public HashMap<String, String> changePassword(ChangePasswordDTO dto) throws ChangePasswordServiceException;

	public UserEntity checkUserByUserName(String userName);

}
