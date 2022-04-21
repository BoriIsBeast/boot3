package com.iu.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.iu.boot3.util.FileManager;
import com.iu.boot3.util.Pager;

@Service
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private FileManager fileManager;
	
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO)throws Exception{
		
		return boardMapper.getFileDetail(boardFilesVO);
	}
	
	public int setUpdate(BoardVO boardVO)throws Exception{
		return boardMapper.setUpdate(boardVO);
	}
	
	public int setDelete(BoardVO boardVO)throws Exception{
		
		List<BoardFilesVO> ar = boardMapper.listFile(boardVO);
		
		int result = boardMapper.setDelete(boardVO);
		
		if(result>0) {
			
			for(BoardFilesVO vo : ar) {
				
				boolean check = fileManager.remove("resources/upload/board/", vo.getFileName());
			}
			
		}
		
		return result;
	}
	
	public BoardVO getDetail(BoardVO boardVO)throws Exception{
		return boardMapper.getDetail(boardVO);
	}
	
	public List<BoardVO> getList(Pager pager)throws Exception{
		pager.makeRow();
		pager.makeNum(boardMapper.GetTotalCount(pager));
		
		return boardMapper.getList(pager);
	}
	
	public int setAdd(BoardVO boardVO, MultipartFile [] files)throws Exception{
		System.out.println("INSERT 전 :"+boardVO.getNum());
		int result = boardMapper.setAdd(boardVO);
		System.out.println("INSERT 후 :"+boardVO.getNum());
		
		for(MultipartFile mf : files) {
			
			if(mf.isEmpty()) {
				continue;
			}
			
			
			//1. File을 hdd 에 저장
			String fileName = fileManager.filesave(mf, "resources/upload/board/");
			//2. 저장된 정보를 DB에 저장
			BoardFilesVO boardFilesVO = new BoardFilesVO();
			boardFilesVO.setNum(boardVO.getNum());
			
			boardFilesVO.setFileName(fileName);
			boardFilesVO.setOriName(mf.getOriginalFilename());
			boardMapper.setFileAdd(boardFilesVO);
		}
		
		return result;// 
	}
}
