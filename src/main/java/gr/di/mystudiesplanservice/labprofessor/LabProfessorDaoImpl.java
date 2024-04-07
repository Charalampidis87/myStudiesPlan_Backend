package gr.di.mystudiesplanservice.labprofessor;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class LabProfessorDaoImpl extends JpaDao<LabProfessor, Long> implements LabProfessorDao {

    public List<LabProfessor> findLabProfessors() {
        List<LabProfessor> professors = entityManager.createQuery("select p from LabProfessor p", LabProfessor.class)
                .getResultList();

        if (professors == null || professors.isEmpty()) {
            return new ArrayList<>();
        }

        return professors;
    }

    public List<LabProfessor> findLabProfessorByCourse(Course course) {
        return entityManager.createQuery("select l from LabProfessor l where l.course = :course", LabProfessor.class)
                .setParameter("course", course)
                .getResultList();
    }

    public LabProfessor labProfessorExists(Course course, String labProfessor) {
        List<LabProfessor> lp = entityManager.createQuery("select l from LabProfessor l where l.course = :course and l.labProfessor = :labProfessor", LabProfessor.class)
                .setParameter("course", course)
                .setParameter("labProfessor", labProfessor)
                .getResultList();

        if (lp.size() == 0) {
            return null;
        }

        return lp.get(0);


    }
}
