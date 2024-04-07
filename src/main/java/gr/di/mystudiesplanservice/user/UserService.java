package gr.di.mystudiesplanservice.user;

import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.declared.Declared;
import gr.di.mystudiesplanservice.declared.DeclaredService;
import gr.di.mystudiesplanservice.passed.Passed;
import gr.di.mystudiesplanservice.passed.PassedService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.RoundingMode;
import java.text.DecimalFormat;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserDao userDao;
    @Autowired
    private PassedService passedService;
    @Autowired
    private DeclaredService declaredService;

    public User getUser(String username) throws IllegalArgumentException {
        User user = userDao.findUserByUsername(username);
        if (user == null) {
            return null;
        }
        return user;
    }

    public Long getUserId(String username){
        User user = userDao.findUserByUsername(username);
        return user.getId();
    }

    public User getUserById(Long id) {
        User user = userDao.read(id);
        return user;
    }

    public Double getAverageGrade(Long userId) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        List<Passed> Passedlist = passedService.getPassedByUser(userId);
        List<Course> Declaredlist = declaredService.getDeclaredByUser(userId);

        Declared declared;

        Double average = 0.0;
        Integer sumEcts = 0;

        for (Passed p : Passedlist) {
            if (p.getGrade() < 5) {
                continue;
            }

            average = average + p.getGrade() * p.getCourse().getEcts();
            sumEcts = sumEcts + p.getCourse().getEcts();
        }

        for (Course c : Declaredlist) {
            declared = declaredService.getDeclaredByUserAndCourse(userId, c.getId());

            if (declared.getGrade() == null || declared.getGrade() < 5) {
                continue;
            }

            average = average + declared.getGrade() * c.getEcts();
            sumEcts = sumEcts + c.getEcts();
        }

        if (sumEcts == 0) {
            return 0.0;
        }

        average = average / sumEcts;
        double decimal = average % 0.01;
        if (decimal >= 0.005) {
            average = (average - decimal) + 0.01;
        } else {
            average = average - decimal;
        }

        return average;
    }

    public Double getAverageGradeGoal(Long id) {
        DecimalFormat df = new DecimalFormat("#.##");
        df.setRoundingMode(RoundingMode.CEILING);

        List<Passed> Passedlist = passedService.getPassedByUser(id);
        List<Course> Declaredlist = declaredService.getDeclaredByUser(id);

        Declared declared;

        Double average = 0.0;
        Integer sumEcts = 0;

        for (Passed p : Passedlist) {
            average = average + p.getGrade() * p.getCourse().getEcts();
            sumEcts = sumEcts + p.getCourse().getEcts();
        }

        for (Course c : Declaredlist) {
            declared = declaredService.getDeclaredByUserAndCourse(id, c.getId());

            if (declared.getGradegoal() == null) {
                continue;
            }
            average = average + declared.getGradegoal() * c.getEcts();
            sumEcts = sumEcts + c.getEcts();
        }

        if (sumEcts == 0) {
            return 0.0;
        }

        average = average / sumEcts;
        double decimal = average % 0.01;
        if (decimal >= 0.005) {
            average = (average - decimal) + 0.01;
        } else {
            average = average - decimal;
        }

        return average;
    }

    public void updateDirection(String username, String direction) {
        User user = getUser(username);
        user.setDirection(direction);
        userDao.update(user);
    }

    public User editUser(Long userId, String direction) {
        User previous = userDao.read(userId);
        previous.setDirection(direction);
        return userDao.update(previous);
    }

    public List<User> getUsers() {
        return userDao.findAllUsers();
    }

    public void deleteUser(Long id) {
        userDao.delete(id);
    }
}
