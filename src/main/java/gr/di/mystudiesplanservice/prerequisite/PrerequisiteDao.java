package gr.di.mystudiesplanservice.prerequisite;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface PrerequisiteDao extends Dao<Prerequisite, Long> {

    List<Prerequisite> findPrerequisiteByCourse(Course course);

    List<Prerequisite> findPrerequisites();

    Prerequisite prerequisiteExists(Course course, String prerequisite);

    List<Prerequisite> findCoursesIsPrerequisite (Course course);
}
