package gr.di.mystudiesplanservice.course;

import gr.di.mystudiesplanservice.Dao;

import java.util.List;

public interface CourseDao extends Dao<Course, Long> {
    List<Course> findCourses();

    Course findCourseByName(String name);

    List<Course> findAdminCourses();

}
