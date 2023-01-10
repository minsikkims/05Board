package com.test.controller;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.dto.BoardDto;
import com.test.dto.Criteria;

public class BoardDao {
	
	//DataSource
	private DataSource ds;
	
	//싱글톤패턴
	private static BoardDao instance;
	public static BoardDao getInstance() {
		if(instance==null)
			instance = new BoardDao();
		return instance;
	}
	
	
	private BoardDao() {
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
	
	//SELECTAll
	public List<BoardDto> SelectAll(int startno, int amount) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		List<BoardDto> list = new ArrayList();
		BoardDto dto = null;
		

		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select * from tbl_board order by no desc limit ?,?");
			pstmt.setInt(1, startno);
			pstmt.setInt(2, amount);
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				while(rs.next())
				{
					dto = new BoardDto();
					dto.setNo( rs.getInt(1)+"" );
					dto.setEmail(rs.getString(2));
					dto.setTitle(rs.getString(3));
					dto.setContent(rs.getString(4));
					dto.setRegdate(rs.getString(5));
					dto.setCount(rs.getInt(6)+"");
					dto.setDirpath(rs.getString(7));
					dto.setFilename(rs.getString(8));
					dto.setFilesize(rs.getString(9));
					list.add(dto);
				}
				
			}	
		}catch(Exception e) {
			e.printStackTrace();
			return null;
		
		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return list;
	}


	
	
	public int getAmount() {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		
		int cnt=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("select count(*) from tbl_board");
			
			rs=pstmt.executeQuery();
			if(rs!=null)
			{
				rs.next();
				cnt=rs.getInt(1);
				
			}	
		}catch(Exception e) {
			e.printStackTrace();

		}finally {
			try {rs.close();}catch(Exception e) {}
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return cnt;
	}
	
	
	public int Insert(BoardDto dto) {
		
		Connection conn=null;
		PreparedStatement pstmt = null;
		
		int result=0;
		
		try {
			conn = ds.getConnection();
			pstmt=conn.prepareStatement("insert into tbl_board values(null,?,?,?,now(),0,?,?,?)");
			pstmt.setString(1, dto.getEmail());
			pstmt.setString(2, dto.getTitle());
			pstmt.setString(3, dto.getContent());
			pstmt.setString(4, dto.getDirpath());
			pstmt.setString(5, dto.getFilename());
			pstmt.setString(6, dto.getFilesize());
			result=pstmt.executeUpdate();
			
		}catch(Exception e) {
			e.printStackTrace();
		}finally {
			try {pstmt.close();}catch(Exception e) {}
			try {conn.close();}catch(Exception e) {}
		}
		
		return result;
	}
	
	
	
	
	

	
}
