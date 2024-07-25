package com.example.concurrency.domain.point.service;

import com.example.concurrency.domain.point.dto.PointDto;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.repo.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class PointService {

    private final UserRepository userRepository;


    @Transactional
    public void addPoint(PointDto pointDto) {
        User user = userRepository.findByUserId(pointDto.getUserId());

        Point point = Point.builder()
                .point(pointDto.getPoint())
                .registerDate(LocalDateTime.now())
                .build();
        user.setPoint(point);

        throw new RuntimeException("test");
    }

}
