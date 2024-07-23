package com.example.concurrency.business.user;

import com.example.concurrency.domain.point.dto.PointDto;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserFacade {

    private final UserService userService;


    public User createUser(UserDto userDto){
        return userService.createUser(userDto);
    }
}
