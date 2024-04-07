package gr.di.mystudiesplanservice.declared;

import gr.di.mystudiesplanservice.advisor.Advisor;
import gr.di.mystudiesplanservice.advisor.AdvisorService;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.passed.Passed;
import gr.di.mystudiesplanservice.passed.PassedService;
import gr.di.mystudiesplanservice.prerequisite.Prerequisite;
import gr.di.mystudiesplanservice.prerequisite.PrerequisiteService;
import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class DeclaredService {
    @Autowired
    private DeclaredDao declaredDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PassedService passedService;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private PrerequisiteService prerequisiteService;

    public int create(Long userId, Short year, Short examination, List<Course> courseList) throws IllegalArgumentException {
        User user = userService.getUserById(userId);
        Advisor active = advisorService.getActiveAdvisor(userId);
        for (Course course : courseList) {
            Declared declared = new Declared();

            declared.setUser(user);
            declared.setYear(year);
            declared.setExamination(examination);
            declared.setAdvisor(active);

            declared.setCourse(course);
            declaredDao.create(declared);
        }

        List<Declared> declaredList = declaredDao.findDeclared(userId, active.getId());
        List<Passed> passedList = passedService.getPassedByUser(userId);


        for (Declared declared : declaredList) {                                                                                       // na ginetai to available true sto passed kai sto declared an exoun dilwthei ola ta proapaitoumena tous
            int err = 0;
            List<Prerequisite> prerequisites = prerequisiteService.getPrerequisiteByCourse(declared.getCourse());
            for (Prerequisite prerequisite : prerequisites) {
                if (!declaredDao.declaredExists(userId, prerequisite.getPrerequisite(), active.getId())) {                                                    // an to proapaitoumeno tou dilwmenou den einai sta dilwmena
                    err = 1;
                    break;
                } else {
                    Declared tempDeclared = getDeclaredByUserAndCourse(userId, prerequisite.getPrerequisite().getId());
                    if (tempDeclared.getYear() > declared.getYear()) {
                        err = 2;
                        break;
                    } else if (tempDeclared.getYear() == declared.getYear()) {
                        if (tempDeclared.getExamination() >= declared.getExamination()) {
                            err = 2;
                            break;
                        }
                    }
                }
            }
            if (err == 0) {
                declared.setAvailable(false);
                declaredDao.update(declared);
            }
            if (err == 2) {
                declared.setAvailable(true);
                declaredDao.update(declared);
            }
            if (err == 1) {
                err = 0;
                for (Prerequisite prerequisite : prerequisites) {
                    if (!passedService.passedExists(userId, prerequisite.getPrerequisite())) {
                        err = 1;
                        declared.setAvailable(true);
                        declaredDao.update(declared);
                        break;
                    } else {
                        Passed tempPassed = passedService.getPassedByUserAndCourse(userId, prerequisite.getPrerequisite().getId());
                        if (tempPassed.getYear() > declared.getYear()) {
                            err = 2;
                            declared.setAvailable(true);
                            declaredDao.update(declared);
                            break;
                        } else if (tempPassed.getYear() == declared.getYear()) {
                            if (tempPassed.getExamination() >= declared.getExamination()) {
                                err = 2;
                                declared.setAvailable(true);
                                declaredDao.update(declared);
                                break;
                            }
                        }
                    }
                }
                if (err == 0) {
                    declared.setAvailable(false);
                    declaredDao.update(declared);
                }
            }
        }

        for (Passed passed : passedList) {                                                                                       // na ginetai to available true sto passed kai sto declared an exoun dilwthei ola ta proapaitoumena tous
            int err = 0;
            List<Prerequisite> prerequisites = prerequisiteService.getPrerequisiteByCourse(passed.getCourse());
            for (Prerequisite prerequisite : prerequisites) {
                if (!declaredDao.declaredExists(userId, prerequisite.getPrerequisite(), active.getId())) {                                                    // an to proapaitoumeno tou dilwmenou den einai sta dilwmena
                    err = 1;
                    break;
                } else {
                    Declared tempDeclared = getDeclaredByUserAndCourse(userId, prerequisite.getPrerequisite().getId());
                    if (tempDeclared.getYear() > passed.getYear()) {
                        err = 2;
                        break;
                    } else if (tempDeclared.getYear() == passed.getYear()) {
                        if (tempDeclared.getExamination() >= passed.getExamination()) {
                            err = 2;
                            break;
                        }
                    }
                }
            }
            if (err == 0) {
                passed.setAvailable(false);
                passedService.updatePassed(passed);
            }
            if (err == 2) {
                passed.setAvailable(true);
                passedService.updatePassed(passed);
            }
            if (err == 1) {
                err = 0;
                for (Prerequisite prerequisite : prerequisites) {
                    if (!passedService.passedExists(userId, prerequisite.getPrerequisite())) {
                        err = 1;
                        passed.setAvailable(true);
                        passedService.updatePassed(passed);
                        break;
                    } else {
                        Passed tempPassed = passedService.getPassedByUserAndCourse(userId, prerequisite.getPrerequisite().getId());
                        if (tempPassed.getYear() > passed.getYear()) {
                            err = 2;
                            passed.setAvailable(true);
                            passedService.updatePassed(passed);
                            break;
                        } else if (tempPassed.getYear() == passed.getYear()) {
                            if (tempPassed.getExamination() >= passed.getExamination()) {
                                err = 2;
                                passed.setAvailable(true);
                                passedService.updatePassed(passed);
                                break;
                            }
                        }
                    }
                }
                if (err == 0) {
                    passed.setAvailable(false);
                    passedService.updatePassed(passed);
                }
            }
        }


        return 0;
    }

    public List<Declared> getDeclared(Long userId) {
        Advisor advisor = advisorService.getActiveAdvisor(userId);
        return declaredDao.findDeclared(userId, advisor.getId());
    }

    public List<Course> getDeclaredByUser(Long userId) throws IllegalArgumentException {
        Advisor advisor = advisorService.getActiveAdvisor(userId);
        List<Course> courses = declaredDao.findDeclaredByUser(userId, advisor.getId());
        return courses;
    }

    public Declared getDeclaredByUserAndCourse(Long userId, Long courseId) {
        Advisor advisor = advisorService.getActiveAdvisor(userId);
        return declaredDao.findDeclaredByUserAndCourse(userId, courseId, advisor.getId());
    }

    public List<Double> getGradeGoalsByUser(Long userId) {

        return declaredDao.findGradeGoalsByUser(userId);
    }

    public void delete(Long userId, Long courseId) throws IllegalArgumentException {
        Advisor advisor = advisorService.getActiveAdvisor(userId);
        Declared declared = getDeclaredByUserAndCourse(userId, courseId);
        List<Declared> declaredList = declaredDao.findDeclared(userId, advisor.getId());
        List<Passed> passedList = passedService.getPassedByUser(userId);


        for (Declared course : declaredList) {
            if (prerequisiteService.exist(course.getCourse(), declared.getCourse().getName())) {
                course.setAvailable(true);
                declaredDao.update(course);
            }
        }

        for (Passed course : passedList) {
            if (prerequisiteService.exist(course.getCourse(), declared.getCourse().getName())) {
                course.setAvailable(true);
                passedService.updatePassed(course);
            }
        }

        if (declared != null) {
            declaredDao.delete(declared);
        } else {
            throw new IllegalArgumentException("Declared is empty");
        }


    }

    public void deleteByCourse(Long courseId) {
        List<Declared> declaredList = declaredDao.findDeclaredByCourse(courseId);

        if (declaredList != null || !declaredList.isEmpty()) {
            for (Declared declared : declaredList) {
                declaredDao.delete(declared);
            }
        }
    }

    public void deleteByUser(Long userId) {
        List<Advisor> advisors = advisorService.getAdvisorsByUser(userId);
        for (Advisor advisor : advisors) {
            List<Declared> declaredList = declaredDao.findDeclared(userId, advisor.getId());
            if (declaredList != null || !declaredList.isEmpty()) {
                for (Declared declared : declaredList) {
                    declaredDao.delete(declared);
                }
            }
        }
    }

    public void deleteByAdvisorId(Long userId, Long advisorId) {
        List<Declared> declaredList = declaredDao.findDeclared(userId, advisorId);
        if (declaredList != null || !declaredList.isEmpty()) {
            for (Declared declared : declaredList) {
                declaredDao.delete(declared);
            }
        }
    }

    public void updateGradeGoal(Long userId, Long courseId, Double gradeGoal) {


        Declared declared = getDeclaredByUserAndCourse(userId, courseId);
        declared.setGradegoal(gradeGoal);

        declaredDao.update(declared);


    }

    public void updateDeclaredGrade(Long userId, Long courseId, Short year, Short examination, Double grade) {

        Declared declared = getDeclaredByUserAndCourse(userId, courseId);
        declared.setGrade(grade);

        declaredDao.update(declared);
    }

    public void passDeclared(Long userId, Short year, Short examination) {
        Advisor advisor = advisorService.getActiveAdvisor(userId);
        List<Declared> declaredList = declaredDao.findPassedDeclaredByExamination(userId, year, examination, advisor.getId());
        List<Passed> passedList = passedService.getPassedByExamination(userId, year, examination);

        for (Declared declared : declaredList) {
            if (declared.getGrade() != null) {
                if (declared.getGrade() >= 5) {
                    Passed passed = passedService.create(userId, declared.getCourse().getId(), year, examination, declared.getGrade());
                    List<Declared> declaredToRemove = declaredDao.findAllDeclaredByUserAndCourse(userId, declared.getCourse().getId());
                    for (Declared removeDeclared : declaredToRemove) {
                        declaredDao.delete(removeDeclared);
                    }

                } else {
                    delete(userId, declared.getCourse().getId());
                }


            } else {
                delete(userId, declared.getCourse().getId());
            }

        }

        for (Passed passed : passedList) {
            if (passed.getGrade() < 5) {
                passedService.delete(passed);
            }
        }

        advisorService.changeActiveYearAndExamination(userId, year, examination);


    }

    public void passedToDeclared(Long userId, Short year, Short examination) {
        List<Passed> passedList = passedService.getPassedByExamination(userId, year, examination);
        for (Passed passed : passedList) {
            Declared declared = new Declared();
            declared.setCourse(passed.getCourse());
            declared.setYear(passed.getYear());
            declared.setExamination(passed.getExamination());
            declared.setUser(passed.getUser());
            declared.setAdvisor(advisorService.getActiveAdvisor(userId));
            declared.setGrade(passed.getGrade());
            declaredDao.create(declared);
            passedService.delete(passed);
        }
    }

    public void removeByYear(Long userId, Long advisorId, Short years) {

        List<Declared> declaredList = declaredDao.findDeclaredByYear(userId, advisorId, years);

        for (Declared declared : declaredList) {
            declaredDao.delete(declared);
        }

    }

    public void updateDeclared(Declared declared) {
        declaredDao.update(declared);
    }

    public List<Declared> getAllDeclaredByUser(Long userId) {

        return declaredDao.findAllDeclaredByUser(userId);
    }
}
