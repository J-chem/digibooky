package com.switchfully.digibooky.aop;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ServiceAspect {

    //een pointcut
    @Pointcut("")
    public void logSecurityMisuse() {}

    //   logger.error("Unknown user" + usernamePassword.getUsername());

    // logger.error("Password does not match for user " + usernamePassword.getUsername());

   // logger.error("User " + usernamePassword.getUsername() + " does not have access to " + feature);

}
