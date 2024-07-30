package com.example.concurrency.concurrency;

import com.example.concurrency.business.order.OrderFacade;
import com.example.concurrency.business.user.UserFacade;
import com.example.concurrency.domain.item.dto.ItemDto;
import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.item.repo.ItemRepository;
import com.example.concurrency.domain.item.service.ItemService;
import com.example.concurrency.domain.order.service.OrderService;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.repo.UserRepository;
import com.example.concurrency.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.assertj.core.api.Assertions.assertThat;

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

    @Autowired
    private ItemRepository itemRepository;

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
                .price(1000L)
                .count(150L)
                .build());
    }

    @Test
    void orderTest() throws InterruptedException {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                try {
                    orderFacade.syncOrder("test", "한정 상품", 1L);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();
        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isNotEqualTo(0L);
        assertThat(item.getCount()).isNotEqualTo(100L);
        assertThat(orderCount).isNotEqualTo(50L);
    }

    @Test
    void syncTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                try {
                    order();
                }catch (Exception e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isEqualTo(0L);
        assertThat(item.getCount()).isEqualTo(100L);
        assertThat(orderCount).isEqualTo(50L);
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
                try {
                    orderFacade.xLockOrder("test", "한정 상품", 1L);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isEqualTo(0L);
        assertThat(item.getCount()).isEqualTo(100L);
        assertThat(orderCount).isEqualTo(50L);
    }

    @Test
    void Named_LockTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);
        int timeout = 100;

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                try {
                    int lock = itemRepository.getLock("lock:key", timeout);
                    if(lock == 0) {
                        throw new RuntimeException("unacqurie lock-key");
                    }
                    orderFacade.namedLockOrder("test", "한정 상품", 1L);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    itemRepository.releaseLock("lock:key");
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isEqualTo(0L);
        assertThat(item.getCount()).isEqualTo(100L);
        assertThat(orderCount).isEqualTo(50L);
    }

    @Test
    void distributedLockTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                try {
                    orderFacade.distributedLockOrder("test", "한정 상품", 1L);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isEqualTo(0L);
        assertThat(item.getCount()).isEqualTo(100L);
        assertThat(orderCount).isEqualTo(50L);
    }

    @Test
    void redisLockTest() throws Exception {
        ExecutorService executorService = Executors.newFixedThreadPool(maxThread);
        CountDownLatch countDownLatch = new CountDownLatch(maxThread);

        for(int i = 0; i < maxThread; i++) {
            executorService.execute(() -> {
                try {
                    orderFacade.redisLockOrder("test", "한정 상품", 1L);
                } catch (Exception e){
                    e.printStackTrace();
                } finally {
                    countDownLatch.countDown();
                }
            });
        }

        countDownLatch.await();

        User user = userService.findByUserWithPointByUserId("test");
        Item item = itemService.findByName("한정 상품");
        long orderCount = orderService.getOrderCount();
        Point point = user.getPoint();

        assertThat(point.getPoint()).isEqualTo(0L);
        assertThat(item.getCount()).isEqualTo(100L);
        assertThat(orderCount).isEqualTo(50L);
    }


    private void deleteAll(){
        orderService.deleteAllItems();
        userService.deleteAllItems();
        itemService.deleteAllItems();
    }
}
