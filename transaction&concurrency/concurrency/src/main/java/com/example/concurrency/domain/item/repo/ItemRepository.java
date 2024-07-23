package com.example.concurrency.domain.item.repo;

import com.example.concurrency.domain.item.entity.Item;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Transactional(readOnly = true)
    @Query("select i from Item i where i.name = :name")
    Item findByName(@Param("name") String name);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    @Query("select i from Item i where i.name = :name")
    Item findByNameWithXLock(@Param("name") String name);

    @Transactional
    @Modifying
    @Query("delete from Item")
    void deleteAllItems();
}
