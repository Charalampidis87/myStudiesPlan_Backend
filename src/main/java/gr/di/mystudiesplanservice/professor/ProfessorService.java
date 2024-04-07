package gr.di.mystudiesplanservice.professor;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ProfessorService {
    @Autowired
    private ProfessorDao professorDao;

    @Autowired
    private CourseService courseService;

    public List<Professor> getProfessorByCourse(Course course) {
        return professorDao.findProfessorByCourse(course);
    }

    public void deleteByCourse(Long id) {
        Course course = courseService.getCourseById(id);
        List<Professor> professors = professorDao.findProfessorByCourse(course);

        if(professors != null || !professors.isEmpty()) {
            for(Professor professor : professors) {
                professorDao.delete(professor);
            }
        }
    }

    public boolean exist(Course course, String professor) {
        if (professorDao.professorExists(course, professor) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int create(Course course, List<Professor> professorList) {
//        Professor courseProfessor = new Professor();
        for (Professor professor : professorList) {
            if (professor.getProfessor().isEmpty()) {
                continue;
            }
            professor.setCourse(course);
//            professor.setProfessor(professor);
            professorDao.create(professor);
        }

        return 0;
    }

    public int edit(Course course, List<Professor> professorList) {
        List<Professor> professors = professorDao.findProfessorByCourse(course);

        for (Professor professor : professors) {
            if(!professorList.contains(professor)) {
                professorDao.delete(professor);
            }
        }

        for (Professor professor : professorList) {
            if (professor.getProfessor().isEmpty()) {
                continue;
            }
            boolean exist = exist(course, professor.getProfessor());
            if (exist) {
                continue;
            }
//            System.out.println("new professor: " + professor.getProfessor());
            professor.setCourse(course);
            professorDao.create(professor);
        }

        return 0;
    }

    public List<Professor> getProfessors() {
        return professorDao.findProfessors();
    }

}
