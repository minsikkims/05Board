package com.test.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;

import com.test.dao.MemberDao;
import com.test.dto.AuthDto;
import com.test.dto.MemberDto;

public class AuthService {
	
	
	BCrypt bc = new BCrypt();	//암호화
	MemberDao dao = MemberDao.getInstance();
	
	//싱글톤
	private static AuthService instance;
	public static AuthService getInstance() {
		if(instance==null)
			instance=new AuthService();
		return instance;
	}
	private AuthService() {};
	//싱글톤
	
	//Login확인
	public boolean LoginCheck(Map<String,String[]>params, HttpServletRequest request) {	//String배열로 params받기,request받기
		boolean flag=false;	//false 설정
		
		
		String email = params.get("email")[0];	//email 배열로 받기
		String pwd = params.get("pwd")[0];	//pwd 배열로 받기
		
		MemberDto mdto= dao.Select(email);	
		if(mdto!=null) //ID 일치 여부확인 OK라면(DB)
		{
			if( bc.checkpw(pwd, mdto.getPwd()) ) //PW 일치 여부확인(DB)
			{
				//ID/PW일치한다면 email/grade(권한)을 Session에 저장
				AuthDto adto = new AuthDto();	//AuthDto 생성
				adto.setEmail(email);	//email정보
				adto.setGrade(mdto.getGrade());	//등급 정보
				
				//Session 유지시간 설정
				HttpSession session = request.getSession();	//session 내장객체를 request로 부터 꺼내오기
				session.setAttribute("authdto", adto);	//session에 setAttribute로 authdto를 전달
				session.setMaxInactiveInterval(60*5);	//session 접근시간 및 유효시간 설정(60*5=5분)

				//true 전달
				flag=true;
			}
			
		}
		return flag;
	}
	
	public boolean LogoutRequest(HttpServletRequest req) {
		boolean flag=false;

		HttpSession session = req.getSession(false);	//새로운 객체 생성 x
		if(session!=null) {
			session.invalidate();	//session 무효화
			flag=true;
		}
			
		
		return flag;
	}
	
	
	//Logout처리
	
	
	
}

