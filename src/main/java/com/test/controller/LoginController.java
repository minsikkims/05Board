package com.test.controller;

import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.AuthService;

public class LoginController  implements SubController{

	private static String msg;	//메시지
	private AuthService service = AuthService.getInstance();	//AuthService 객체 가져오기
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//0 메시지 처리
			msg = req.getParameter("msg");
			if(msg!=null) {
				req.setAttribute("msg", msg);
			}
			//0 Get구별
			String method=req.getMethod();	//메소드 확인
			if(method.equals("GET")) {
				System.out.println("[LC] 요청 METHOD : " + method);	//로그인 확인
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);
				return ;
			}
			//1 파라미터 확인
			Map<String,String[]>params = req.getParameterMap();	//모든 파라미터를 받음
			
			//2 유효성 검사
			boolean isvalid=isValid(params);	//params를 함수에 넣어서 체크
			if(!isvalid) {
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);	// /auth/login으로 전달
				return ;
			}
			
			//3 서비스 실행
			boolean flag = service.LoginCheck(params, req);	//parameter와 request 객체 전달
			if(!flag) {
				msg="<i class='bi bi-exclamation-triangle' style='color:orange;font-size:1rem'></i> ID나 PW가 올바르지 않습니다.";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/auth/login.jsp").forward(req, resp);	//문제가 있다면 로그인으로 다시 이동
				return ;
			}	//아니라면 main으로 이동
			
			//4 View 이동
			String path = req.getContextPath();	//path 잡기
			resp.sendRedirect(path+"/main.do");	//main으로 이동
			return ;
				
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	
	
	
	private boolean isValid(Map<String, String[]> params) {
		
		for(String name : params.keySet()) {
			//공백확인
			 if(params.get(name)[0].isEmpty()) {
				 msg="<i class='bi bi-exclamation-triangle' style='color:orange;font-size:1rem'></i> <span>공백은 포함할 수 없습니다.</span>";
				 return false;
			 }		 
		}
		return true;
	}
	
	
	

}
