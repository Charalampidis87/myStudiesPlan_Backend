package gr.di.mystudiesplanservice.professor;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProfessorDaoImpl extends JpaDao<Professor, Long> implements ProfessorDao {

    public List<Professor> findProfessors() {
        List<Professor> professors = entityManager.createQuery("select p from Professor p", Professor.class)
                .getResultList();

        if (professors == null || professors.isEmpty()) {
            return new ArrayList<>();
        }

        return professors;
    }

    public List<Professor> findProfessorByCourse(Course course) {
        return entityManager.createQuery("select p from Professor p where p.course = :course", Professor.class)
                .setParameter("course", course)
                .getResultList();
    }

    public Professor professorExists(Course course, String professor) {
        List<Professor> professors = entityManager.createQuery("select p from Professor p where p.course = :course and p.professor = :professor", Professor.class)
                .setParameter("course", course)
                .setParameter("professor", professor)
                .getResultList();

        if (professors.size() == 0) {
            return null;
        }

        return professors.get(0);
    }
}
