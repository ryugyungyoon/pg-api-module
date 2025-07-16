package com.rc.pgapimodule.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class PGLoggingAspect {

	@Pointcut("execution(* com.rc.pgapimodule.gateway..*(..))")
	public void pgGatewayMethods() {}

	@Before("pgGatewayMethods()")
	public void logBefore(JoinPoint joinPoint) {
		log.info("[PG REQUEST] {}({})", joinPoint.getSignature().toShortString(), joinPoint.getArgs());
	}

	@AfterReturning(pointcut = "pgGatewayMethods()", returning = "result")
	public void logAfter(JoinPoint joinPoint, Object result) {
		log.info("[PG RESPONSE] {} => {}", joinPoint.getSignature().toShortString(), result);
	}
}
