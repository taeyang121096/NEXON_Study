package com.example.concurrency.domain.user.service;

import com.example.concurrency.core.event.event.CouponEvent;
import com.example.concurrency.domain.coupon.entity.Coupon;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final ApplicationEventPublisher eventPublisher;

    @Transactional
    public User createUser(UserDto userDto) {

        Point point = Point.builder()
                .point(50000L)
                .registerDate(LocalDateTime.now())
                .build();

        User user = User.builder().id(userDto.getId()).pw(userDto.getPw())
                .registerDate(userDto.getRegisterDate()).build();

        user.setPoint(point);

        return userRepository.save(user);
    }

    @Transactional
    public User createUserWithCoupon(UserDto userDto) {
        Point point = Point.builder()
                .point(50000L)
                .registerDate(LocalDateTime.now())
                .build();

        User user = User.builder().id(userDto.getId()).pw(userDto.getPw())
                .registerDate(userDto.getRegisterDate()).build();

        eventPublisher.publishEvent(new CouponEvent("회원가입", 3000L,LocalDateTime.now().plusDays(30L),LocalDateTime.now(), userDto.getId()));

        user.setPoint(point);

        return userRepository.save(user);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }


    public void deleteAllItems(){
        userRepository.deleteAllItems();
        userRepository.deleteAllPoint();
        userRepository.deleteAllCoupon();
    }
}
