package gr.di.mystudiesplanservice.course;

import gr.di.mystudiesplanservice.JpaDao;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CourseDaoImpl extends JpaDao<Course, Long> implements CourseDao {

    public List<Course> findCourses() {
        List<Course> courses = entityManager.createQuery("select c from Course c where c.offered = true", Course.class)
                .getResultList();

        if(courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }

        return courses;
    }

    public List<Course> findAdminCourses() {
        List<Course> courses = entityManager.createQuery("select c from Course c order by c.name", Course.class)
                .getResultList();

        if(courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }

        return courses;
    }

    public Course findCourseByName(String name) {
        return entityManager.createQuery("select c from Course c where c.name = :name", Course.class)
                .setParameter("name", name)
                .getSingleResult();
    }
}
