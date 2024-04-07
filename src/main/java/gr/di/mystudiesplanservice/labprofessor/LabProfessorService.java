package gr.di.mystudiesplanservice.labprofessor;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class LabProfessorService {

    @Autowired
    private LabProfessorDao labProfessorDao;

    @Autowired
    private CourseService courseService;

    public List<LabProfessor> getLabProfessorByCourse(Course course) {
        return labProfessorDao.findLabProfessorByCourse(course);
    }

    public void deleteByCourse(Long id) {
        Course course = courseService.getCourseById(id);
        List<LabProfessor> labProfessors = labProfessorDao.findLabProfessorByCourse(course);

        if(labProfessors != null || !labProfessors.isEmpty()) {
            for(LabProfessor labProfessor : labProfessors) {
                labProfessorDao.delete(labProfessor);
            }
        }
    }

    public boolean existLabProfessor(Course course, String labProfessor) {
        if (labProfessorDao.labProfessorExists(course, labProfessor) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int create(Course course, List<LabProfessor> labProfessorList) {
        for (LabProfessor labProfessor : labProfessorList) {
            if (labProfessor.getLabProfessor().isEmpty()) {
                continue;
            }
            labProfessor.setCourse(course);
//            professor.setProfessor(professor);
            labProfessorDao.create(labProfessor);
        }

        return 0;
    }

    public int edit(Course course, List<LabProfessor> labProfessorList) {
        List<LabProfessor> labProfessors = labProfessorDao.findLabProfessorByCourse(course);


        for (LabProfessor labProfessor : labProfessors) {
            if(!labProfessorList.contains(labProfessor)) {
                labProfessorDao.delete(labProfessor);
            }
        }

        for (LabProfessor labProfessor : labProfessorList) {
            if (labProfessor.getLabProfessor().isEmpty()) {
                continue;
            }
            boolean exist = this.existLabProfessor(course, labProfessor.getLabProfessor());

            if (exist) {
                continue;
            }
//            System.out.println("new professor: " + professor.getLabProfessor());
            labProfessor.setCourse(course);
            labProfessorDao.create(labProfessor);
        }

        return 0;
    }

    public List<LabProfessor> getLabProfessors() {
        return labProfessorDao.findLabProfessors();
    }
}
