package com.xworkz.securepassword.service;

import java.util.List;

import com.xworkz.securepassword.dto.UserCredentialDTO;

public interface UserCredentialService {

	
	public boolean addCredential(UserCredentialDTO dto);
	public List<UserCredentialDTO> getAllCredential(String username);
	public UserCredentialDTO editUserCredintialService(String appName,String userNmae);
	public UserCredentialDTO updateUserCredintialService(UserCredentialDTO dto);
	public void deleteCredintialService(String appName,String userName);
}
