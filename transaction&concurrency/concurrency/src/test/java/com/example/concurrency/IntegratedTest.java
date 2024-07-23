package com.example.concurrency;

import com.example.concurrency.business.order.OrderFacade;
import com.example.concurrency.business.user.UserFacade;
import com.example.concurrency.domain.item.dto.ItemDto;
import com.example.concurrency.domain.item.service.ItemService;
import com.example.concurrency.domain.order.service.OrderService;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class IntegratedTest {

    @Autowired
    private ItemService itemService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserFacade userFacade;

    @Autowired
    private OrderFacade orderFacade;

    private final int maxThread = 100;

    @BeforeEach
    void setUp(){
        deleteAll();
        userFacade.createUser(UserDto.builder()
                        .id("test")
                        .pw("test")
                .build());

        itemService.save(ItemDto.builder()
                .name("한정 상품")
                .price(100L)
                .count(100L)
                .build());
    }

    @Test
    void orderTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                orderFacade.syncOrder("test", "한정 상품", 1L);
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
    }

    @Test
    void syncTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                order();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
    }

    public synchronized void order(){
        orderFacade.syncOrder("test", "한정 상품", 1L);
    }

    @Test
    void X_LockTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                order();
                countDownLatch.countDown();
            });
        }

        countDownLatch.await();
    }


    private void deleteAll(){
        orderService.deleteAllItems();
        itemService.deleteAllItems();
        userService.deleteAllItems();
    }
}
