package com.xworkz.securepassword.dto;

public class ChangePasswordDTO {
	private String userName;
	private String oldPassword;
	private String newPassword;
	private String confirmPassword;
	private String securePhrase;

	public ChangePasswordDTO() {
		System.out.println("created.....\t" + this.getClass().getSimpleName());

	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;

	}

	public String getOldPassword() {
		return oldPassword;
	}

	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	public String getNewPassword() {
		return newPassword;
	}

	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getSecurePhrase() {
		return securePhrase;
	}

	public void setSecurePhrase(String securePhrase) {
		this.securePhrase = securePhrase;
	}

	@Override
	public String toString() {
		return "ChangePasswordDTO [userName=" + userName + ", oldPassword=" + oldPassword + ", newPassword="
				+ newPassword + ", confirmPassword=" + confirmPassword + ", securePhrase=" + securePhrase + "]";
	}

}
