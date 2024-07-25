package com.example.concurrency.core.event.listener;

import com.example.concurrency.core.event.event.CouponEvent;
import com.example.concurrency.domain.coupon.entity.Coupon;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class CouponEventListener {

    private final UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void coupon(CouponEvent event) {
        Coupon coupon = Coupon.builder().name(event.getName()).price(event.getPrice())
                .expireDate(event.getExpireTime()).registerDate(event.getRegisterDate()).build();

        User user = userService.findUserByUserId(event.getUserId());

        user.getCoupons().add(coupon);
    }
}
