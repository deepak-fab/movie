package com.deepak.theatrecatalogueservice.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import com.deepak.theatrecatalogueservice.constants.LoggingConstants;

@Aspect
@Component
public class ProfilerAspect {

	
	private static Logger logger = LoggerFactory.getLogger(ProfilerAspect.class);

	/**
	 * Point cut definition
	 */
	@Pointcut(LoggingConstants.POINT_CUT_BASE_PACKAGE)
	public void profilerPointCut() {
	}

	/**
	 * Profile the method and log the execution time in milliseconds
	 *
	 * @param joinPoint
	 * @return
	 * @throws Throwable
	 */
	@Around(LoggingConstants.PROFILER_POINT_CUT)
	public Object profile(ProceedingJoinPoint joinPoint) throws Throwable {
		boolean isExceptionThrown = false;
		StopWatch stopWatch = new StopWatch();

		// Start the stop watch
		stopWatch.start(joinPoint.toShortString());

		try {
			// Execute the profiled method
			return joinPoint.proceed();
		} catch (RuntimeException e) {
			isExceptionThrown = true;
			throw e;
		} finally {
			// Stop the stop watch
			stopWatch.stop();

			// Log the execution time of the method. The format will be as follows,
			// [PROFILER]
			// com.boeing.bcs.search.controller.BrassBoardController.saveBoostTerms,
			// Execution time: 11ms
			String logMessage = String.format(LoggingConstants.LOG_MESSAGE_FORMAT, joinPoint.getTarget().getClass().getName(),
					joinPoint.getSignature().getName(), stopWatch.getLastTaskInfo().getTimeMillis(),
					isExceptionThrown ? " (thrown Exception)" : "");
			logger.info(logMessage);
		}
	}
}
