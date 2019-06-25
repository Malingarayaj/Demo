package com.xworkz.securepassword.repository;

import com.xworkz.securepassword.entity.UserEntity;

public interface ForgotPasswordRepository {

	public UserEntity checkUserByNameEmailMobileNo(String userName, String email, long mobileNo);

	public void setFalseFirstLog(UserEntity entity);
	public boolean resetPasswordUpdateRepository(UserEntity entity);

	public UserEntity findkUserByName(String userName);


}
