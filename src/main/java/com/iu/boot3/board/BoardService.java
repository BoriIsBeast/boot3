package com.iu.boot3.board;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.iu.boot3.util.FileManager;
import com.iu.boot3.util.Pager;

@Service
@Transactional(rollbackFor = Exception.class)
public class BoardService {
	@Autowired
	private BoardMapper boardMapper;
	@Autowired
	private FileManager fileManager;
	
	public boolean setSummerFileDelete(String fileName)throws Exception{
		fileName = fileName.substring(fileName.lastIndexOf("/")+1);
		System.out.println(fileName);
		
		return fileManager.remove("/resources/upload/board/",fileName);
	}
	
	public String setSummberFileUpload(MultipartFile files)throws Exception{
		//file HDD에 저장하고 저장된 파일명을 RETURN
		String fileName = fileManager.filesave(files, "resources/upload/board");
		fileName = "/resources/upload/board/"+fileName;
		
		return fileName;
	}
	
	public BoardFilesVO getFileDetail(BoardFilesVO boardFilesVO)throws Exception{
		
		return boardMapper.getFileDetail(boardFilesVO);
	}
	
	public int setFileDelete(BoardFilesVO boardFilesVO)throws Exception{
		
		boardFilesVO = boardMapper.getFileDetail(boardFilesVO);
		
		int check = boardMapper.setFileDelete(boardFilesVO);
		
		if(check>0) {
			boolean result = fileManager.remove("/resources/upload/board/", boardFilesVO.getFileName());
		}
		
		return check;
	}
	
	public int setUpdate(BoardVO boardVO, MultipartFile [] multipartFiles)throws Exception{
		int result =  boardMapper.setUpdate(boardVO);
		
		if(multipartFiles != null) {
			
			for(MultipartFile multipartFile : multipartFiles) {
				if(multipartFile.isEmpty()) {
					continue;
				}
				BoardFilesVO boardFilesVO = new BoardFilesVO();
				String fileName = fileManager.filesave(multipartFile, "/resources/upload/board/");
				boardFilesVO.setFileName(fileName);
				boardFilesVO.setOriName(multipartFile.getOriginalFilename());
				boardFilesVO.setNum(boardVO.getNum());
				result = boardMapper.setFileAdd(boardFilesVO);
				
				
			}
			
		}
		
		return result;
		
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
		
		if(files != null && result>0) {
		
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
			result = boardMapper.setFileAdd(boardFilesVO);
			
			if(result<1) {
				//board table insert 한 것도 rollback
				throw new SQLException();
			}
			
			}
		}
		
		return result;// 
	}
}
