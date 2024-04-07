package gr.di.mystudiesplanservice.rest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/")
public class LoginController {

    @Value("${appUrl}")
    private String appUrl;

    @GetMapping()
    public void method(HttpServletResponse httpServletResponse) {
            httpServletResponse.setHeader("Location", appUrl + "/#/auth");
            httpServletResponse.setStatus(302);
    }
}
