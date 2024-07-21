package com.example.concurrency.domain.point.service;

import com.example.concurrency.domain.point.dto.PointDto;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.point.repo.PointRepository;
import com.example.concurrency.domain.user.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PointService {

    private final PointRepository pointRepository;

    @Transactional
    public Point save(User user, PointDto pointDto) {

        Point point = Point.builder().point(pointDto.getPoint())
                .registerDate(pointDto.getRegisterDate())
                .build();
        point.setPointWithUser(user);

        return pointRepository.save(point);
    }
}
