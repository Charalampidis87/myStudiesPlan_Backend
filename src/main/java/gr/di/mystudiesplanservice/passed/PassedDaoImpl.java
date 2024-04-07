package gr.di.mystudiesplanservice.passed;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PassedDaoImpl extends JpaDao<Passed, Long> implements PassedDao {

    public List<Passed> findPassedByUser(Long userId) {

        List<Passed> passedCourses = entityManager.createQuery("select p from Passed p where p.user.id = :userId", Passed.class)
                .setParameter("userId", userId)
                .getResultList();

        if (passedCourses == null || passedCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return passedCourses;
    }

    public List<Course> findPassed(Long userId) {

        List<Course> courses = entityManager.createQuery("select p.course from Passed p where p.user.id = :userId", Course.class)
                .setParameter("userId", userId)
                .getResultList();

        if (courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }

        return courses;
    }

    public Integer findGoodGradesSum(Long userId) {

        List<Passed> passedGood = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.grade < 6.5", Passed.class)
                .setParameter("userId", userId)
                .getResultList();

        if (passedGood == null || passedGood.isEmpty()) {
            return 0;
        }

        return passedGood.size();
    }

    public Integer findVeryGoodGradesSum(Long userId) {

        List<Passed> passedVeryGood = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.grade > 6.5 and p.grade < 8.5", Passed.class)
                .setParameter("userId", userId)
                .getResultList();

        if (passedVeryGood == null || passedVeryGood.isEmpty()) {
            return 0;
        }

        return passedVeryGood.size();
    }

    public Integer findExcellentGradesSum(Long userId) {

        List<Passed> passedExcellent = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.grade > 8.5", Passed.class)
                .setParameter("userId", userId)
                .getResultList();

        if (passedExcellent == null || passedExcellent.isEmpty()) {
            return 0;
        }

        return passedExcellent.size();
    }

    public List<Passed> findPassedByExamination(Long userId, Short year, Short examination) {

        List<Passed> passed = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.year = :year and p.examination = :examination", Passed.class)
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("examination", examination)
                .getResultList();

        if (passed == null || passed.isEmpty()) {
            return new ArrayList<>();
        }

        return passed;

    }

    public List<Passed> findPassedByYear(Long userId, Short years) {

        List<Passed> passed = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.year = :years", Passed.class)
                .setParameter("userId", userId)
                .setParameter("years", years)
                .getResultList();

        if (passed == null || passed.isEmpty()) {
            return new ArrayList<>();
        }

        return passed;

    }

    public boolean passedExists(Long userId, Course course) {
        List<Passed> passedList = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.course = :course", Passed.class)
                .setParameter("userId", userId)
                .setParameter("course", course)
                .getResultList();

        if (passedList == null || passedList.isEmpty()) {
            return false;
        }

        return true;
    }

    public Passed findPassedByUserAndCourse(Long userId, Long courseId) {
        List<Passed> passed = entityManager.createQuery("select p from Passed p where p.user.id = :userId and p.course.id = :courseId", Passed.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .getResultList();

        if (passed == null || passed.isEmpty()) {
            return null;
        }

        return passed.get(0);
    }

    public List<Passed> findPassedByCourse(Long courseId) {

        List<Passed> passedCourses = entityManager.createQuery("select p from Passed p where p.course.id = :courseId", Passed.class)
                .setParameter("courseId", courseId)
                .getResultList();

        if (passedCourses == null || passedCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return passedCourses;
    }
}
