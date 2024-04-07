package gr.di.mystudiesplanservice.recommended;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface RecommendedDao extends Dao<Recommended, Long> {
    List<Recommended> findRecommendedByCourse(Course course);

    List<Recommended> findRecommended();

    Recommended recommendedExists(Course course, String recommended);

    List<Recommended> findCoursesIsRecommended(Course course);
}
