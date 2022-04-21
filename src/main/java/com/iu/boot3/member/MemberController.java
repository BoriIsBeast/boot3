package com.iu.boot3.member;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.iu.boot3.board.BoardVO;

@Controller
@RequestMapping("member/*")
public class MemberController {
	
	@Autowired
	private MemberService memberService;
	
	@PostMapping("update")
	public ModelAndView setUpdate(MemberVO memberVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setUpdate(memberVO);
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("update")
	public ModelAndView setUpdate(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.addObject("vo", memberVO);
		
		return mv;
	}
	
	@GetMapping("mypage")
	public ModelAndView mypage(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		MemberVO memberVO = (MemberVO)session.getAttribute("member");
		memberVO = memberService.getMypage(memberVO);
		mv.setViewName("member/mypage");
		mv.addObject("vo", memberVO);
		
		return mv;
	}
	@GetMapping("delete")
	public ModelAndView setDelete(HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		MemberVO memberVO = (MemberVO) session.getAttribute("member");
		int result = memberService.setDelete(memberVO);
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("logout")
	public String logout(HttpSession session)throws Exception{
		session.invalidate();
		
		return "redirect:../";
	}
	
	@PostMapping("login")
	public ModelAndView getLogin(MemberVO memberVO,HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getLogin(memberVO);
		
		mv.setViewName("member/login");
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		return mv;
		
	}
	
	
	
	
	@GetMapping("login")
	public ModelAndView getLogin()throws Exception{
		ModelAndView mv = new ModelAndView();
		mv.setViewName("member/login");
		return mv;
		
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(MemberVO memberVO, MultipartFile file)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		int result = memberService.setJoin(memberVO,file);
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("join")
	public void setJoin()throws Exception{
		
		
	}

}
