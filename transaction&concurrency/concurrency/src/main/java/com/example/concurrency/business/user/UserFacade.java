package com.example.concurrency.business.user;

import com.example.concurrency.domain.point.dto.PointDto;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.point.service.PointService;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;

    private final PointService pointService;

    @Transactional
    public User createUser(UserDto userDto){

        User user = userService.saveUser(userDto);
        pointService.save(user, PointDto.builder().point(50000L).build());

        return user;
    }
}
