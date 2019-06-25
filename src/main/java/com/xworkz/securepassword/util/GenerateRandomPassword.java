package com.xworkz.securepassword.util;

import java.util.Random;

import org.springframework.stereotype.Component;

@Component
public class GenerateRandomPassword {
	public GenerateRandomPassword() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());
	}

	/*
	 * public UUID generatePassword() {
	 * 
	 * UUID uuid1 = Generators.timeBasedGenerator().generate(); return uuid1; }
	 */

	public String generatePassword() {
		int len = 10;
		String Capital_chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String Small_chars = "abcdefghijklmnopqrstuvwxyz";
		String numbers = "0123456789";
		String symbols = "!@#$%^&*_=+-/.?<>)";
		String values = Capital_chars + Small_chars + numbers + symbols;

		Random rndm_method = new Random();
		char[] pwd = new char[len];
		for (int i = 0; i < len; i++) {
			pwd[i] = values.charAt(rndm_method.nextInt(values.length()));

		}
		String password = new String(pwd);
		return password;
	}
}
