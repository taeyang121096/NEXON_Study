package com.example.concurrency.rollback;

import com.example.concurrency.business.user.UserFacade;
import com.example.concurrency.domain.item.service.ItemService;
import com.example.concurrency.domain.order.service.OrderService;
import com.example.concurrency.domain.point.entity.Point;
import com.example.concurrency.domain.point.service.PointService;
import com.example.concurrency.domain.user.dto.UserDto;
import com.example.concurrency.domain.user.entity.User;
import com.example.concurrency.domain.user.service.UserService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class GlobalRollbackTest {


    @Autowired
    private UserService userService;

    @Autowired
    private OrderService orderService;

    @Autowired
    private UserFacade userFacade;
    @Autowired
    private PointService pointService;


    @Transactional
    @BeforeEach
    void setUp() {
        deleteAll();
    }

    @Test
    void eventException() throws Exception {
        try {
            userFacade.createRollbackUser(UserDto.builder()
                    .id("test")
                    .pw("test")
                    .build());
        } catch (Exception e){
            e.printStackTrace();
        }
        User user = userService.findByUserWithPointByUserId("test");
        assertThat(user).isNull();
    }

    @Test
    void notRollbackTest() throws Exception {
        pointService.notRollbackPoint();

        Point point = pointService.getPointByPointId(1L);

        assertThat(point).isNotNull();
    }


    private void deleteAll() {
        orderService.deleteAllItems();
        userService.deleteAllItems();

    }

}
