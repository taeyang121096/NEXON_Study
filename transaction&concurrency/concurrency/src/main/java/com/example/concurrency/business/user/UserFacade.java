package com.example.concurrency.business.user;

import com.example.concurrency.core.event.event.CouponEvent;
import com.example.concurrency.core.event.event.PointEvent;
import com.example.concurrency.core.event.event.PointRollbackEvent;
import com.example.concurrency.domain.point.dto.PointDto;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.point.service.PointService;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final PointService pointService;

    private final ApplicationEventPublisher eventPublisher;


    @Transactional
    public User createUser(UserDto userDto){
        eventPublisher.publishEvent(new PointEvent(50000L, userDto.getId()));
        eventPublisher.publishEvent(new CouponEvent("회원가입", 3000L,LocalDateTime.now().plusDays(30L),LocalDateTime.now(), userDto.getId()));
        return userService.createUser(userDto);
    }

    @Transactional
    public void createRollbackUser(UserDto userDto){

        userService.createUser(userDto);

        try {
            pointService.addPoint(PointDto.builder()
                            .userId(userDto.getId())
                            .point(5000L)
                    .build());
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        eventPublisher.publishEvent(new CouponEvent("회원가입", 3000L,LocalDateTime.now().plusDays(30L),LocalDateTime.now(), userDto.getId()));

    }
}
