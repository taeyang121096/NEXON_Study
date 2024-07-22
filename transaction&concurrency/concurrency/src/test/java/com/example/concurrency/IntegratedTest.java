package com.example.concurrency;

import com.example.concurrency.business.order.OrderFacade;
import com.example.concurrency.business.user.UserFacade;
import com.example.concurrency.domain.item.dto.ItemDto;
import com.example.concurrency.domain.item.service.ItemService;
import com.example.concurrency.domain.order.service.OrderService;
import com.example.concurrency.domain.point.service.PointService;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
public class IntegratedTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private PointService pointService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private OrderFacade orderFacade;

    @BeforeEach
    void setUp(){
        deleteAll();
        userFacade.createUser(UserDto.builder()
                        .id("test")
                        .pw("test")
                .build());

        itemService.save(ItemDto.builder()
                .name("한정 상품")
                .price(20000L)
                .count(10L)
                .build());
    }


    @Transactional
    @Test
    void syncTest() throws Exception {
        Long value = orderFacade.syncOrder("test", "한정 상품", 3L);
    }

    private void deleteAll(){
        pointService.deleteAllItems();
        orderService.deleteAllItems();
        itemService.deleteAllItems();
        userService.deleteAllItems();
    }
}
