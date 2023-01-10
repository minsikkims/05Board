package com.test.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.dto.MemberDto;

public class MemberDao {
	
	//DataSource
	private DataSource ds;
	
	//싱글톤패턴
	private static MemberDao instance;
	public static MemberDao getInstance() {
		if(instance==null)
			instance = new MemberDao();
		return instance;
	}
	
	
	private MemberDao() {
		//DB관련 코드 적용(DBCP / HikariDb / Mabatis / JPA ...)
		try {
			//1. JNDI 객체 생성
			InitialContext ic= new InitialContext();
			//2. DataSource 자원 찾기
			ds = (DataSource) ic.lookup("java:comp/env/jdbc/mysql");
			
			System.out.println("DS : " + ds);
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
	//싱글톤패턴
	
	
	//INSERT
	public int Insert(MemberDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		int result=0;
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("insert into tbl_member values(?,?,?,?,?,?,?)");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getPwd());
			pstmt.setString(3, dto.getPhone());
			pstmt.setString(4, dto.getZipcode());
			pstmt.setString(5, dto.getAddr1());
			pstmt.setString(6, dto.getAddr2());
			pstmt.setString(7, "0");	//Grade
			
			result=pstmt.executeUpdate();
				
		
		}catch(Exception e) {
			e.printStackTrace();
		
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	//SELECT
	public MemberDto Select(String email) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		MemberDto dto=null;

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_member where email=?");
			pstmt.setString(1, email);
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				rs.next();
				dto = new MemberDto();
				dto.setEmail(rs.getString(1));
				dto.setPwd(rs.getString(2));
				dto.setPhone(rs.getString(3));
				dto.setZipcode(rs.getString(4));
				dto.setAddr1(rs.getString(5));
				dto.setAddr2(rs.getString(6));
				dto.setGrade(rs.getString(7));	
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return dto;
	}
	
	
	
	
	
	

	
}
