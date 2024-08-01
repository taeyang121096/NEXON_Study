package com.example.domain.repo;

import org.springframework.stereotype.Repository;

@Repository
public class HealthRepository {

    public String getHealth(){
        return "OK";
    }
}
