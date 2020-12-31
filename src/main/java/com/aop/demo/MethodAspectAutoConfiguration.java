package com.aop.demo;

import java.lang.reflect.Method;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
public class MethodAspectAutoConfiguration {

	@Component
	@Aspect
	public static class MethodAspect{
		@Around(value="execution(* *Service(..))")
	    public Object around(ProceedingJoinPoint joinPoint)throws Throwable{
	        Signature signature = joinPoint.getSignature();
	        MethodSignature methodSignature = (MethodSignature) signature;
	        Method method = methodSignature.getMethod();

	        long st = System.currentTimeMillis();
	        Object obj = null ;
	        try {
	            obj = joinPoint.proceed();
	        }catch(Throwable e) {
	            throw e ;
	        }finally{
	            long ed = System.currentTimeMillis();
	            System.out.printf("execute method %s, time: %d ms.",method.getName(),(ed-st)) ;
	        }
	        return obj ;
	    }
	}
	
}
