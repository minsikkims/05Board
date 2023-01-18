package com.test.controller;

import java.net.URLEncoder;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.MemberDto;
import com.test.service.MemberService;

public class MemberJoinController  implements SubController{
	
	private static String msg;	//메시지전달 변수
	
	private MemberService service = MemberService.getInstance();	//service 받기
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		//System.out.println("[MJC] Start-");
		try 
		{
			//0 Get구별
			String method=req.getMethod();	//method꺼내기
			if(method.equals("GET")) {	//method가 GET이라면 그대로 전달
				System.out.println("[MJC] 요청 METHOD : " + method);	//요청method 확인
				req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
				return ;
			}
			
			//1 파라미터 받기
			Map<String,String[]> params=req.getParameterMap();	//Map형식으로 모든 파라미터 받기
//			for(String name : params.keySet()) { 파라미터 확인
//				System.out.println("name : " + name  + " val : "+params.get(name)[0]);
//			}
			
			//2 Validation(유효성 검증)
			boolean isvalid=isValid(params);	//파라미터 전달
			if(!isvalid) {	
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로
				req.setAttribute("msg", msg);	//msg로 된 속성의 값을 msg로 지정
				req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);	//request를 보낼 경로
				return ;
			}
			
			//3 Service
			MemberDto dto = new MemberDto();	//MemberDto 생성
			dto.setEmail(params.get("email")[0]);	//값 넣기
			dto.setPwd(params.get("pwd")[0]);
			dto.setPhone(params.get("phone")[0]);
			dto.setZipcode(params.get("zipcode")[0]);
			dto.setAddr1(params.get("addr1")[0]);
			dto.setAddr2(params.get("addr2")[0]);
			boolean result=service.memberJoin(dto);	//result로 결과 받아오기

			//4 View(로그인페이지로이동)			
			if(result) {
				//로그인
				msg= URLEncoder.encode("회원가입 성공하셨습니다.");	//URL코드에 맞게 인코딩 작업
				
				String path = req.getContextPath();	//경로 받기
				resp.sendRedirect(path+"/auth/login.do?msg="+msg);//URL 변경
				return ;
			}
			else {
				//회원가입페이지로 이동
				msg="<i class='bi bi-exclamation-triangle' style='color:orange'></i><span>회원가입 양식에 맞게 다시 입력하세요.</span>";
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/member/join.jsp").forward(req, resp);
				return ;
			}

			
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean isValid(Map<String, String[]> params) {
		
		for(String name : params.keySet()) {
			//공백확인
			 if(params.get(name)[0].isEmpty()) {	//받은 name이 값이 없다면
				 msg="<i class='bi bi-exclamation-triangle' style='color:orange'></i> <span>공백은 포함할 수 없습니다.</span>";
				 return false;	//반환x
			 }
			 //패스워드 복잡성 체크(비밀번호 정규식 패턴)
			 if(name.equals("pwd"))
			 {
				 String pwvalue = params.get(name)[0];	//입력된 패스워드 값 가져오기
				 Pattern passPattern1 = Pattern.compile("^(?=.*[a-zA-Z])(?=.*\\d)(?=.*\\W).{8,20}$");	//패턴 지정
				 Matcher passMatcher1 = passPattern1.matcher(pwvalue);	//입력한 방식과 일치하는지 확인
				  
				 if(!passMatcher1.find()){
					 msg="<i class='bi bi-exclamation-triangle' style='color:orange'></i> "
					 		+ "<span>패스워드는 영소문자/대문자/특수문자/숫자를 포함하며 8자 이상이어야 합니다.</span>";	//메시지 전달
					 return false;
				 }
			 }
			 
		}
		
		//msg="<i class='bi bi-exclamation-triangle' style='color:orange'></i> <span>유효성 검사 오류</span>";
		return true;
	}

}
