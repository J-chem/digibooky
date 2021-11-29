package com.switchfully.digibooky;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class DigibookyApplication {

    public static void main(String[] args) {
        SpringApplication.run(DigibookyApplication.class, args);
    }
}
