package com.example.api.controller;


import com.example.domain.repo.HealthRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class HealthController {

    private final HealthRepository healthRepository;

    @GetMapping("/health")
    public String health(){
        return healthRepository.getHealth();
    }
}
