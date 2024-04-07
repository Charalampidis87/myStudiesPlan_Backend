package gr.di.mystudiesplanservice.rest;

import gr.di.mystudiesplanservice.advisor.Advisor;
import gr.di.mystudiesplanservice.advisor.AdvisorService;
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
@RequestMapping("advisor")
public class AdvisorController {

    @Autowired
    private AdvisorService advisorService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<Advisor>> getAdvisors() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        List<Advisor> advisors = advisorService.getAdvisorsByUser(userId);
        return new ResponseEntity<>(advisors, HttpStatus.OK);
    }

    @GetMapping("create")
    public ResponseEntity<String> createAdvisor(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            advisorService.create(userId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Advisor has been created.", HttpStatus.OK);
    }

    @GetMapping("delete")
    public ResponseEntity<String> deleteAdvisor(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            advisorService.delete(userId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Advisor has been deleted.", HttpStatus.OK);
    }

    @GetMapping("addyear")
    public ResponseEntity<String> addYear(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            advisorService.addYear(userId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Year has been increased", HttpStatus.OK);
    }

    @GetMapping("removeyear")
    public ResponseEntity<String> removeYear(){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            advisorService.removeYear(userId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Year has been decreased", HttpStatus.OK);
    }

    @PostMapping("changeactive")
    public ResponseEntity<String> changeActiveAdvisor(@RequestParam Long advisorId){
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Long userId = userService.getUserId(username);
        try {
            advisorService.changeActiveAdvisor(userId, advisorId);
        } catch (Exception e) {
            return new ResponseEntity<>("Server could not process the request.", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return new ResponseEntity<>("Advisor has been changed.", HttpStatus.OK);
    }

}
