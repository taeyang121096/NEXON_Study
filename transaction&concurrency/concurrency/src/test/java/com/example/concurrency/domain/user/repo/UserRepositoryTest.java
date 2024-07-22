package com.example.concurrency.domain.user.repo;

import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Order(value = 1)
    @Transactional
    @Test
    void saveTest(){
        userRepository.deleteAllItems();
        userRepository.save(User.builder().id("userTest").pw("userTest")
                .registerDate(LocalDateTime.now()).build());
    }

    @Order(value = 2)
    @Test
    void findByUserId() {
    }

    @Order(value = 3)
    @Test
    void deleteAllItems() {
    }
}