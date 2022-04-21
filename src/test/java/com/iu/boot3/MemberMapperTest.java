package com.iu.boot3;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.boot3.member.MemberMapper;
import com.iu.boot3.member.MemberVO;
@SpringBootTest
class MemberMapperTest {
	
	@Autowired
	private MemberMapper memberMapper;
	
	@Test
	void DeleteTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("Test ID");
		int result =memberMapper.setDelete(memberVO);
		
		assertEquals(1, result);
	}
	
	//@Test
	void MypageTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("ID");
		memberVO = memberMapper.getMyPage(memberVO);
		
		assertNotNull(memberVO);
	}
	
	//@Test
	void JoinTest() throws Exception {
		MemberVO memberVO = new MemberVO();
		memberVO.setId("ID");
		memberVO.setPw("PW");
		memberVO.setName("이응");
		memberVO.setEmail("wlsdn123@naver.com");
		memberVO.setPhone("010-1111-1111");
		
		int result = memberMapper.setJoin(memberVO);
		
		assertEquals(1, result);
		
	}
	
	//@Test
	void LoginTest() throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setId("ID");
		memberVO.setPw("PW");
		
		memberVO = memberMapper.getLogin(memberVO);
		
		assertNotNull(memberVO);
		
	}
	
	//@Test
	void UpdateTest()throws Exception{
		MemberVO memberVO = new MemberVO();
		memberVO.setName("update name");
		memberVO.setEmail("update email");
		memberVO.setPhone("010-2222-2222");
		memberVO.setId("");
	}

}
