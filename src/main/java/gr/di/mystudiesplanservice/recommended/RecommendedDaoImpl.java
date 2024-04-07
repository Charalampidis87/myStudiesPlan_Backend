package gr.di.mystudiesplanservice.recommended;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class RecommendedDaoImpl extends JpaDao<Recommended, Long> implements RecommendedDao {

    public List<Recommended> findRecommendedByCourse(Course course) {
        return entityManager.createQuery("select r from Recommended r where r.course = :course", Recommended.class)
                .setParameter("course", course)
                .getResultList();
    }

    public List<Recommended> findRecommended() {
        List<Recommended> recommendedList = entityManager.createQuery("select r from Recommended r", Recommended.class)
                .getResultList();

        if (recommendedList == null || recommendedList.isEmpty()) {
            return new ArrayList<>();
        }

        return recommendedList;
    }

    public Recommended recommendedExists(Course course, String recommended) {
        List<Recommended> recCourses = entityManager.createQuery("select r from Recommended r where r.course = :course and r.recommended.name = :recommended", Recommended.class)
                .setParameter("course", course)
                .setParameter("recommended", recommended)
                .getResultList();

        if (recCourses.size() == 0) {
            return null;
        }

        return recCourses.get(0);
    }

    public List<Recommended> findCoursesIsRecommended(Course course) {
        return entityManager.createQuery("select r from Recommended r where r.recommended = :course", Recommended.class)
                .setParameter("course", course)
                .getResultList();
    }
}
