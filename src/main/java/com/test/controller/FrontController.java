package com.test.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//파일 업로드 정보
@MultipartConfig
(
		fileSizeThreshold=1024*1024*10,	// 10MB 업로드 파일이 10Mb이상이 될때 임시경로(temp)에 저장
		maxFileSize=1024*1024*50,		// 50MB  업로드 파일의 최대 크기 
		maxRequestSize=1024*1024*100	// 100MB request전체의 크기
)
public class FrontController extends HttpServlet{

	private Map<String,SubController> list;	//url정보를 받아오는 작업
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		String path=config.getServletContext().getContextPath(); // /02Mvc2 경로 저장
		System.out.println("[FC] CONTEXT PATH : " + path);	//경로 확인
		list = new HashMap();	//list를 HashMap으로 생성
		
		//URL ,SubController 저장
		//Member
		list.put(path+"/member/join.do", new MemberJoinController());
		
		//Board
		list.put(path+"/board/list.do", new BoardListController());
		list.put(path+"/board/post.do", new BoardPostController());
		list.put(path+"/board/read.do", new BoardReadController());
		list.put(path+"/board/download.do", new BoardDownloadController());
		list.put(path+"/board/downloadzip.do", new BoardDownloadzipController());
		list.put(path+"/board/replypost.do", new BoardReplyPostController());
		list.put(path+"/board/replylist.do", new BoardReplyListController());
		list.put(path+"/board/replycnt.do", new BoardReplyCntController());
		
		//Board 수정,삭제
		list.put(path+"/board/update.do", new BoardUpdateController());
		list.put(path+"/board/delete.do", new BoardDeleteController());
		
		
		//Notice
		list.put(path+"/notice/list.do", new NoticeListController());
		list.put(path+"/notice/post.do", new NoticePostController());

		//Auth
		list.put(path+"/auth/login.do", new LoginController());
		list.put(path+"/auth/logout.do", new LogoutController());
		
		//Main
		list.put(path+"/main.do", new MainController());
		
		
	}
	
	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//문자셋 설정
		req.setCharacterEncoding("UTF-8");	//한글문자가 깨지지 않기위해 UTF-8로 설정
		resp.setContentType("text/html; charset=UTF-8");
		
		//SubController실행
		String uri = req.getRequestURI();	//자원정보 경로를 받아오기
		System.out.println("[FC] URI : " + uri);	//URI정보 확인
		SubController sub = list.get(uri);	//URI정보 SubController로 받기
		sub.execute(req, resp);	//실행
		 
	}

	
 
	
}
