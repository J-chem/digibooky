package com.switchfully.digibooky.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;


@Aspect
@Component
public class RepositoryAspect {
    private final Logger logger = LoggerFactory.getLogger(Repository.class);

    @AfterThrowing(
            pointcut = "execution(* com.switchfully.digibooky.aop.PointCutters.allDataRetrievalRepos())",
            throwing ="exception" )
    public void log(Throwable exception) {
        String message = exception.getMessage();
        logger.warn(message);
    }
}
