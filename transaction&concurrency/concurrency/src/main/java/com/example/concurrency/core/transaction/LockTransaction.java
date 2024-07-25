package com.example.concurrency.core.transaction;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
public class LockTransaction {

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Object proceed(ProceedingJoinPoint point) throws Throwable {
        return point.proceed();
    }
}
