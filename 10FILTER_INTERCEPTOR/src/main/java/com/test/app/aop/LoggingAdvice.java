package com.test.app.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
	
	@Before("execution(* com.test.app.domain.service.SimpleService.get1())")
	public void logBefore(JoinPoint joinPoint) {
		System.out.println("BEFORE..." +joinPoint );
	}
	@After("execution(* com.test.app.domain.service.SimpleService.get2())")
	public void logAfter(JoinPoint joinPoint) {
		System.out.println("AFTER..." +joinPoint.getTarget() );
	}
	@Around("execution(* com.test.app.domain.service.SimpleService.get3())")
	public void logAround(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("[AOP] Start : "+pjp.getSignature().getName());
		Object result = pjp.proceed();	//대상 타겟함수(get3())을 실행
		System.out.println("[AOP] Result : "+result);
		long end = System.currentTimeMillis();
		System.out.println("[AOP] Time : " +(end-start)+"ms");
		System.out.println("[AOP] End : " + end);
	}
	@Around("execution(void com.test.app.domain.service.*.*(..))")
	public Object logAroundAll(ProceedingJoinPoint pjp) throws Throwable {
		long start = System.currentTimeMillis();
		System.out.println("[AOP] Start : "+pjp.getSignature().getName());
		Object result = pjp.proceed();	//대상 타겟함수(get3())을 실행
		System.out.println("[AOP] Result : "+result);
		long end = System.currentTimeMillis();
		System.out.println("[AOP] Time : " +(end-start)+"ms");
		System.out.println("[AOP] End : " + end);
		
		return result;
	}
}
