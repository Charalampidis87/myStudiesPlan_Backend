package gr.di.mystudiesplanservice.advisor;

import gr.di.mystudiesplanservice.JpaDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class AdvisorDaoImpl extends JpaDao<Advisor, Long> implements AdvisorDao {

    public Advisor findActiveAdvisor(Long userId) {
        List<Advisor> advisors = entityManager.createQuery("select a from Advisor a where a.active = :active and a.user.id = :userId", Advisor.class)
                .setParameter("active", true)
                .setParameter("userId", userId)
                .getResultList();

        if(advisors == null || advisors.isEmpty()){
            return null;
        }
        return advisors.get(0);
    }

    public List<Advisor> findAdvisorsByUser(Long userId) {
        List<Advisor> advisors = entityManager.createQuery("select a from Advisor a where a.user.id = :userId", Advisor.class)
                .setParameter("userId", userId)
                .getResultList();

        if (advisors == null || advisors.isEmpty()) {
            return new ArrayList<>();
        }

        return advisors;
    }
}
