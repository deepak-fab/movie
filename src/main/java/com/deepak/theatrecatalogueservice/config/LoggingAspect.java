package com.deepak.theatrecatalogueservice.config;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.deepak.theatrecatalogueservice.constants.LoggingConstants;



@Aspect
@Component
public class LoggingAspect {

	private static Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

	@Before(LoggingConstants.LOG_ALL_METHODS)
	public void logBeforeMethod(JoinPoint joinPoint) {
		
		logger.info(LoggingConstants.METHOD_ENTRY_LOG + joinPoint.getSignature().getDeclaringType() + LoggingConstants.METHOD_LOG_SEPARATOR
				+ joinPoint.getSignature().getName());
	}

	@After(LoggingConstants.LOG_ALL_METHODS)
	public void logAfterMethod(JoinPoint joinPoint) {
		logger.info(LoggingConstants.METHOD_EXIT_LOG  + joinPoint.getSignature().getDeclaringType() + LoggingConstants.METHOD_LOG_SEPARATOR
				+ joinPoint.getSignature().getName());
	}

	@Pointcut(LoggingConstants.POINT_CUT_BASE_PACKAGE)
	public void allMethodsPointcut() {
	}
}
