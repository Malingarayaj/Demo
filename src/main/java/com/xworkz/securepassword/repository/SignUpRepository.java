package com.xworkz.securepassword.repository;

import java.util.List;

import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.RepositoryException;

public interface SignUpRepository {
	public String saveSignUp(UserEntity entity) throws RepositoryException;
	

	List<UserEntity> findUserByNameEmailMno(UserEntity entity) throws RepositoryException;

}
