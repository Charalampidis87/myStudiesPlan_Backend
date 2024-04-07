package gr.di.mystudiesplanservice.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import gr.di.mystudiesplanservice.declared.Declared;
import gr.di.mystudiesplanservice.declared.DeclaredService;
import gr.di.mystudiesplanservice.labprofessor.LabProfessor;
import gr.di.mystudiesplanservice.labprofessor.LabProfessorService;
import gr.di.mystudiesplanservice.passed.Passed;
import gr.di.mystudiesplanservice.passed.PassedService;
import gr.di.mystudiesplanservice.prerequisite.Prerequisite;
import gr.di.mystudiesplanservice.prerequisite.PrerequisiteService;
import gr.di.mystudiesplanservice.professor.Professor;
import gr.di.mystudiesplanservice.professor.ProfessorService;
import gr.di.mystudiesplanservice.recommended.Recommended;
import gr.di.mystudiesplanservice.recommended.RecommendedService;
import gr.di.mystudiesplanservice.specialization.Specialization;
import gr.di.mystudiesplanservice.specialization.SpecializationService;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("course")
public class CourseController {
    @Autowired
    private DeclaredService declaredService;

    @Autowired
    private PassedService passedService;

    @Autowired
    private UserService userService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private ProfessorService professorService;

    @Autowired
    private LabProfessorService labProfessorService;

    @Autowired
    private PrerequisiteService prerequisiteService;

    @Autowired
    private RecommendedService recommendedService;

    @Autowired
    private SpecializationService specializationService;

    @GetMapping()
    public ResponseEntity<List<Course>> getCourses() {
        List<Course> courses = courseService.getCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("getdeclared")
    public ResponseEntity<List<Declared>> getDeclared() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Declared> courses = declaredService.getDeclared(userId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("getgradegoals")
    public ResponseEntity<List<Double>> getGradeGoals() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Double> gradegoals = declaredService.getGradeGoalsByUser(userId);

        return new ResponseEntity<>(gradegoals, HttpStatus.OK);
    }


    @PostMapping("createpassed")
    public ResponseEntity<String> createPassed(@RequestParam Long courseId, @RequestParam Short examination, @RequestParam Double grade, @RequestParam Short year) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            passedService.create(userId, courseId, year, examination, grade);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Course is passed succesfully", HttpStatus.OK);
    }

    @PostMapping("addDeclaredGrade")
    public ResponseEntity<String> addDeclaredGrade(@RequestParam Long courseId, @RequestParam Short examination, @RequestParam Double grade, @RequestParam Short year) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            declaredService.updateDeclaredGrade(userId, courseId, year, examination, grade);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Course is passed succesfully", HttpStatus.OK);
    }

    @PostMapping("creategradegoal")
    public ResponseEntity<String> createGradeGoal(@RequestParam Long courseId, @RequestParam Double gradeGoal) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            declaredService.updateGradeGoal(userId, courseId, gradeGoal);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("GradeGoal is passed succesfully", HttpStatus.OK);
    }


    @DeleteMapping("deletedeclared")
    public ResponseEntity<String> deleteDeclared(@RequestParam Long courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            declaredService.delete(userId, courseId);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Declared not exists!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Notification has been deleted.", HttpStatus.OK);
    }

    @GetMapping("getpassed")
    public ResponseEntity<List<Passed>> getPassed() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Passed> courses = passedService.getPassedByUser(userId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @GetMapping("professors")
    public ResponseEntity<List<Professor>> getProfessors() {
        List<Professor> professors = professorService.getProfessors();
        return new ResponseEntity<>(professors, HttpStatus.OK);
    }

    @GetMapping("labprofessors")
    public ResponseEntity<List<LabProfessor>> getLabProfessors() {
        List<LabProfessor> labProfessors = labProfessorService.getLabProfessors();
        return new ResponseEntity<>(labProfessors, HttpStatus.OK);
    }

    @GetMapping("prerequisites")
    public ResponseEntity<List<Prerequisite>> getPrerequisites() {
        List<Prerequisite> prerequisites = prerequisiteService.getPrerequisites();
        return new ResponseEntity<>(prerequisites, HttpStatus.OK);
    }

    @GetMapping("recommended")
    public ResponseEntity<List<Recommended>> getRecommended() {
        List<Recommended> recommended = recommendedService.getRecommended();
        return new ResponseEntity<>(recommended, HttpStatus.OK);
    }

    @GetMapping("specializations")
    public ResponseEntity<List<Specialization>> getSpecializations() {
        List<Specialization> specializations = specializationService.getSpecializations();
        return new ResponseEntity<>(specializations, HttpStatus.OK);
    }

    @GetMapping("available")
    public ResponseEntity<List<Course>> getAvailable(@RequestParam Short year, @RequestParam Short examination) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Course> available = courseService.getAvailable(userId, year, examination);
        return new ResponseEntity<>(available, HttpStatus.OK);
    }

    @PostMapping("declare")
    public ResponseEntity<String> createDeclared(@RequestParam Short year, @RequestParam Short examination, @RequestParam String courses) throws IOException {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        ObjectMapper mapper = new ObjectMapper();
        List<Course> courseList = mapper.readValue(courses, new TypeReference<List<Course>>() {
        });
        try {
            declaredService.create(userId, year, examination, courseList);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }


        return new ResponseEntity<>("Course is declared succesfully", HttpStatus.OK);
    }

    @PostMapping("passDeclared")
    public ResponseEntity<String> passDeclared(@RequestParam Short year, @RequestParam Short examination){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            declaredService.passDeclared(userId, year, examination);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Course is Passed.", HttpStatus.OK);
    }

    @PostMapping("passedToDeclared")
    public ResponseEntity<String> passedToDeclared(@RequestParam Short year, @RequestParam Short examination){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            declaredService.passedToDeclared(userId, year, examination);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Examination finished.", HttpStatus.OK);
    }

    @PostMapping("addPassedGrade")
    public ResponseEntity<String> addPassedGrade(@RequestParam Long courseId, @RequestParam Short examination, @RequestParam Double grade, @RequestParam Short year) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            passedService.updatePassedGrade(userId, courseId, year, examination, grade);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Grade is passed successfully", HttpStatus.OK);
    }

    @DeleteMapping("deletepassed")
    public ResponseEntity<String> addPassedGrade(@RequestParam Long courseId) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            passedService.deletePassedCourse(userId, courseId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Passed is deleted successfully", HttpStatus.OK);
    }

    @GetMapping("getUserCourses")
    public ResponseEntity<List<Course>> getUserCourses(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Course> courses = courseService.getUserCourses(userId);
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }
}
