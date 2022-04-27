package com.iu.boot3.aop;


import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class Payment {
	
	@Around("execution(* com.iu.boot3.aop.Transfer.*())")
	public Object cardCheck(ProceedingJoinPoint joinPoint) throws Throwable {
		//join point 핵심 로직메서드 (bus, subway)
		System.out.println("탑승전 카드 체크");
		
		Object obj = joinPoint.proceed();
		//obj 는 핵심 로직 메서드가 리턴하는 Data
		System.out.println("하차시 카드 체크");
		
		return obj;
	}
	
	@Before("execution(* com.iu.boot3.board.BoardService.get*(*))")
	public void before() {
		System.out.println("==== SELECT =========");
	}
	
	
	
	@AfterReturning("execution(* com.iu.boot3.aop.Transfer.*())")
	public void afterReturngning() {
		System.out.println("AfterReturning");
	}
	
	@AfterThrowing("execution(* com.iu.boot3.aop.Transfer.*())")
	public void afterThrowing() {
		System.out.println("AfterThrowing");
	}
	
	@After("execution(* com.iu.boot3.board.BoardService.get*(*))")
	public void after() {
		System.out.println("afterReturning + AfterThrowing");
	}
	
	/*
	 * @Around("execution(* com.iu.boot3.board.BoardService.get*(*))") public void
	 * around() { System.out.println("Before + After"); }
	 */
}
