package com.test.service;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.mindrot.bcrypt.BCrypt;

import com.test.dao.MemberDao;
import com.test.dto.AuthDto;
import com.test.dto.MemberDto;

public class AuthService {
	
	
	BCrypt bc = new BCrypt();
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
	public boolean LoginCheck(Map<String,String[]>params, HttpServletRequest request) {
		boolean flag=false;
		
		
		String email = params.get("email")[0];
		String pwd = params.get("pwd")[0];
		
		MemberDto mdto= dao.Select(email);
		if(mdto!=null) //ID 일치 여부확인 OK(DB)
		{
			if( bc.checkpw(pwd, mdto.getPwd()) ) //PW 일치 여부확인(DB)
			{
				//ID/PW일치한다면 email/grade(권한) 을 Session에 저장
				AuthDto adto = new AuthDto();
				adto.setEmail(email);
				adto.setGrade(mdto.getGrade());
				
				//Session 유지시간 설정
				HttpSession session = request.getSession();
				session.setAttribute("authdto", adto);
				session.setMaxInactiveInterval(60*5);

				//true 전달
				flag=true;
			}
			
		}
		return flag;
	}
	
	public boolean LogoutRequest(HttpServletRequest req) {
		boolean flag=false;

		HttpSession session = req.getSession(false);
		if(session!=null) {
			session.invalidate();
			flag=true;
		}
			
		
		return flag;
	}
	
	
	//Logout처리
	
	
	
}

