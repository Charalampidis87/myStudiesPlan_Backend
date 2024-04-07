package gr.di.mystudiesplanservice.recommended;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class RecommendedService {

    @Autowired
    private RecommendedDao recommendedDao;

    @Autowired
    private CourseService courseService;

    public List<Recommended> getRecommendedByCourse(Course course) {
        return recommendedDao.findRecommendedByCourse(course);
    }

    public List<Recommended> getRecommended() {
        return recommendedDao.findRecommended();
    }

    public void deleteByCourse(Long courseId) {
        Course course = courseService.getCourseById(courseId);
        List<Recommended> recommendedList = recommendedDao.findRecommendedByCourse(course);
        List<Recommended> isRecommended = recommendedDao.findCoursesIsRecommended(course);

        if(recommendedList != null || !recommendedList.isEmpty()) {
            for(Recommended recommended : recommendedList) {
                recommendedDao.delete(recommended);
            }
        }

        if(isRecommended != null || !isRecommended.isEmpty()) {
            for(Recommended recommended : isRecommended) {
                recommendedDao.delete(recommended);
            }
        }
    }

    public boolean exist(Course course, String recommended) {
        if (recommendedDao.recommendedExists(course, recommended) != null) {
            return true;
        } else {
            return false;
        }
    }

    public int create(Course course, List<String> recommendedList) throws IllegalArgumentException {

        for (String courseName : recommendedList) {
            if (courseName.isEmpty()) {
                continue;
            }
            Recommended recCourse = new Recommended();
            Course prerequisite = courseService.getCourseByName(courseName);
            recCourse.setCourse(course);
            recCourse.setRecommended(prerequisite);
            recommendedDao.create(recCourse);
        }

        return 0;
    }

    public int edit(Course course, List<String> recommendedList) throws IllegalArgumentException {
        List<Recommended> recommended = recommendedDao.findRecommendedByCourse(course);


        for (Recommended recommendedCourse : recommended) {
            if(!recommendedList.contains(recommendedCourse)) {
                recommendedDao.delete(recommendedCourse);
            }
        }

        for (String courseName : recommendedList) {
            if (courseName.isEmpty()) {
                continue;
            }
            boolean exist = exist(course, courseName);
            if (exist) {
                continue;
            }
//            if (courseService.getCourseByName(courseName) != null) {
            Recommended recCourse = new Recommended();
            Course recommendedCourse = courseService.getCourseByName(courseName);
            recCourse.setCourse(course);
            recCourse.setRecommended(recommendedCourse);
            recommendedDao.create(recCourse);
//            } else {
//                return 1;
//            }
        }

        return 0;
    }
}
