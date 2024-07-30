package com.example.concurrency.domain.user.service;

import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public User createUser(UserDto userDto) {
        User user = User.builder().id(userDto.getId()).pw(userDto.getPw())
                .registerDate(userDto.getRegisterDate()).build();
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

    public User findByUserWithPointByUserId(String userId){
        return userRepository.findByUserWithPointByUserId(userId);
    }
}
