package com.iu.boot3.interceptor;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.iu.boot3.board.BoardMapper;
import com.iu.boot3.board.BoardVO;
import com.iu.boot3.member.MemberVO;

@Component
public class WriterCheckInterceptor implements HandlerInterceptor {
	
	@Autowired
	private BoardMapper boardMapper;
	
	/*
	 * @Override public void postHandle(HttpServletRequest request,
	 * HttpServletResponse response, Object handler, ModelAndView modelAndView)
	 * throws Exception { // TODO Auto-generated method stub
	 * 
	 * String method = request.getMethod(); System.out.println(method);
	 * 
	 * MemberVO memberVO = (MemberVO)request.getSession().getAttribute("member");
	 * 
	 * Map<String, Object> map= modelAndView.getModel(); BoardVO boardVO =
	 * (BoardVO)map.get("vo");
	 * 
	 * if(!boardVO.getWriter().equals(memberVO.getId())) {
	 * //modelAndView.setViewName("redirect:./list"); modelAndView.addObject("msg",
	 * "작성자만 가능합니다"); modelAndView.addObject("path", "./list");
	 * modelAndView.setViewName("common/getResult"); }
	 * 
	 * }
	 */
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		
		String num =request.getParameter("num");
		
		BoardVO boardVO = new BoardVO();
		
		
		Long a = Long.parseLong(num);
		
		boardVO.setNum(a);
		
		boardVO = boardMapper.getDetail(boardVO);
		
		//세션에서 가져오기
		HttpSession session = request.getSession();
		MemberVO memberVO =(MemberVO)session.getAttribute("member");
		
		String result = boardVO.getWriter();
		
		//boolean check = false;
		
		
		//작성자와 로그인한 사용자의 id가 일치하면 통과
		if(memberVO.getId().equals(result)) {
			
			//check = true;
			
			return true;
			
			
		}else  {
			//일치하지 않으면 list로 redirect
			response.sendRedirect("./list");
			return false;
		}
		
	}
	 
	

}
