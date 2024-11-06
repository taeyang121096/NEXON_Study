package com.example.secondapplication.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/second-service/health")
public class HealthController {

    @GetMapping
    public ResponseEntity<String> health(HttpServletRequest request){
        return ResponseEntity.ok(String.format("health1 port : %s",request.getServerPort()));
    }
}