package com.example.concurrency.domain.user.repo;

import com.example.concurrency.domain.point.entity.Point;
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
//    @Transactional
    @Test
    void saveTest(){
        userRepository.deleteAllItems();

        User saveUser = User.builder().id("userTest").pw("userTest")
                .registerDate(LocalDateTime.now()).build();
        Point point = Point.builder().point(5000L).registerDate(LocalDateTime.now()).build();
        point.setPointWithUser(saveUser);

        User user = userRepository.save(saveUser);


    }

    @Order(value = 2)
    @Test
    void findByUserId() {
        userRepository.findByUserId("userTest");
    }

    @Order(value = 3)
    @Test
    void deleteAllItems() {
    }
}
