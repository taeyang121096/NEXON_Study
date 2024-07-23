package com.example.concurrency.domain.user.service;

import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDto userDto) {

        Point point = Point.builder()
                .point(50000L)
                .registerDate(LocalDateTime.now())
                .build();

        User user = User.builder().id(userDto.getId()).pw(userDto.getPw())
                .registerDate(userDto.getRegisterDate()).build();

        point.setPointWithUser(user);

        return userRepository.save(user);
    }

    public User findUserByUserId(String userId) {
        return userRepository.findByUserId(userId);
    }


    public void deleteAllItems(){
        userRepository.deleteAllItems();
    }
}
