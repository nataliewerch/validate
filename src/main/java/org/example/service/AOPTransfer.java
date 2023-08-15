package org.example.service;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AOPTransfer {

    @Around(value = "execution(* org.example.service.TransferService.transfer(..))")
    public Object advice(ProceedingJoinPoint joinPoint) throws Throwable {
        Object returnValue = null;
        System.out.println("Start Transaction " + joinPoint);
        try {
            returnValue = joinPoint.proceed();
            System.out.println("Transaction succeeded " + joinPoint);
        } catch (IllegalArgumentException e) {
            System.out.println("Transaction failed " + e.getMessage());
        }
        return returnValue;
    }
}
