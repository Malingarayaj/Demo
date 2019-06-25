package com.xworkz.securepassword.repository;

import com.xworkz.securepassword.dto.ChangePasswordDTO;
import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.ChangePasswordRepoException;

public interface ChangePasswordRepository {

	UserEntity findUserByNamePwd(ChangePasswordDTO dto)throws ChangePasswordRepoException, ChangePasswordRepoException;

	public boolean changePassword(UserEntity entity) throws ChangePasswordRepoException;

	public UserEntity checkUserByUserName(String userName) throws ChangePasswordRepoException;

}
