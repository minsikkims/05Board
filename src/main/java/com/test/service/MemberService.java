package com.test.service;

import org.mindrot.bcrypt.BCrypt;

import com.test.dao.MemberDao;
import com.test.dto.MemberDto;

public class MemberService {
	
	MemberDao dao =MemberDao.getInstance();
	BCrypt bc = new BCrypt();
	
	//싱글톤
	private static MemberService instance;
	public static MemberService getInstance() {
		if(instance==null)
			instance=new MemberService();
		return instance;
	}
	private MemberService() {};
	//싱글톤
	
	//회원가입
	public boolean memberJoin(MemberDto dto) {
		boolean flag=false;
		//pwd를 해시화하여 저장 작업
		dto.setPwd(bc.hashpw(dto.getPwd() , bc.gensalt() ) );
		
		int result = dao.Insert(dto);
		if(result>0)
			flag=true;
		
		return flag;
	}
	
	
	
}
