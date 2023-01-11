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
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 10, // 10MB 업로드 파일이 10MB이상이 될때 임시경로(temp)에 저장
		maxFileSize = 1024 * 1024 * 50, // 50MB 업로드 파일의 최대 크기
		maxRequestSize = 1024 * 1024 * 100 // 100MB request전체의 크기
)
public class FrontController extends HttpServlet {

	private Map<String, SubController> list;

	@Override
	public void init(ServletConfig config) throws ServletException {
		String path = config.getServletContext().getContextPath(); // /02Mvc2 경로 저장
		System.out.println("[FC] CONTEXT PATH : " + path);
		list = new HashMap();

		// URL ,SubController 저장
		// Member
		list.put(path + "/member/join.do", new MemberJoinController());

		// Board
		list.put(path + "/board/list.do", new BoardListController());
		list.put(path + "/board/post.do", new BoardPostController());
		list.put(path + "/board/read.do", new BoardReadController());
		list.put(path + "/board/download.do", new BoardDownloadController());
		list.put(path + "/board/downloadzip.do", new BoardDownloadzipController());

		// Notice
		list.put(path + "/notice/list.do", new NoticeListController());
		list.put(path + "/notice/post.do", new NoticePostController());

		// Auth
		list.put(path + "/auth/login.do", new LoginController());
		list.put(path + "/auth/logout.do", new LogoutController());

		// Main
		list.put(path + "/main.do", new MainController());

	}

	@Override
	protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// 문자셋 설정(Filter 이동예정)
		req.setCharacterEncoding("UTF-8");
		resp.setContentType("text/html; charset=UTF-8");

		// SubController실행
		String uri = req.getRequestURI();
		System.out.println("[FC] URI : " + uri);
		SubController sub = list.get(uri);
		sub.execute(req, resp);

	}

}
