package com.example.concurrency.core.aspect;

import com.example.concurrency.core.annotation.DistributedLock;
import com.example.concurrency.core.parser.CustomSpringELParser;
import com.example.concurrency.core.transaction.LockTransaction;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class DistributedLockAspect {

    private final RedissonClient redissonClient;

    private final LockTransaction lockTransaction;

    @Around("@annotation(distributedLock)")
    public Object distributedLock(ProceedingJoinPoint point, DistributedLock distributedLock) throws Throwable {

        MethodSignature signature = (MethodSignature) point.getSignature();
        String lockName = "LOCK:";

        if(distributedLock.parser()){
            lockName += CustomSpringELParser.getDynamicValue(signature.getParameterNames(), point.getArgs(), distributedLock.lockKey());
        } else {
            lockName += distributedLock.lockKey();
        }

        RLock lock = redissonClient.getLock(lockName);

        try {
            boolean available = lock.tryLock(distributedLock.waitTime(), distributedLock.leaseTime(), distributedLock.timeUnit());

            if(!available){
                throw new RuntimeException("lock key를 얻지 못했습니다. >>" + lockName);
            }
            return lockTransaction.proceed(point);

        } catch (Exception e){
            throw new RuntimeException(e);
        } finally {
            try {
                if(lock.isHeldByCurrentThread()){
                    lock.unlock();
                }
            } catch (Exception e) {
            }
        }
    }


}
