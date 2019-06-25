package com.xworkz.securepassword.email;

import com.xworkz.securepassword.entity.UserEntity;
import com.xworkz.securepassword.exception.JavaMailSendingException;

public interface JavaMailSending {
public void mailSending(UserEntity entity)throws JavaMailSendingException;

public void forgotPassword(UserEntity entity) throws JavaMailSendingException;
}
