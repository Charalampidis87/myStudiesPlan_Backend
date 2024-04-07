package gr.di.mystudiesplanservice.labprofessor;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface LabProfessorDao extends Dao<LabProfessor, Long> {

    List<LabProfessor> findLabProfessors();

    List<LabProfessor> findLabProfessorByCourse(Course course);

    LabProfessor labProfessorExists(Course course, String labProfessor);
}
