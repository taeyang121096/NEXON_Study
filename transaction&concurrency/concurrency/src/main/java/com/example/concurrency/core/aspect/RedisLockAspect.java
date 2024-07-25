package com.example.concurrency.core.aspect;

import com.example.concurrency.core.annotation.RedisLock;
import com.example.concurrency.core.parser.CustomSpringELParser;
import com.example.concurrency.core.redis.repo.RedisRepository;
import com.example.concurrency.core.transaction.LockTransaction;
import lombok.RequiredArgsConstructor;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

@Aspect
@Component
@RequiredArgsConstructor
public class RedisLockAspect {

    private final RedisRepository redisRepository;

    private final LockTransaction lockTransaction;


    @Around("@annotation(redisLock)")
    public Object redisLock(ProceedingJoinPoint point, RedisLock redisLock) throws Throwable {
        MethodSignature signature = (MethodSignature) point.getSignature();
        String lockKey;
        if(redisLock.parser()){
            lockKey = CustomSpringELParser.getDynamicValue(signature.getParameterNames(), point.getArgs(), redisLock.lockKey()).toString();
        } else {
            lockKey = redisLock.lockKey();
        }

        while (!redisRepository.tryLock(lockKey, redisLock.timeout(), redisLock.timeUnit())){
            try {
                Thread.sleep(100);
            } catch (InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        try {
            return lockTransaction.proceed(point);
        }finally {
            redisRepository.unlock(lockKey);
        }
    }
}
