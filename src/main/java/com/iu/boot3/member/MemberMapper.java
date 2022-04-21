package com.iu.boot3.member;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.web.multipart.MultipartFile;

@Mapper
public interface MemberMapper {
	//파일 조회
	public MemberFilesVO getFileDetail(MemberVO memberVO)throws Exception;
	
	//파일추가
	public int setFileAdd(MemberFilesVO memberFilesVO)throws Exception;
	
	//mypage
	public MemberVO getMyPage(MemberVO memberVO)throws Exception;
	//login
	public MemberVO getLogin(MemberVO memberVO)throws Exception;
	//join
	public int setJoin(MemberVO memberVO)throws Exception;
	//update
	public int setUpdate(MemberVO memberVO)throws Exception;
	//delete
	public int setDelete(MemberVO memberVO)throws Exception;
}
