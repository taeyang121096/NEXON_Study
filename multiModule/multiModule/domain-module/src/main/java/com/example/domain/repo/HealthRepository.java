package com.example.domain.repo;

import com.example.domain.domain.Health;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRepository extends JpaRepository<Health, Long> {

    @Query("select h from Health h where h.status = :status")
    Health findByStatus(@Param("status") String status);

}
