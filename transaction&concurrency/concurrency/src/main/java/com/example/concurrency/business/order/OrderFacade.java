package com.example.concurrency.business.order;

import com.example.concurrency.domain.item.entity.Item;
import com.example.concurrency.domain.item.service.ItemService;
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
    public Long syncOrder(String userId, String itemName, Long quantity) {
        Item item = itemService.findByName(itemName);
        if(item.getCount() < quantity){
            throw new RuntimeException("수 부족함");
        }

        User user = userService.findUserByUserId(userId);
        Point point = user.getPoint();
        Long totalPrice = item.getPrice() * quantity;

        if (point.getPoint() < totalPrice) {
            throw new RuntimeException("잔액 부족");
        }

        point.setPoint(point.getPoint() - totalPrice);

        return point.getPoint();
    }
}
