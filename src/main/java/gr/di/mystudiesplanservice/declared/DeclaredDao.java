package gr.di.mystudiesplanservice.declared;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.ArrayList;
import java.util.List;

public interface DeclaredDao extends Dao<Declared, Long> {
    List<Course> findDeclaredByUser(Long userId, Long advisorId);

    Declared findDeclaredByUserAndCourse(Long userId, Long courseId, Long advisorId);

    List<Double> findGradeGoalsByUser(Long userId);

    List<Declared> findDeclared(Long userId, Long advisorId);

    List<Declared> findAllDeclaredByUserAndCourse(Long userId, Long courseId);

    List<Declared> findPassedDeclaredByExamination(Long userId, Short year, Short examination, Long advisorId);

    List<Declared> findDeclaredByYear(Long userId, Long advisorId, Short years);

    boolean declaredExists(Long userId, Course course, Long advisorId);

    List<Declared> findAllDeclaredByUser(Long userId);

    List<Declared> findDeclaredByCourse(Long courseId);
}
