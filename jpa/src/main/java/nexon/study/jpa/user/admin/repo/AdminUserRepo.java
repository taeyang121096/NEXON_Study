package nexon.study.jpa.user.admin.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import nexon.study.jpa.core.jpa.JpaUtil;
import nexon.study.jpa.user.admin.entity.AdminUser;
import org.springframework.stereotype.Repository;

@Repository
public class AdminUserRepo {


    public void saveAdminUser(AdminUser adminUser){
        EntityManager em = JpaUtil.getEntityManager();
        EntityTransaction tx = em.getTransaction();

        try {
            tx.begin();
            em.persist(adminUser);
            tx.commit();
        } catch (Exception e){
            e.printStackTrace();
            if(tx.isActive()) tx.rollback();
        } finally {
            em.close();
        }
    }

}
