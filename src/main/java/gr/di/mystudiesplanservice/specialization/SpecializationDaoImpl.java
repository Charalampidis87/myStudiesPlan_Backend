package gr.di.mystudiesplanservice.specialization;

import gr.di.mystudiesplanservice.JpaDao;
import gr.di.mystudiesplanservice.course.Course;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SpecializationDaoImpl extends JpaDao<Specialization, Long> implements SpecializationDao {

    public List<Specialization> findSpecializations() {
        List<Specialization> specializations = entityManager.createQuery("select s from Specialization s", Specialization.class)
                .getResultList();

        if (specializations == null || specializations.isEmpty()) {
            return new ArrayList<>();
        }

        return specializations;
    }

    public Specialization specExists(Course course, Specialization specialization) {
        List<Specialization> specializations = entityManager.createQuery("select s from Specialization s where s.course = :course and s.specialization = :specialization" +
                " and s.type = :type", Specialization.class)
                .setParameter("course", course)
                .setParameter("specialization", specialization.getSpecialization())
                .setParameter("type", specialization.getType())
                .getResultList();

        if (specializations.size() == 0) {
            return null;
        }

        return specializations.get(0);
    }

    public List<Specialization> findSpecializationByCourse(Course course) {
        return entityManager.createQuery("select s from Specialization s where s.course = :course", Specialization.class)
                .setParameter("course", course)
                .getResultList();
    }
}
