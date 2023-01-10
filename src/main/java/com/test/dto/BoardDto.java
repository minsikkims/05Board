package com.test.dto;

public class BoardDto {
	private String no;
	private String email;
	private String title;
	private String content;
	private String regdate;
	private String count;
	private String dirpath;
	private String filename;
	private String filesize;
	
	//default 생성자
	//toString
	//getter and setter
	public BoardDto() {}

	@Override
	public String toString() {
		return "BoardDto [no=" + no + ", email=" + email + ", title=" + title + ", content=" + content + ", regdate="
				+ regdate + ", count=" + count + ", dirpath=" + dirpath + ", filename=" + filename + ", filesize="
				+ filesize + "]";
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRegdate() {
		return regdate;
	}

	public void setRegdate(String regdate) {
		this.regdate = regdate;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getDirpath() {
		return dirpath;
	}

	public void setDirpath(String dirpath) {
		this.dirpath = dirpath;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
	
	
}
