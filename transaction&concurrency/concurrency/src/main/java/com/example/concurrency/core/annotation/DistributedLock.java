package com.example.concurrency.core.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.concurrent.TimeUnit;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributedLock {

    String lockKey();

    boolean parser() default false;

    TimeUnit timeUnit() default TimeUnit.SECONDS;

    long waitTime() default 25L;

    long leaseTime() default 20L;
}