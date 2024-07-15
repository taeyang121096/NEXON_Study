package nexon.study.jpa.user.admin.repo;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import lombok.RequiredArgsConstructor;
import nexon.study.jpa.user.admin.entity.AdminUser;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class AdminUserRepo {

    private final EntityManager em;

    public AdminUser saveAdminUser(AdminUser adminUser){
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
        return adminUser;
    }

    public AdminUser findById(Long id){

        AdminUser adminUser = null;
        try {
            adminUser = em.find(AdminUser.class, id);
        } catch (Exception e){
            e.printStackTrace();
        } finally {
            em.close();
        }

        return adminUser;

    }

}
