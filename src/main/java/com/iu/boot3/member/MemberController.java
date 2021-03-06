package com.iu.boot3.member;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
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
	
	@PostMapping("findId")
	public ModelAndView getFindId(MemberVO memberVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		memberVO = memberService.getFindId(memberVO);
		mv.addObject("idResult", memberVO);
		mv.setViewName("member/findIdResult");
		
		
		return mv;
	}
	
	//아이디 찾기
	@GetMapping("findId")
	public ModelAndView getFindId()throws Exception{
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("member/findId");
		
		
		return mv;
	}
	
	
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
	public ModelAndView getLogin(MemberVO memberVO, HttpSession session)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		/*
		 * if(bindingResult.hasErrors()) { System.out.println("로그인성공 : "
		 * +memberVO.getId()); mv.setViewName("member/login"); return mv; }
		 */
		memberVO = memberService.getLogin(memberVO);
		
		mv.setViewName("member/login");
		if(memberVO != null) {
			session.setAttribute("member", memberVO);
			mv.setViewName("redirect:../");
		}
		return mv;
		
	}
	
	
	
	
	@GetMapping("login")
	public ModelAndView getLogin(@ModelAttribute MemberVO memberVO)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		//mv.addObject("vo", new MemberVO());
		
		mv.setViewName("member/login");
		return mv;
		
	}
	
	@PostMapping("join")
	public ModelAndView setJoin(@Valid MemberVO memberVO,BindingResult bindingResult, MultipartFile file)throws Exception{
		ModelAndView mv = new ModelAndView();
		
		/*
		 * if(bindingResult.hasErrors()) { mv.setViewName("member/join"); return mv; }
		 */		
		
		//사용자 정의 검증 메서드 호출
		if(memberService.memberError(memberVO, bindingResult)) {
			 mv.setViewName("member/join"); 
			 return mv; 
		}
		
		int result = memberService.setJoin(memberVO,file);
		
		mv.setViewName("redirect:../");
		
		return mv;
	}
	
	@GetMapping("join")
	public void setJoin(@ModelAttribute MemberVO memberVO)throws Exception{
		
		
	}

}
