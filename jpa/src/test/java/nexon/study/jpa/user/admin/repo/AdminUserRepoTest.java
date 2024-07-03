package nexon.study.jpa.user.admin.repo;

import jakarta.persistence.EntityManager;
import nexon.study.jpa.core.jpa.JpaUtil;
import nexon.study.jpa.user.admin.entity.AdminUser;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AdminUserRepoTest {


    @Autowired
    private AdminUserRepo adminUserRepo;

    @Rollback(value = false)
    @Test
    void saveAdminUser(){
        adminUserRepo.saveAdminUser(AdminUser.builder()
                .userId("test")
                .password("test")
                .registerDate(LocalDateTime.now()).build());
    }

}
