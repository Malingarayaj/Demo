package com.xworkz.securepassword.service;

import com.xworkz.securepassword.dto.LogInDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.LogInServiceException;

public interface LogInService {
	public UserEntity logIn(LogInDTO dto) throws LogInServiceException;

}
