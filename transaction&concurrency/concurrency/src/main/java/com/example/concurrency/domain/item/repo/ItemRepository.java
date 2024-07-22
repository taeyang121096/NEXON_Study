package com.example.concurrency.domain.item.repo;

import com.example.concurrency.domain.item.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query("select i from Item i where i.name = :name")
    Item findByName(@Param("name") String name);

    @Modifying
    @Query("delete from Item")
    void deleteAllItems();
}
