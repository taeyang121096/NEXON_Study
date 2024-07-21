package com.example.concurrency.business.user;

import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserFacadeTest {

    @Autowired
    private UserFacade userFacade;


    @Rollback(value = false)
    @Test
    void saveUser(){
        User user = userFacade.createUser(
                UserDto.builder()
                        .id("test")
                        .pw("test1")
                        .build()
        );
        Point point = user.getPoint();

        assertThat(point.getPointId()).isEqualTo(user.getPoint().getPointId());
        assertThat(user.getUserId()).isEqualTo(point.getUser().getUserId());
    }

}
