package com.xworkz.securepassword.validator;

import java.util.HashMap;

import com.xworkz.securepassword.dto.SignUpDTO;

public interface SignUpValidation {
	public HashMap<String, String> Validate(SignUpDTO dto);

}
