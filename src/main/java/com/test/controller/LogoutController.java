package com.test.controller;

import java.io.IOException;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.AuthService;

public class LogoutController  implements SubController{
	
	private AuthService service = AuthService.getInstance();
	private static String msg;
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		 
		//1 파라미터 생략
		//2 입력값 생략

		//3 서비스 실행
		boolean flag= service.LogoutRequest(req);
		if(!flag) {
			//로그아웃 실패시
			
		}
		msg=URLEncoder.encode("로그아웃을 완료하였습니다.");
		
		//4 view
		String path=req.getContextPath();
		try {
			
			resp.sendRedirect(path+"/auth/login.do?msg="+msg);
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

}
