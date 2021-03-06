package com.iu.boot3.member;

import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface MemberMapper {
	
	public MemberVO getId(MemberVO memberVO)throws Exception;
	
	//아이디 조회
	public MemberVO getFindId(MemberVO memberVO)throws Exception;
	
	//파일 조회
	public MemberFilesVO getFileDetail(MemberVO memberVO)throws Exception;
	
	//파일추가
	public int setFileAdd(MemberFilesVO memberFilesVO)throws Exception;
	
	//mypage
	public MemberVO getMyPage(MemberVO memberVO)throws Exception;
	//login
	public MemberVO getLogin(MemberVO memberVO)throws Exception;
	//Role
	public int setRoleAdd(Map<String, String> map)throws Exception;
	//join
	public int setJoin(MemberVO memberVO)throws Exception;
	//update
	public int setUpdate(MemberVO memberVO)throws Exception;
	//delete
	public int setDelete(MemberVO memberVO)throws Exception;
}
