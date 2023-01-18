package com.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.BoardService;

public class BoardDownloadzipController implements SubController {
	private static String msg;
	private BoardService service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		try {
		//1 파라미터	
		Map<String,String[]> params=req.getParameterMap();

		//2 유효성검사
		boolean isvalid=isValid(params);
		if(!isvalid) {
			//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로			
			req.setAttribute("msg", msg);
			req.getRequestDispatcher("/WEB-INF/view/list.jsp").forward(req, resp);
			return ;
		}		
		//3 서비스실행
		service.downloadzip(req,resp);
		
		//4 뷰로이동(X)
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

	private boolean isValid(Map<String, String[]> params) {
		return true;
	}

}
