package gr.di.mystudiesplanservice.professor;

import gr.di.mystudiesplanservice.Dao;
import gr.di.mystudiesplanservice.course.Course;

import java.util.List;

public interface ProfessorDao extends Dao<Professor, Long> {

    List<Professor> findProfessors();

    List<Professor> findProfessorByCourse(Course course);

    Professor professorExists(Course course, String professor);
}
