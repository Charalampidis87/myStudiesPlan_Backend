package gr.di.mystudiesplanservice.passed;

import gr.di.mystudiesplanservice.advisor.Advisor;
import gr.di.mystudiesplanservice.advisor.AdvisorService;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import gr.di.mystudiesplanservice.declared.Declared;
import gr.di.mystudiesplanservice.declared.DeclaredService;
import gr.di.mystudiesplanservice.prerequisite.Prerequisite;
import gr.di.mystudiesplanservice.prerequisite.PrerequisiteService;
import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PassedService {
    @Autowired
    private PassedDao passedDao;


    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private DeclaredService declaredService;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private PrerequisiteService prerequisiteService;


    public Passed create(Long userId, Long courseId, Short year, Short examination, Double grade) throws IllegalArgumentException {

        User user = userService.getUserById(userId);

        Course course = courseService.getCourseById(courseId);



        Passed passed = new Passed();
        passed.setUser(user);
        passed.setCourse(course);
        passed.setExamination(examination);
        passed.setGrade(grade);
        passed.setYear(year);
        return passedDao.create(passed);
    }

    public void updatePassed(Passed passed) {
        passedDao.update(passed);
    }

    public List<Passed> getPassedByUser(Long userId) {

        return passedDao.findPassedByUser(userId);
    }

    public List<Course> getPassed(Long userId) {
        return passedDao.findPassed(userId);
    }

    public Integer getGoodGradesSum(Long userId) {
        return passedDao.findGoodGradesSum(userId);
    }

    public Integer getVeryGoodGradesSum(Long userId) {
        return passedDao.findVeryGoodGradesSum(userId);
    }

    public Integer getExcellentGradesSum(Long userId) {
        return passedDao.findExcellentGradesSum(userId);
    }

    public List<Passed> getPassedByExamination(Long userId, Short year, Short examination){
        return passedDao.findPassedByExamination(userId, year, examination);
    }

    public void delete(Passed passed) {
        passedDao.delete(passed);
    }

    public void removeByYear(Long userId, Short years) {

        List<Passed> passedList = passedDao.findPassedByYear(userId, years);

        for (Passed passed : passedList) {
            passedDao.delete(passed);
        }

    }

    public boolean passedExists(Long userId, Course course) {
        return passedDao.passedExists(userId, course);
    }

    public Passed getPassedByUserAndCourse(Long userId, Long courseId) {
        return passedDao.findPassedByUserAndCourse(userId, courseId);
    }

    public Passed updatePassedGrade(Long userId, Long courseId, Short year,Short examination, Double grade){
        Passed passed = getPassedByUserAndCourse(userId, courseId);
        passed.setGrade(grade);

        return passedDao.update(passed);
    }

    public void deletePassedCourse(Long userId, Long courseId) throws IllegalArgumentException {
        Passed passed = getPassedByUserAndCourse(userId, courseId);
        List<Declared> declaredList = declaredService.getAllDeclaredByUser(userId);
        List<Passed> passedList = getPassedByUser(userId);


        for (Declared course : declaredList) {
            if (prerequisiteService.exist(course.getCourse(), passed.getCourse().getName())){
                course.setAvailable(true);
                declaredService.updateDeclared(course);
            }
        }

        for (Passed course : passedList) {
            if (prerequisiteService.exist(course.getCourse(), passed.getCourse().getName())){
                course.setAvailable(true);
                passedDao.update(course);
            }
        }

        if (passed != null) {
            passedDao.delete(passed);
        } else {
            throw new IllegalArgumentException("Passed is empty");
        }


    }

    public void deleteByCourse(Long courseId) {
        List<Passed> passedList = passedDao.findPassedByCourse(courseId);

        if(passedList != null || !passedList.isEmpty()){
            for(Passed passed : passedList) {
                passedDao.delete(passed);
            }
        }
    }

    public void deleteByUser(Long id) {
        List<Passed> passedList = passedDao.findPassedByUser(id);
        if(passedList != null || !passedList.isEmpty()){
            for(Passed passed : passedList) {
                passedDao.delete(passed);
            }
        }
    }


}
