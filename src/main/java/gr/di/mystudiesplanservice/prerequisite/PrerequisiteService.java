package gr.di.mystudiesplanservice.prerequisite;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PrerequisiteService {

    @Autowired
    private PrerequisiteDao prerequisiteDao;

    @Autowired
    private CourseService courseService;

    public List<Prerequisite> getPrerequisiteByCourse(Course course) {
        return prerequisiteDao.findPrerequisiteByCourse(course);
    }

    public List<Prerequisite> getPrerequisites() {
        return prerequisiteDao.findPrerequisites();
    }

    public void deleteByCourse(Long courseId) {
        Course course = courseService.getCourseById(courseId);
        List<Prerequisite> prerequisites = prerequisiteDao.findPrerequisiteByCourse(course);
        List<Prerequisite> isPrerequisite = prerequisiteDao.findCoursesIsPrerequisite(course);

        if(prerequisites != null || !prerequisites.isEmpty()) {
            for(Prerequisite prerequisite : prerequisites) {
                prerequisiteDao.delete(prerequisite);
            }
        }

        if(isPrerequisite != null || !isPrerequisite.isEmpty()) {
            for(Prerequisite prerequisite : isPrerequisite) {
                prerequisiteDao.delete(prerequisite);
            }
        }
    }

    public boolean exist(Course course, String prerequisite) {
        if (prerequisiteDao.prerequisiteExists(course, prerequisite) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int create(Course course, List<String> prerequisiteList) throws IllegalArgumentException {

        for (String courseName : prerequisiteList) {
            if (courseName.isEmpty()) {
                continue;
            }
            Prerequisite preCourse = new Prerequisite();
            Course prerequisite = courseService.getCourseByName(courseName);
            preCourse.setCourse(course);
            preCourse.setPrerequisite(prerequisite);
            prerequisiteDao.create(preCourse);
        }

        return 0;
    }

    public int edit(Course course, List<String> prerequisiteList) throws IllegalArgumentException {
        List<Prerequisite> prerequisites = prerequisiteDao.findPrerequisiteByCourse(course);


        for (Prerequisite prerequisite : prerequisites) {
            if(!prerequisiteList.contains(prerequisite)) {
                prerequisiteDao.delete(prerequisite);
            }
        }

        for (String courseName : prerequisiteList) {
            if (courseName.isEmpty()) {
                continue;
            }
            boolean exist = exist(course, courseName);
            if (exist) {
                continue;
            }
//            if (courseService.getCourseByName(courseName) != null) {
            Prerequisite preCourse = new Prerequisite();
            Course prerequisite = courseService.getCourseByName(courseName);
            preCourse.setCourse(course);
            preCourse.setPrerequisite(prerequisite);
            prerequisiteDao.create(preCourse);
//            } else {
//                return 1;
//            }
        }

        return 0;
    }
}
