package com.test.dto;

public class AuthDto {
	private String email;
	private String grade;
	
	
	//default 생성자
	//getter and setter
	//toString
	public AuthDto() {}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGrade() {
		return grade;
	}
	public void setGrade(String grade) {
		this.grade = grade;
	}
	@Override
	public String toString() {
		return "AuthDto [email=" + email + ", grade=" + grade + "]";
	}
	
	
}
