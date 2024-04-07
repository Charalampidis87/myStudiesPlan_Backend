package gr.di.mystudiesplanservice.course;

import gr.di.mystudiesplanservice.declared.Declared;
import gr.di.mystudiesplanservice.declared.DeclaredService;
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
import java.util.Comparator;
import java.util.List;

@Service
@Transactional
public class CourseService {
    @Autowired
    private CourseDao courseDao;

    @Autowired
    private UserService userService;

    @Autowired
    private PassedService passedService;

    @Autowired
    private DeclaredService declaredService;

    @Autowired
    private PrerequisiteService prerequisiteService;

    public Course getCourseByName(String name) {
        return courseDao.findCourseByName(name);
    }

    public Course create(String name, String code, Short ects, Short semester,
                         String type, String direction, Short theory, Short extra,
                         Short lab, boolean double_exam, boolean offered) {
        Course course = new Course();
        course.setName(name);
        course.setCode(code);
        course.setEcts(ects);
        course.setSemester(semester);
        course.setType(type);
        course.setDirection(direction);
        course.setDouble_exam(double_exam);
        course.setOffered(offered);
        course.setTheory(theory);
        course.setExtra(extra);
        course.setLab(lab);

        return courseDao.create(course);
    }

    public Course edit(Long id, String name, String code, Short ects, Short semester,
                       String type, String direction, Short theory, Short extra,
                       Short lab, boolean double_exam, boolean offered) {
        Course course = courseDao.read(id);
        course.setName(name);
        course.setCode(code);
        course.setEcts(ects);
        course.setSemester(semester);
        course.setType(type);
        course.setDirection(direction);
        course.setDouble_exam(double_exam);
        course.setOffered(offered);
        course.setTheory(theory);
        course.setExtra(extra);
        course.setLab(lab);

        return courseDao.update(course);

    }

    public List<Course> getCourses() {
        return courseDao.findCourses();
    }

    public Course getCourseById(Long id) {
        Course course = courseDao.read(id);
        return course;
    }

    public List<Course> getAvailable(Long userId, Short year, Short examination) {
        User user = userService.getUserById(userId);
        List<Passed> passed = passedService.getPassedByUser(userId);
        List<Declared> declared = declaredService.getDeclared(userId);

        List<Course> courses = courseDao.findCourses();
        List<Course> toRemove = new ArrayList<>();

        for (Course course : courses) {
            if(!course.isOffered()) {
                continue;
            }
            int err = 1;

            for (int i = 0; i < passed.size(); i++) {
                if (passed.get(i).getCourse().getId() == course.getId()) {
                    err = 0;
                    break;
                }
            }

            for (int i = 0; i < declared.size(); i++) {
                if (declared.get(i).getCourse().getId() == course.getId()) {
                    err = 0;
                    break;
                }
            }

            if (err == 0) {
                toRemove.add(course);
                continue;
            }

            if (examination == 0) {                                                                         //Elegxei an einai sti swsti eksetastiki (0: Xeimerino, 1: Earino)
                if (course.getSemester() % 2 == 0 && !course.isDouble_exam()) {
                    toRemove.add(course);
                    continue;
                }
            }

            if (examination == 1) {
                if (course.getSemester() % 2 == 1 && !course.isDouble_exam()) {
                    toRemove.add(course);
                    continue;
                }
            }


            List<Prerequisite> prerequisites = prerequisiteService.getPrerequisiteByCourse(course);
            for (Prerequisite prerequisite : prerequisites) {
                err = 0;
                for (int i = 0; i < passed.size(); i++) {

                    if (passed.get(i).getCourse().getId() == prerequisite.getPrerequisite().getId()) {                      //Elegxei an exoun perastei ta proapaitoumena
                        err = 1;
                        if (passed.get(i).getYear() > year) {                                               //Elegxei pote exoun perastei ta proapaitoumena
                            err = 2;
                            break;
                        }
//                        else if (passed.get(i).getYear() == year && examination == 2 && passed.get(i).getExamination() == 1) {
//                            err = 2;
//                            break;
//                        }
                        else if (passed.get(i).getYear() < year) {
                            continue;
                        } else {
                            if (passed.get(i).getCourse().getSemester() % 2 == 1 && course.getSemester() % 2 == 0) {                                           //xeimerinou to proapaitoumeno kai earinou auto pou theloume na dhlwsoume

                                continue;
                            } else {
                                if (passed.get(i).getCourse().getSemester() % 2 == 1 && course.getSemester() % 2 == 1 && course.isDouble_exam() && (examination == 1 || examination == 2)) {
                                    continue;
                                } else {
                                    err = 2;
                                    break;
                                }
                            }
                        }
                    }
//                    if (err == 0) {                                                                         //An den exei perastei estw kai ena, stamataei tin epanalipsi
//                        break;
//                    }
                }
                if (err == 1) {
                    continue;
                } else if (err == 2) {
                    break;
                }

                for (int i = 0; i < declared.size(); i++) {

                    if (declared.get(i).getCourse().getId() == prerequisite.getPrerequisite().getId()) {                    //Elegxei an exoun dilwthei ta proapaitoumena
                        err = 1;
                        if (declared.get(i).getYear() > year) {                                             //Elegxei pote dilwthikan ta proapaitoumena
                            err = 2;
                            break;
                        }
//                        else if (declared.get(i).getYear() == year && examination == 2 && declared.get(i).getExamination() == 1) {
//                            err = 2;
//                            break;
//                        }
                        else if (declared.get(i).getYear() < year) {
                            continue;
                        } else {
                            if (declared.get(i).getCourse().getSemester() % 2 == 1 && course.getSemester() % 2 == 0) {                                           //xeimerinou to proapaitoumeno kai earinou auto pou theloume na dhlwsoume

                                continue;
                            } else {
                                if (declared.get(i).getCourse().getSemester() % 2 == 1 && course.getSemester() % 2 == 1 && course.isDouble_exam() && (examination == 1 || examination == 2)) {
                                    continue;
                                } else {
                                    err = 2;
                                    break;
                                }
                            }
                        }


                    }
//                    if (err == 0) {                                                                         //An den exei dilwthei estw kai ena, stamataei tin epanalipsi
//                        break;
//                    }
                }
                if (err == 0 || err == 2) {
                    break;
                }
            }
            if (err == 0 || err == 2) {
                toRemove.add(course);
                continue;
            }
        }

        courses.removeAll(toRemove);

        if (courses == null || courses.isEmpty()) {
            return new ArrayList<>();
        }

        courses.sort(Comparator.comparing(Course::getName));
        return courses;
    }


    public List<Course> getUserCourses(Long userId) {

        List<Course> declaredCourses = declaredService.getDeclaredByUser(userId);
        List<Course> passedCourses = passedService.getPassed(userId);
        List<Course> userCourses = new ArrayList<>();

        userCourses.addAll(declaredCourses);
        userCourses.addAll(passedCourses);

        return userCourses;
    }

    public void deleteCourse(Long id) {
        courseDao.delete(id);
    }

    public List<Course> getAdminCourses() {
        return courseDao.findAdminCourses();
    }

}
