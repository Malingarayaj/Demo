package com.xworkz.securepassword.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import com.xworkz.securepassword.util.EcryptionDecryption;

public class JavaMAilSenderImplement extends JavaMailSenderImpl {
	@Autowired
	private EcryptionDecryption enc;

	JavaMAilSenderImplement(String password) {
		super.setPassword(enc.decrypt(password));
	}
}
