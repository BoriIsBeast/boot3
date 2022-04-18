package com.iu.boot3;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.iu.boot3.board.BoardFilesVO;
import com.iu.boot3.board.BoardMapper;
import com.iu.boot3.board.BoardVO;
import com.iu.boot3.util.Pager;
@SpringBootTest
class BoardMapperTest {
	
	@Autowired
	private BoardMapper boardMapper;
	
	//@Test
	void setFileAddTest()throws Exception{
		BoardFilesVO boardFilesVO = new BoardFilesVO();
		boardFilesVO.setFileName("fileName");
		boardFilesVO.setOriName("oriName");
		boardFilesVO.setNum(3L);
		
		int result = boardMapper.setFileAdd(boardFilesVO);
		
		assertEquals(1, result);
	}
	
	//@Test
	void deleteTest()throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(4L);
		int result = boardMapper.setDelete(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	void updateTest()throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("Title Update");
		boardVO.setContents("Contents Update");
		boardVO.setNum(3L);
		int result = boardMapper.setUpdate(boardVO);
		assertEquals(1, result);
	}
	
	//@Test
	void addTest() throws Exception{
		
		for(int i = 0; i<100;i++) {
		if(i%10 == 0) {
			Thread.sleep(1000);
		}
			
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("Add Title"+i);
		boardVO.setWriter("Add Writer"+i);
		boardVO.setContents("Add Contents"+i);
		int result = boardMapper.setAdd(boardVO);
		}
		
		System.out.println("Finish");
		//assertEquals(1, result);
		
	}
	
	
	@Test
	void listTest()throws Exception{
		Pager pager = new Pager();
		pager.makeRow();
		
		
		List<BoardVO> ar = boardMapper.getList(pager);
		System.out.println(ar);
		assertEquals(10, ar.size());
	}
	//@Test
	void test() throws Exception {
		BoardVO boardVO = new BoardVO();
		boardVO.setNum(3L);
		boardVO = boardMapper.getDetail(boardVO);
		System.out.println(boardVO.toString());
		assertNotNull(boardVO);
	}
	
	
	
	
	

}
