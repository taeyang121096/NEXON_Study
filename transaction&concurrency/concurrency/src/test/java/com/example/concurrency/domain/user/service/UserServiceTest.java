package com.example.concurrency.domain.user.service;

import com.example.concurrency.domain.user.dto.UserDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @Transactional
    @Test
    void saveUser() {
        userService.deleteAllItems();
        userService.createUser(UserDto.builder().id("test").pw("test").build());
    }

    @Test
    void findUserByUserId() {
    }

    @Test
    void deleteAllItems() {
    }
}
