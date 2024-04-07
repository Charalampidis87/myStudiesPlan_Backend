package gr.di.mystudiesplanservice.specialization;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SpecializationService {
    @Autowired
    private SpecializationDao specializationDao;

    @Autowired
    private CourseService courseService;

    public boolean specializationExist(Course course, Specialization specialization) {
        if (specializationDao.specExists(course, specialization) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int create(Course course, List<Specialization> specializationList) {
//        Professor courseProfessor = new Professor();
        for (Specialization specialization : specializationList) {
            if (specialization.getType().isEmpty()) {
                continue;
            }
            specialization.setCourse(course);
//            professor.setProfessor(professor);
            specializationDao.create(specialization);
        }

        return 0;
    }

    public int edit(Course course, List<Specialization> specializationList) {
        List<Specialization> specializations = specializationDao.findSpecializationByCourse(course);

        for (Specialization specialization : specializations) {
            if(!specializationList.contains(specialization)) {
                specializationDao.delete(specialization);
            }
        }

        for (Specialization specialization : specializationList) {
            if (specialization.getType().isEmpty()) {
                continue;
            }
            boolean exist = specializationExist(course, specialization);
            if (exist) {
                continue;
            }
//            System.out.println("new professor: " + professor.getProfessor());
            specialization.setCourse(course);
            specializationDao.create(specialization);
        }

        return 0;
    }

    public List<Specialization> getSpecializations() {

        List<Specialization> specializations = specializationDao.findSpecializations();

        return specializations;
    }

    public void deleteByCourse(Long id) {
        Course course = courseService.getCourseById(id);
        List<Specialization> specializations = specializationDao.findSpecializationByCourse(course);

        if(specializations != null || !specializations.isEmpty()) {
            for(Specialization specialization : specializations) {
                specializationDao.delete(specialization);
            }
        }
    }
}
