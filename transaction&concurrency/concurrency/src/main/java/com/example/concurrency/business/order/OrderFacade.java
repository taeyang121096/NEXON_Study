package com.example.concurrency.business.order;

import com.example.concurrency.core.annotation.DistributedLock;
import com.example.concurrency.core.annotation.RedisLock;
import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.item.service.ItemService;
import com.example.concurrency.domain.order.dto.OrderDto;
import com.example.concurrency.domain.order.service.OrderService;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderFacade {

    private final UserService userService;

    private final OrderService orderService;

    private final ItemService itemService;


    @Transactional
    public void syncOrder(String userId, String itemName, Long quantity) {

        Item item = itemService.findByName(itemName);

        if (item.getCount() < quantity) {
            throw new RuntimeException("수 부족함");
        }
        Long totalPrice = item.getPrice() * quantity;

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();


        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        item.setCount(item.getCount() - quantity);


        orderService.createOrder(user, item, OrderDto.builder()
                .count(quantity)
                .totalPrice(totalPrice)
                .build());

        point.setPoint(point.getPoint() - totalPrice);

    }

    @Transactional
    public void xLockOrder(String userId, String itemName, Long quantity) {

        Item item = itemService.findByNameWithXLock(itemName);

        if (item.getCount() < quantity) {
            throw new RuntimeException("수 부족함");
        }
        Long totalPrice = item.getPrice() * quantity;

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();


        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        item.setCount(item.getCount() - quantity);


        orderService.createOrder(user, item, OrderDto.builder()
                .count(quantity)
                .totalPrice(totalPrice)
                .build());

        point.setPoint(point.getPoint() - totalPrice);

    }

    @Transactional
    public void namedLockOrder(String userId, String itemName, Long quantity) {

        Item item = itemService.findByName(itemName);

        if (item.getCount() < quantity) {
            throw new RuntimeException("수 부족함");
        }
        Long totalPrice = item.getPrice() * quantity;

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();


        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        item.setCount(item.getCount() - quantity);


        orderService.createOrder(user, item, OrderDto.builder()
                .count(quantity)
                .totalPrice(totalPrice)
                .build());

        point.setPoint(point.getPoint() - totalPrice);

    }

    @DistributedLock(lockKey = "'OrderFacade:distributedLockOrder:' + #itemName")
    public void distributedLockOrder(String userId, String itemName, Long quantity) {

        Item item = itemService.findByName(itemName);

        if (item.getCount() < quantity) {
            throw new RuntimeException("수 부족함");
        }
        Long totalPrice = item.getPrice() * quantity;

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();


        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        item.setCount(item.getCount() - quantity);


        orderService.createOrder(user, item, OrderDto.builder()
                .count(quantity)
                .totalPrice(totalPrice)
                .build());

        point.setPoint(point.getPoint() - totalPrice);

    }

    @RedisLock(lockKey = "'OrderFacade:redisLockOrder:' + #itemName")
    public void redisLockOrder(String userId, String itemName, Long quantity) {

        Item item = itemService.findByName(itemName);

        if (item.getCount() < quantity) {
            throw new RuntimeException("수 부족함");
        }
        Long totalPrice = item.getPrice() * quantity;

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();


        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        item.setCount(item.getCount() - quantity);


        orderService.createOrder(user, item, OrderDto.builder()
                .count(quantity)
                .totalPrice(totalPrice)
                .build());

        point.setPoint(point.getPoint() - totalPrice);

    }
}
