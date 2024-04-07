package gr.di.mystudiesplanservice.specialization;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface SpecializationDao extends Dao<Specialization, Long> {
    Specialization specExists(Course course, Specialization specialization);

    List<Specialization> findSpecializationByCourse(Course course);

    List<Specialization> findSpecializations();
}
