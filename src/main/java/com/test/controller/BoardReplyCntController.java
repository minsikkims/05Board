package com.test.controller;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.service.BoardService;

public class BoardReplyCntController implements SubController {

	private BoardService  service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		try {
				
			//1 파라미터
			
			//2 유효성
			
			//3 서비스!!!!
			int bno = Integer.parseInt(req.getParameter("bno"));
			int count = service.getReplyCount(bno);
			
			PrintWriter out = resp.getWriter();
			out.println(count);
			
			//4 뷰x
			
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
