package nexon.study.jpa.core.jpa;

import jakarta.persistence.EntityManager;
import nexon.study.jpa.user.general.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class JpaUtilTest {

    @Autowired
    private JpaUtil jpaUtil;


    @Test
    public void flushTest(){
        EntityManager em = jpaUtil.getEntityManager();
        em.getTransaction().begin();
        em.persist(User.builder().userId("test").password("123").build());
        User user = em.find(User.class, 1L);
        System.out.println(user);
        System.out.println("hohohoho");
        em.flush();
        System.out.println("hohohoh1212o");
        User user1 = em.find(User.class, 1L);
        System.out.println(user);
        em.getTransaction().commit();
    }

}
