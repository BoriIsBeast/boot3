package com.iu.boot3.exception;

import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
	@ExceptionHandler(BindException.class)
	public ModelAndView ex1(Exception e) {
		ModelAndView mv = new ModelAndView();
		System.out.println("예외당 :" +e.getMessage());
		
		System.out.println("=================스택트레이스===========");
		
		e.printStackTrace();
		
		System.out.println("예외 발생 처리");
		mv.addObject("msg", "불편 죄송!");
		mv.addObject("path", "../");
		mv.setViewName("common/getResult");
		return mv;
		
	}
	@ExceptionHandler(NullPointerException.class)
	public ModelAndView ex2() {
		ModelAndView mv = new ModelAndView();
		System.out.println("Nullpointer 예외 발생 처리");
		mv.addObject("msg", "불편 죄송!");
		mv.addObject("path", "../");
		mv.setViewName("common/getResult");
		return mv;
		
	}
	@ExceptionHandler(Exception.class)// 익셉션의 상의 카테고리
	public ModelAndView ex3() {
		ModelAndView mv = new ModelAndView();
		System.out.println("Exception 예외 발생 처리");
		mv.setViewName("error/error");
		return mv;
		
	}
	@ExceptionHandler(Throwable.class)//최상위 부모
	public ModelAndView ex4() {
		ModelAndView mv = new ModelAndView();
		System.out.println("Throwable 예외 발생 처리");
		mv.setViewName("error/error");
		return mv;
		
	}
	
	// 400 error
	@ExceptionHandler(NoHandlerFoundException.class)
	public ModelAndView ex5() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("message", "Page 못찾겠어요");
		System.out.println("4XX 예외발생처리");
		mv.setViewName("error/error");
		
		return mv;
	}
	
}
