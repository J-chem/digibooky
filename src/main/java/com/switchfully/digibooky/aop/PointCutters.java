package com.switchfully.digibooky.aop;

import org.aspectj.lang.annotation.Pointcut;

public class PointCutters {
    @Pointcut("execution(* com.switchfully.digibooky.repositories.*.get*(..))")
    public void allDataRetrievalRepos() {};
}
