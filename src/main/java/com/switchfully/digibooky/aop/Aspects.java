package com.switchfully.digibooky.aop;

import com.switchfully.digibooky.repositories.BookRepository;
import com.switchfully.digibooky.repositories.validators.Validator;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class Aspects {
    private final Logger logger = LoggerFactory.getLogger(org.aspectj.lang.annotation.Aspect.class);

    @Pointcut("execution(* com.switchfully.digibooky.*.*.*(..))")
    public void allDataRetrievalRepos() {};

    @AfterThrowing( pointcut = "allDataRetrievalRepos()",
                    throwing ="exception" )
    public void log(JoinPoint joinPoint, Throwable exception) {
        String message = exception.getMessage();
        String method = joinPoint.getSignature().toString();
        logger.warn(message.concat("This for the method: ").concat(method));
    }
}
