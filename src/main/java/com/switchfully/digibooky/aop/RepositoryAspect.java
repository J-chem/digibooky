package com.switchfully.digibooky.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class RepositoryAspect {
    private final Logger logger = LoggerFactory.getLogger(RepositoryAspect.class);

    @Pointcut("execution(* com.switchfully.digibooky.repositories.*.get*(..))")
    public void allDataRetrievalRepos() {};

    @AfterThrowing(
            pointcut = "allDataRetrievalRepos()",
            throwing ="exception" )
    public void log(JoinPoint joinPoint, Throwable exception) {
        String message = exception.getMessage();
        String method = joinPoint.getSignature().toString();
        logger.warn(message.concat("\nThis for the method: ").concat(method));
    }


}
