package com.example.api.controller;


import com.example.domain.domain.Health;
import com.example.domain.repo.HealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final HealthRepository healthRepository;

    @GetMapping("/health")
    public String health(){
        List<Health> test = healthRepository.findAll();
        return test.toString();
    }
}
