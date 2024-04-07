package gr.di.mystudiesplanservice.passed;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface PassedDao extends Dao<Passed, Long> {
    List<Passed> findPassedByUser(Long userId);

    List<Course> findPassed(Long userId);

    List<Passed> findPassedByExamination(Long userId, Short year, Short examination);

    Integer findGoodGradesSum(Long userId);

    Integer findVeryGoodGradesSum(Long userId);

    Integer findExcellentGradesSum(Long userId);

    List<Passed> findPassedByYear(Long userId, Short years);

    boolean passedExists(Long userId, Course course);

    Passed findPassedByUserAndCourse(Long userId, Long courseId);

    List<Passed> findPassedByCourse(Long courseId);
}
