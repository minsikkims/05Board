package com.test.dto;

public class MemberDto {
	private String email;
	private String pwd;
	private String phone;
	private String zipcode;
	private String addr1;
	private String addr2;
	private String grade;
	
	public MemberDto() {}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPwd() {
		return pwd;
	}

	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getZipcode() {
		return zipcode;
	}

	public void setZipcode(String zipcode) {
		this.zipcode = zipcode;
	}

	public String getAddr1() {
		return addr1;
	}

	public void setAddr1(String addr1) {
		this.addr1 = addr1;
	}

	public String getAddr2() {
		return addr2;
	}

	public void setAddr2(String addr2) {
		this.addr2 = addr2;
	}

	public String getGrade() {
		return grade;
	}

	public void setGrade(String grade) {
		this.grade = grade;
	}

	@Override
	public String toString() {
		return "MemberDto [email=" + email + ", pwd=" + pwd + ", phone=" + phone + ", zipcode=" + zipcode + ", addr1="
				+ addr1 + ", addr2=" + addr2 + ", grade=" + grade + "]";
	}
	
	
	//default 생성자
	//getter and setter
	
	//toSting
	
}
