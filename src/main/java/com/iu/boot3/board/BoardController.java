package com.iu.boot3.board;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.boot3.util.Pager;

@Controller
@RequestMapping("/board/*")
public class BoardController {

	@Autowired
	private BoardService boardService;
	
	@ModelAttribute("board")
	public String getBoard() {
		return "board";
	}
	
	@GetMapping("summerFileDelete")
	public ModelAndView setSummerFileDelete(String fileName)throws Exception{
		ModelAndView mv = new ModelAndView();
		boolean result =boardService.setSummerFileDelete(fileName);
		mv.setViewName("common/result");
		mv.addObject("result", result);
		return mv;
	}
	
	
	@PostMapping("summerFileUpload")
	public ModelAndView setSummerFileUpload(MultipartFile files) throws Exception {
		ModelAndView mv = new ModelAndView();
		String fileName = boardService.setSummberFileUpload(files);
		System.out.println(fileName);
		mv.setViewName("common/result");
		mv.addObject("result", fileName);
		
		return mv;
	}
	
	@GetMapping("fileDown")
	public ModelAndView getFileDown(BoardFilesVO boardFilesVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		boardFilesVO = boardService.getFileDetail(boardFilesVO);
		//속성명은 fileDown 클래스에서 사용하는 이름과 동일하게
		mv.addObject("fileVO",boardFilesVO);
		
		//Bean(클래스)의 이름과 동일하게 
		mv.setViewName("fileDown");
		
		return mv;
		
	}
	
	@GetMapping("delete")
	public ModelAndView setDelete(BoardVO boardVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = boardService.setDelete(boardVO);
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	
	@PostMapping("fileDelete")
	public ModelAndView setFileDelete(BoardFilesVO boardFilesVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = boardService.setFileDelete(boardFilesVO);
		
		mv.setViewName("common/result");
		mv.addObject("result", result);
		
		return mv;
	}
	
	@PostMapping("update")
	public ModelAndView setUpdate(BoardVO boardVO, MultipartFile[] files)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		for(int i = 0; i<files.length;i++) {
			System.out.println(files[i].getOriginalFilename());
		}
		
		int result = boardService.setUpdate(boardVO, files);
		if(result>0) {
			mv.setViewName("redirect:./list");
		}else {
			mv.setViewName("common/getResult");
			mv.addObject("msg", "Update 실패");
			mv.addObject("path", "./detail?num="+boardVO.getNum());
		}
		
		
		
		return mv;
	}
	
	@GetMapping("update")
	public void setUpdate(BoardVO boardVO,Model model)throws Exception{
		
		
		boardVO = boardService.getDetail(boardVO);
		model.addAttribute("vo", boardVO);
		
		
	}
	
	@GetMapping("detail")
	public ModelAndView getDetail(BoardVO boardVO, Model model)throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("board/detail");
		boardVO = boardService.getDetail(boardVO);
		mv.addObject("vo", boardVO);
		
		return mv;
	}
	
	@GetMapping("list")
	public ModelAndView getList(Pager pager)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		List<BoardVO> ar = boardService.getList(pager);
		mv.addObject("list", ar);
		mv.addObject("pager", pager);
		mv.setViewName("board/list");
		
		return mv;
	}
	
	@PostMapping("add")
	public ModelAndView setAdd(BoardVO boardVO, MultipartFile [] files)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//업로드시 파일명을출력
//		for(MultipartFile f : files) {
//			System.out.println(f.getOriginalFilename());
//		}
		
		
		int result = boardService.setAdd(boardVO,files);
		
		mv.setViewName("redirect:./list");
		
		return mv;
	}
	@GetMapping("add")
	public void setAdd()throws Exception{
		
		
	}
}
