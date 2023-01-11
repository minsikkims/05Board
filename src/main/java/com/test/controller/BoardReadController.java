package com.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dto.BoardDto;
import com.test.dto.PageDto;
import com.test.service.BoardService;

public class BoardReadController implements SubController {
	
	private static String msg;
	private BoardService service = BoardService.getInstance();	 
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {	
			
			//1 파라미터 받기
			Map<String,String[]> params=req.getParameterMap();
	
			//2 Validation
			boolean isvalid=isValid(params);
			if(!isvalid) {
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로			
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/board/post.jsp").forward(req, resp);
				return ;
			}
			
			//3 Service 실행
			int bno = Integer.parseInt(params.get("bno")[0]);
			boolean result = service.GetBoard(bno,req,resp);
			
			
			//4 View 이동
			String method=req.getMethod();	
			System.out.println("[BRC] 요청 METHOD : " + method);
			req.getRequestDispatcher("/WEB-INF/view/board/read.jsp").forward(req, resp);
			return ;
			
			
			
		}catch(Exception e) {
			e.printStackTrace();
		}
	

	}

	
	
	private boolean isValid(Map<String, String[]> params) {
		//msg="유효성 체크 오류";
		return true;
	}
}
