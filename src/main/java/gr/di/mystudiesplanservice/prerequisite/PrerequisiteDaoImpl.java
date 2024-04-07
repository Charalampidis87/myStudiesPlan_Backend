package gr.di.mystudiesplanservice.prerequisite;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PrerequisiteDaoImpl extends JpaDao<Prerequisite, Long> implements PrerequisiteDao {

    public List<Prerequisite> findPrerequisiteByCourse(Course course) {
        return entityManager.createQuery("select p from Prerequisite p where p.course = :course", Prerequisite.class)
                .setParameter("course", course)
                .getResultList();
    }

    public List<Prerequisite> findPrerequisites() {
        List<Prerequisite> prerequisites = entityManager.createQuery("select p from Prerequisite p", Prerequisite.class)
                .getResultList();

        if (prerequisites == null || prerequisites.isEmpty()) {
            return new ArrayList<>();
        }

        return prerequisites;
    }

    public Prerequisite prerequisiteExists(Course course, String prerequisite) {
        List<Prerequisite> preCourses = entityManager.createQuery("select p from Prerequisite p where p.course = :course and p.prerequisite.name = :prerequisite", Prerequisite.class)
                .setParameter("course", course)
                .setParameter("prerequisite", prerequisite)
                .getResultList();

        if (preCourses.size() == 0) {
            return null;
        }

        return preCourses.get(0);
    }

    public List<Prerequisite> findCoursesIsPrerequisite (Course course) {
        return entityManager.createQuery("select p from Prerequisite p where p.prerequisite = :course", Prerequisite.class)
                .setParameter("course", course)
                .getResultList();
    }
}
