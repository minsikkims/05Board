package com.test.controller;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.test.dto.AuthDto;
import com.test.dto.ReplyDto;
import com.test.service.BoardService;

public class BoardReplyPostController implements SubController {

	private BoardService service=BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {

		try {
			//1 파라미터
			Map<String,String[]> params = req.getParameterMap();
			
			//2 유효성체크
			
			//3 서비스
			//ReplyDto에 파라미터 저장
			HttpSession session = req.getSession(false);
			AuthDto adto =(AuthDto)session.getAttribute("authdto");
			
			ReplyDto rdto = new ReplyDto();
			rdto.setBno(params.get("bno")[0]);
			rdto.setContent(params.get("comment")[0]);
			rdto.setEmail(adto.getEmail());
			
			boolean flag=service.replyPost(rdto);
			if(flag) {
				PrintWriter out=resp.getWriter();
				out.print("댓글 저장 성공성공성공성공!!!!!!!!!!");
			}		
			
			//4 뷰(X)
		}catch(Exception e) {
			e.printStackTrace();
		}

	}

}
