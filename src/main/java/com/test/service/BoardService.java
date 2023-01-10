package com.test.service;

import java.io.File;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import com.test.dao.BoardDao;
import com.test.dto.AuthDto;
import com.test.dto.BoardDto;
import com.test.dto.Criteria;
import com.test.dto.PageDto;

public class BoardService {
	
	BoardDao dao =BoardDao.getInstance();
	
	private String uploadDir;
	 
	//싱글톤
	private static BoardService instance;
	public static BoardService getInstance() {
		if(instance==null)
			instance=new BoardService();
		return instance;
	}
	private BoardService() {};
	//싱글톤
	
	//모든 게시물 가져오기
//	public boolean GetBoardList(HttpServletRequest request) {
//		List<BoardDto> list = dao.SelectAll();
//		if(list!=null) {
//			request.setAttribute("list", list);
//			return true;
//		}
//		return false;
//	}
	
	
	//모든 게시물 가져오기
	public boolean GetBoardList(Criteria criteria, HttpServletRequest request) {
		
		//전체게시물 건수 받기
		int totalcount = dao.getAmount();
		
		//PageDto 만들기
		PageDto pagedto = new PageDto(totalcount,criteria);
		
		//시작 게시물 번호 구하기(수정)
		int startno = criteria.getPageno()*criteria.getAmount()-criteria.getAmount();
		
		//PageDto를 Session에 추가
		HttpSession session = request.getSession(false);
		
		List<BoardDto> list = dao.SelectAll(startno,criteria.getAmount());
		if(list!=null) {
			request.setAttribute("list", list);
			session.setAttribute("pagedto", pagedto);
			return true;
		}
		return false;
	}
	
	//게시물 추가하기
//	public boolean PostBoard(BoardDto dto,HttpServletRequest request)
//	{
//		boolean flag=false;
//		
//		int result=dao.Insert(dto);
//		
//		if(result>0)
//			flag=true;
//		
//		return flag;
//	}
	
	//게시물 추가하기(업로드포함)
	public boolean PostBoard(BoardDto dto,HttpServletRequest request)
	{
		boolean flag=false;
		
		//디렉토리 경로 설정
		System.out.println("DIRPATH : " + request.getServletContext().getRealPath("/"));
		uploadDir=request.getServletContext().getRealPath("/")+"upload";
		
		//접속한 Email 계정 확인
		HttpSession session = request.getSession(false);
		AuthDto adto =(AuthDto)session.getAttribute("authdto");
		
		//UUID(랜덤값) 폴더생성
		UUID uuid = UUID.randomUUID();
		String path = uploadDir+File.separator+adto.getEmail()+File.separator+uuid;
		
		//추출된 파일정보 저장용 Buffer
		StringBuffer filelist = new StringBuffer();
		StringBuffer filesize= new StringBuffer();
		
		
		try {
			
			Collection<Part> parts=request.getParts();
			for(Part part : parts)
			{
				if(part.getName().equals("files"))
				{
					System.out.println("-------------------------------------------");
					
//					System.out.println("PART명 : " + part.getName());
//					System.out.println("PART크기 : " + part.getSize());				
//					for(String name : part.getHeaderNames()) {
//						System.out.println("Header Name : " + name);
//						System.out.println("Header Value : " + part.getHeader(name));
//					}
					
					//파일명 추출
					System.out.println("파일명 : " + getFileName(part));
					String filename=getFileName(part);
					
					if(!filename.equals(""))
					{
						
						//폴더생성
						File dir = new File(path);
						if(!dir.exists()) {
							dir.mkdirs();
						}
						//업로드
						part.write(path+File.separator+filename);
						
						//파일명 DB Table에 추가 위한 저장
						filelist.append(filename+";");
						//디렉토리 경로 DB Table에 추가 위한 저장
						filesize.append(part.getSize()+";");
					}
					
					System.out.println("-------------------------------------------");
				}
				
			}	
			
			dto.setFilename(filelist.toString());
			dto.setFilesize(filesize.toString());
			
			//DB연결
			int result=dao.Insert(dto);		
			if(result>0)
				flag=true;
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
		return flag;
	}
	
	
	
	private String getFileName(Part part) {
//		-------------------------------------------
//		PART명 : files
//		PART크기 : 107637
//		Header Name : content-disposition
//		Header Value : form-data; name="files"; filename="aaa.csv"
//		Header Name : content-type
//		Header Value : text/csv
//		-------------------------------------------
		
		String contentDisp = part.getHeader("content-disposition");
		//String contentDisp = form-data; name="files"; filename="aaa.csv"
		
		String[] tokens = contentDisp.split(";");//[form-data,name="files",filename="aaa.csv"]
		String filename = tokens[tokens.length - 1]; //filename="aaa.csv"

		return filename.substring(11,filename.length()-1);
	}
	
	
	
}