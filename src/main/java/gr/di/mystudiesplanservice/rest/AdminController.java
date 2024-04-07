package gr.di.mystudiesplanservice.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import gr.di.mystudiesplanservice.advisor.Advisor;
import gr.di.mystudiesplanservice.advisor.AdvisorService;
import gr.di.mystudiesplanservice.course.Course;
import gr.di.mystudiesplanservice.course.CourseService;
import gr.di.mystudiesplanservice.declared.DeclaredService;
import gr.di.mystudiesplanservice.labprofessor.LabProfessor;
import gr.di.mystudiesplanservice.labprofessor.LabProfessorService;
import gr.di.mystudiesplanservice.passed.PassedService;
import gr.di.mystudiesplanservice.prerequisite.PrerequisiteService;
import gr.di.mystudiesplanservice.professor.Professor;
import gr.di.mystudiesplanservice.professor.ProfessorService;
import gr.di.mystudiesplanservice.recommended.RecommendedService;
import gr.di.mystudiesplanservice.specialization.Specialization;
import gr.di.mystudiesplanservice.specialization.SpecializationService;
import gr.di.mystudiesplanservice.user.User;
import gr.di.mystudiesplanservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.List;

@Controller
@RequestMapping("admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private DeclaredService declaredService;

    @Autowired
    private PassedService passedService;

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

    @Value("${appUrl}")
    private String appUrl;


    @GetMapping("/")
    public void loginAdmin(HttpServletResponse httpServletResponse) {
        httpServletResponse.setHeader("Location", appUrl + "/#/admin");
        httpServletResponse.setStatus(302);
    }


    @GetMapping("users")
    public ResponseEntity<List<User>> getUsers() {
        List<User> users = userService.getUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @PostMapping("createCourse")
    public ResponseEntity<String> createCourse(@RequestParam String name, @RequestParam String code, @RequestParam Short ects, @RequestParam Short semester,
                                               @RequestParam String type, @RequestParam String direction, @RequestParam String specialization,
                                               @RequestParam Short theory, @RequestParam Short extra, @RequestParam Short lab, @RequestParam String professor,
                                               @RequestParam String labProfessor, @RequestParam String prerequisite, @RequestParam String recommended, @RequestParam boolean double_exam,
                                               @RequestParam boolean offered) throws IOException {

        name = name.trim();
        code = code.trim();
        professor = professor.trim();
        labProfessor = labProfessor.trim();

        ObjectMapper mapper = new ObjectMapper();
        List<Professor> professorList = mapper.readValue(professor, new TypeReference<List<Professor>>() {
        });
        List<LabProfessor> labProfessorList = mapper.readValue(labProfessor, new TypeReference<List<LabProfessor>>() {
        });
        List<String> prerequisiteList = mapper.readValue(prerequisite, new TypeReference<List<String>>() {
        });
        List<String> recommendedList = mapper.readValue(recommended, new TypeReference<List<String>>() {
        });
        List<Specialization> specializationList = mapper.readValue(specialization, new TypeReference<List<Specialization>>() {
        });
        try {
            Course course = courseService.create(name, code, ects, semester, type, direction, theory, extra, lab, double_exam, offered);
            professorService.create(course, professorList);
            labProfessorService.create(course, labProfessorList);
            prerequisiteService.create(course, prerequisiteList);
            recommendedService.create(course, recommendedList);
            specializationService.create(course, specializationList);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Course has been created.", HttpStatus.OK);
    }

    @PostMapping("editCourse")
    public ResponseEntity<String> editCourse(@RequestParam Long id, @RequestParam String name, @RequestParam String code, @RequestParam Short ects, @RequestParam Short semester,
                                             @RequestParam String type, @RequestParam String direction, @RequestParam String specialization,
                                             @RequestParam Short theory, @RequestParam Short extra, @RequestParam Short lab, @RequestParam String professor,
                                             @RequestParam String labProfessor, @RequestParam String prerequisite, @RequestParam String recommended, @RequestParam boolean double_exam,
                                             @RequestParam boolean offered) throws IOException {

        name = name.trim();
        code = code.trim();
        professor = professor.trim();
        labProfessor = labProfessor.trim();

        ObjectMapper mapper = new ObjectMapper();
        List<Professor> professorList = mapper.readValue(professor, new TypeReference<List<Professor>>() {
        });
        List<LabProfessor> labProfessorList = mapper.readValue(labProfessor, new TypeReference<List<LabProfessor>>() {
        });
        List<String> prerequisiteList = mapper.readValue(prerequisite, new TypeReference<List<String>>() {
        });
        List<String> recommendedList = mapper.readValue(recommended, new TypeReference<List<String>>() {
        });
        List<Specialization> specializationList = mapper.readValue(specialization, new TypeReference<List<Specialization>>() {
        });
        try {
            Course course = courseService.edit(id, name, code, ects, semester, type, direction, theory, extra, lab, double_exam, offered);
            professorService.edit(course, professorList);
            labProfessorService.edit(course, labProfessorList);
            prerequisiteService.edit(course, prerequisiteList);
            recommendedService.edit(course, recommendedList);
            specializationService.edit(course, specializationList);
        } catch (IllegalArgumentException e) {
            return new ResponseEntity<>("Prerequisite course not exists.", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>("Course has been edited.", HttpStatus.OK);
    }

    @GetMapping("adminCourses")
    public ResponseEntity<List<Course>> getAdminCourses() {
        List<Course> courses = courseService.getAdminCourses();
        return new ResponseEntity<>(courses, HttpStatus.OK);
    }

    @DeleteMapping("deleteCourse{id}")
    public ResponseEntity<String> deleteCourse(@PathVariable("id") Long id) {
        labProfessorService.deleteByCourse(id);
        professorService.deleteByCourse(id);
        passedService.deleteByCourse(id);
        prerequisiteService.deleteByCourse(id);
        recommendedService.deleteByCourse(id);
        specializationService.deleteByCourse(id);
        declaredService.deleteByCourse(id);
        courseService.deleteCourse(id);
        return new ResponseEntity<>("Course has been deleted successfully!", HttpStatus.OK);
    }

    @DeleteMapping(value = "/deleteUser/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        declaredService.deleteByUser(id);
        passedService.deleteByUser(id);
        advisorService.deleteByUser(id);
        userService.deleteUser(id);
        return new ResponseEntity<>("User has been deleted successfully!", HttpStatus.OK);
    }

}

