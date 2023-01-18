package com.test.dto;

public class ReplyDto {
	private String rno;
	private String bno;
	private String email;
	private String content;
	private String date;
	
	//디폴트생성자
	//toString
	//getter and setter
	public ReplyDto() {}

	public String getRno() {
		return rno;
	}

	public void setRno(String rno) {
		this.rno = rno;
	}

	public String getBno() {
		return bno;
	}

	public void setBno(String bno) {
		this.bno = bno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	@Override
	public String toString() {
		return "ReplyDto [rno=" + rno + ", bno=" + bno + ", email=" + email + ", content=" + content + ", date=" + date
				+ "]";
	}
	
}
