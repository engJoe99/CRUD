package com.example.luv2code.mvc.advancedcrud.aspect;


import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // Initialize a logger instance for this class to enable logging.
    // The logger is named after the fully qualified class name
    // (e.g., "com.example.luv2code.mvc.advancedcrud.aspect.DemoLoggingAspect"),
    // which helps in organizing and filtering log messages specific to this class.
    private Logger myLogger = Logger.getLogger(getClass().getName());



    // setup pointcut declarations

    // This aspect defines pointcuts for logging in a Spring application.
    // It specifies three pointcuts: one for controller methods, one for service methods,
    // and one for repository methods within the specified package structure.

    @Pointcut("execution(* com.example.luv2code.mvc.advancedcrud.controller.*.*(..))")
    private void forControllerPackage() {}


    @Pointcut("execution(* com.example.luv2code.mvc.advancedcrud.services.*.*(..))")
    private void forServicePackage() {}


    @Pointcut("execution(* com.example.luv2code.mvc.advancedcrud.repository.*.*(..))")
    private void forRepositoryPackage() {}


    @Pointcut("forControllerPackage() || forServicePackage() || forRepositoryPackage()")
    private void forAppFlow() {}



    // add @Before Advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint) {

        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("===>>> in @Before: calling method: " + theMethod);


        // display the arguments of the method
        // get the arguments
        Object[] args = theJoinPoint.getArgs();

        // loop through then display args
        for (Object tempArg : args) {
            System.out.println("===>>> arguments: " + tempArg);
        }
    }



    // add @AfterReturning Advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult")
    public void afterReturning(JoinPoint theJoinPoint, Object theResult) {

        // display the method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        System.out.println("===>>> in @AfterReturning: from method: " + theMethod);

        // display data returned
        System.out.println("===>>> result: " + theResult);
    }











}
