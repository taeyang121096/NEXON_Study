package nexon.study.jpa.user.general.entity;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import nexon.study.jpa.order.entity.Order;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.reflect.Member;
import java.time.LocalDateTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UserTest {

    @PersistenceContext
    private EntityManager em;

    private User user;

    @Transactional
    @BeforeEach
    void setUpTest(){
        user = User.builder().userId("test").password("test1")
                .registerDate(LocalDateTime.now())
                .build();
        em.persist(user);

        Order order1 = Order.builder().date(LocalDateTime.now()).build();
        order1.setUserWithOrder(user);
        Order order2 = Order.builder().date(LocalDateTime.now()).build();
        order2.setUserWithOrder(user);

        em.persist(order1);
        em.persist(order2);
    }

    @Transactional
    @Test
    void graphTest(){

        User user1 = em.find(User.class, user.getId());
        List<Order> orders = user1.getOrders();
        System.out.println(orders);

    }

    @Transactional
    @Test
    void jpqlTest(){
        String jpql = "select o from Order o join o.user u where " + "u.userId=: userName";

        List<Order> resultList = em.createQuery(jpql, Order.class)
                .setParameter("userName", "test")
                .getResultList();

        for (Order order : resultList) {
            System.out.println(order);
        }
    }

}