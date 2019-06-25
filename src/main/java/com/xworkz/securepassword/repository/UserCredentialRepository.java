package com.xworkz.securepassword.repository;

import java.util.List;

import com.xworkz.securepassword.dto.UserCredentialDTO;
import com.xworkz.securepassword.entity.UserCredentialEntity;

public interface UserCredentialRepository {

	public UserCredentialEntity addCredential(UserCredentialEntity entity);
	public UserCredentialEntity checkUserCredentials(UserCredentialDTO dto);
	public List<UserCredentialEntity> getAllCredential(String username);
	public UserCredentialEntity editUserCredintialRepo(String appName,String userNamee);
	public UserCredentialEntity updateUserCredintialRepo(UserCredentialEntity entity);
	public void deleteCredintialRepo(String appName,String userName);


}
