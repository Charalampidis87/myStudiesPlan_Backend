package gr.di.mystudiesplanservice.declared;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class DeclaredDaoImpl extends JpaDao<Declared, Long> implements DeclaredDao {


    public List<Course> findDeclaredByUser(Long userId, Long advisorId) {

        List<Course> declaredCourses = entityManager.createQuery("select d.course from Declared d where d.user.id = :userId and d.advisor.id = :advisorId", Course.class)
                .setParameter("userId", userId)
                .setParameter("advisorId", advisorId)
                .getResultList();

        if (declaredCourses == null || declaredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return declaredCourses;
    }

    public List<Declared> findDeclaredByCourse(Long courseId) {

        List<Declared> declaredCourses = entityManager.createQuery("select d from Declared d where d.course.id = :courseId", Declared.class)
                .setParameter("courseId", courseId)
                .getResultList();

        if (declaredCourses == null || declaredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return declaredCourses;
    }

    public List<Declared> findAllDeclaredByUser(Long userId) {

        List<Declared> declaredCourses = entityManager.createQuery("select d from Declared d where d.user.id = :userId", Declared.class)
                .setParameter("userId", userId)
                .getResultList();

        if (declaredCourses == null || declaredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return declaredCourses;
    }

    public Declared findDeclaredByUserAndCourse(Long userId, Long courseId, Long advisorId) {

        List<Declared> declared = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.course.id = :courseId and d.advisor.id = :advisorId", Declared.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .setParameter("advisorId", advisorId)
                .getResultList();

        if (declared == null || declared.isEmpty()) {
            return null;
        }

        return declared.get(0);
    }

    public List<Double> findGradeGoalsByUser(Long userId) {

        List<Double> gradeGoals = entityManager.createQuery("select d.gradegoal from Declared d where d.user.id = :userId", Double.class)
                .setParameter("userId", userId)
                .getResultList();

        if (gradeGoals == null || gradeGoals.isEmpty()) {
            return new ArrayList<>();
        }

        return gradeGoals;
    }

    public List<Declared> findDeclared(Long userId, Long advisorId) {
        List<Declared> declaredCourses = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.advisor.id = :advisorId", Declared.class)
                .setParameter("userId", userId)
                .setParameter("advisorId", advisorId)
                .getResultList();

        if (declaredCourses == null || declaredCourses.isEmpty()) {
            return new ArrayList<>();
        }

        return declaredCourses;
    }

    public List<Declared> findAllDeclaredByUserAndCourse(Long userId, Long courseId) {
        List<Declared> declared = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.course.id = :courseId", Declared.class)
                .setParameter("userId", userId)
                .setParameter("courseId", courseId)
                .getResultList();

        if (declared == null || declared.isEmpty()) {
            return new ArrayList<>();
        }

        return declared;
    }

    public List<Declared> findPassedDeclaredByExamination(Long userId, Short year, Short examination, Long advisorId) {

        List<Declared> declared = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.year = :year and d.examination = :examination and d.advisor.id = :advisorId", Declared.class)
                .setParameter("userId", userId)
                .setParameter("year", year)
                .setParameter("examination", examination)
                .setParameter("advisorId", advisorId)
                .getResultList();

        if (declared == null || declared.isEmpty()) {
            return new ArrayList<>();
        }

        return declared;

    }

    public List<Declared> findDeclaredByYear(Long userId, Long advisorId, Short years){
        List<Declared> declared = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.advisor.id = :advisorId and d.year = :years", Declared.class)
                .setParameter("userId", userId)
                .setParameter("advisorId", advisorId)
                .setParameter("years", years)
                .getResultList();

        if (declared == null || declared.isEmpty()) {
            return new ArrayList<>();
        }

        return declared;

    }

    public boolean declaredExists(Long userId, Course course, Long advisorId) {
        List<Declared> declaredList = entityManager.createQuery("select d from Declared d where d.user.id = :userId and d.course = :course and d.advisor.id = :advisorId", Declared.class)
                .setParameter("userId", userId)
                .setParameter("course", course)
                .setParameter("advisorId", advisorId)
                .getResultList();

        if (declaredList == null || declaredList.isEmpty()) {
            return false;
        }

        return true;
    }

}
