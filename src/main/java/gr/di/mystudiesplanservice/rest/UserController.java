package gr.di.mystudiesplanservice.rest;

import gr.di.mystudiesplanservice.passed.PassedService;
import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private PassedService passedService;

    @GetMapping("login")
    public ResponseEntity<User> login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getUser(username);
        if (user == null) {
            return new ResponseEntity<>(user, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("getaveragegrade")
    public ResponseEntity<Double> getAverageGrade() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        Double average = userService.getAverageGrade(userId);

        return new ResponseEntity<>(average, HttpStatus.OK);
    }

    @GetMapping("getaveragegradegoal")
    public ResponseEntity<Double> getAverageGradeGoal() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        Double average = userService.getAverageGradeGoal(userId);

        return new ResponseEntity<>(average, HttpStatus.OK);
    }

    @PostMapping("updatedirection")
    public ResponseEntity<String> updatedirection(@RequestParam String direction) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        try {
            userService.updateDirection(username, direction);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Direction changed succesfully", HttpStatus.OK);
    }

    @GetMapping("getpiechartsparts")
    public ResponseEntity<List<Integer>> getGradesSum() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Integer> PieChartParts = new ArrayList<Integer>();
        PieChartParts.add(passedService.getGoodGradesSum(userId));
        PieChartParts.add(passedService.getVeryGoodGradesSum(userId));
        PieChartParts.add(passedService.getExcellentGradesSum(userId));
        return new ResponseEntity<>(PieChartParts, HttpStatus.OK);
    }

    @PostMapping("editUser")
    public ResponseEntity<String> editUser(@RequestParam String direction) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            userService.editUser(userId, direction);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("User has been edited successfully!", HttpStatus.OK);
    }
}
