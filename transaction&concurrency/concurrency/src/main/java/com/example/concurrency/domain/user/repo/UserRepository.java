package com.example.concurrency.domain.user.repo;

import com.example.concurrency.domain.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select u from users u where u.id = :userId")
    User findByUserId(@Param("userId") String userId);
}
