package com.test.controller;

import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.ReplyDto;
import com.test.service.BoardService;

public class BoardReplyListController implements SubController {

	private BoardService service=BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
	
		try {
			//1 파라미터
			Map<String,String[]> params = req.getParameterMap();
			
			//2 유효성체크
			
			//3 서비스
			
			ReplyDto dto = new ReplyDto();
			dto.setBno(params.get("bno")[0]);
			List<ReplyDto> list=service.replyList(dto);
			
			
			PrintWriter out = resp.getWriter();
			for(ReplyDto rdto : list)
			{
	 			out.println("<div class='w-75' style='display:flex;justify-contents:left;position:relative;'>");
	 			out.println("	<div style='margin:10px;'>");
	 			out.println("		<i class='bi bi-person-circle' style='font-size:2rem'></i>");		
				out.println("	</div>");
				out.println("	<div  style='margin:10px;'>");
				out.println("		<div style=font-size:0.8rem><span>"+rdto.getEmail()+"</span> <span>"+rdto.getDate()+"</span></div>");
				out.println("		<div>"+rdto.getContent()+"</div>");
				out.println("		<div style='display:flex;justify-contents:left;'>");
				out.println("			<i class='bi bi-hand-thumbs-up'></i>&nbsp;<span>0</span>&nbsp;");
				out.println("			<i class='bi bi-hand-thumbs-down'></i>&nbsp;<span>0</span>&nbsp;");
				out.println("		</div>");
				out.println("	</div>");
				out.println("	<div style='position:absolute;right:0px;'>");
				out.println("		<i class=\"bi bi-three-dots-vertical\"></i>");
				out.println("	</div>");
				out.println("</div>");		
				
			}
			
			
			//4 뷰(X)
		}catch(Exception e) {
			e.printStackTrace();
		}
		
	}

}
