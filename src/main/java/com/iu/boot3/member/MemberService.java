package com.iu.boot3.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.boot3.util.FileManager;

@Service
public class MemberService {
	
	@Autowired
	private MemberMapper memberMapper;
	@Autowired
	private FileManager fileManager;
	
	//properties파일의 member.role.member 속성값 반환
	@Value("${member.role.member}")
	private String memberRole;
	
	//mypage
	public MemberVO getMypage(MemberVO memberVO)throws Exception{
		
		return memberMapper.getMyPage(memberVO);
	}
	//login
	public MemberVO getLogin(MemberVO memberVO)throws Exception{
		
		return memberMapper.getLogin(memberVO);
	}
	//update
	public int setUpdate(MemberVO memberVO)throws Exception{
		
		return memberMapper.setUpdate(memberVO);
	}
	
	//delete
	public int setDelete(MemberVO memberVO)throws Exception{
		MemberFilesVO memberFilesVO = memberMapper.getFileDetail(memberVO);
		int result = memberMapper.setDelete(memberVO);
		
		if(result >0) {
			boolean check = fileManager.remove("resources/upload/member", memberFilesVO.getFileName());
		}
		
		return result;
	}
	
	//join
	public int setJoin(MemberVO memberVO, MultipartFile file)throws Exception{
		
		int result = memberMapper.setJoin(memberVO);
		
		Map<String, String> map = new HashMap<>();
		map.put("id", memberVO.getId());
		map.put("roleName", memberRole);
		
		result = memberMapper.setRoleAdd(map);
			
		//MEMBERROLE TABLE INSERT 
		
		
		String fileName = fileManager.filesave(file, "resources/upload/member/");
		
		MemberFilesVO memberFilesVO = new MemberFilesVO();
		memberFilesVO.setId(memberVO.getId());
		memberFilesVO.setFileName(fileName);
		memberFilesVO.setOriName(file.getOriginalFilename());
		memberMapper.setFileAdd(memberFilesVO);
				
		
		
		return result;
	}
}
