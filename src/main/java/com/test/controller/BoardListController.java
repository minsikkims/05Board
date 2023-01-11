package com.test.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.dto.Criteria;
import com.test.service.BoardService;

public class BoardListController implements SubController {

	private static String msg;
	private BoardService service = BoardService.getInstance();
	
	@Override
	public void execute(HttpServletRequest req, HttpServletResponse resp) {
		
		//System.out.println("[MJC] Start-");
		try 
		{
			
			//1 파라미터 받기
			Map<String,String[]> params=req.getParameterMap();

			
			//2 Validation
			boolean isvalid=isValid(params);
			if(!isvalid) {
				//유효성 체크 오류 발생시 전달할 메시지 + 이동될 경로			
				req.setAttribute("msg", msg);
				req.getRequestDispatcher("/WEB-INF/view/main.jsp").forward(req, resp);
				return ;
			}
			
			//3 Service
			Criteria criteria=null;
			if(params.get("pageno")==null) //최초 /board/list.do 접근
			{
				criteria = new Criteria(); //pageno=1 , amount=10
			}
			else
			{
				//페이지이동 요청 했을때
				int pageno = Integer.parseInt(params.get("pageno")[0]);
				criteria = new Criteria(pageno,10);
			}
			boolean result=service.GetBoardList(criteria,req,resp);

			//4 View
			if(result) {		
				req.getRequestDispatcher("/WEB-INF/view/board/list.jsp").forward(req, resp);
			}
		
			
		} catch (Exception e) {
			e.printStackTrace();
		}	
	}
	
	private boolean isValid(Map<String, String[]> params) {
		return true;
	}

	
}
