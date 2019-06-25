package com.xworkz.securepassword.repository;

import com.xworkz.securepassword.dto.LogInDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.LogInRepoException;

public interface LogInRepository {

	public UserEntity logIn(LogInDTO dto) throws LogInRepoException;

}
