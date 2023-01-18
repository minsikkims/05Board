package com.test.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BoardUpdateController implements SubController {

	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
			//0 Get구별
			String method=req.getMethod();
			if(method.equals("GET")) {
	
				System.out.println("[BUC] 요청 METHOD : " + method);
				req.getRequestDispatcher("/WEB-INF/view/board/update.jsp").forward(req, resp);
				return ;
			}
		
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
