package com.example.concurrency.domain.point.repo;

import com.example.concurrency.domain.point.entity.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface PointRepository extends JpaRepository<Point, Long> {

    @Modifying
    @Query("delete from Point")
    void deleteAllItems();
}
