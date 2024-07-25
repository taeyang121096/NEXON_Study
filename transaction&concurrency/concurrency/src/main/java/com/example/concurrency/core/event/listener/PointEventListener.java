package com.example.concurrency.core.event.listener;

import com.example.concurrency.core.event.event.PointEvent;
import com.example.concurrency.core.event.event.PointRollbackEvent;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;

import java.time.LocalDateTime;

@Component
@RequiredArgsConstructor
public class PointEventListener {

    private final UserService userService;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void point(PointEvent pointEvent) {

        Point point = Point.builder()
                .point(pointEvent.getPoint())
                .registerDate(LocalDateTime.now())
                .build();

        User user = userService.findUserByUserId(pointEvent.getUserId());

        user.setPoint(point);
    }

    @TransactionalEventListener(phase = TransactionPhase.BEFORE_COMMIT)
    public void pointRollback(PointRollbackEvent event){
        Point point = Point.builder()
                .point(event.getPoint())
                .registerDate(LocalDateTime.now())
                .build();

        User user = userService.findUserByUserId(event.getUserId());
        throw new RuntimeException("test");

    }
}
