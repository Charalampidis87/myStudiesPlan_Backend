package gr.di.mystudiesplanservice.security;

import org.jasig.cas.client.session.SingleSignOutFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.cas.authentication.CasAuthenticationProvider;
import org.springframework.security.cas.web.CasAuthenticationEntryPoint;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@EnableWebSecurity
public class SecurityConfiguration {



    @Configuration
    @Order(1)
    public static class LoginSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        @Override
        public void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.inMemoryAuthentication()
                    .withUser("admin").password(getPasswordEncoder().encode("7Ei&5*ZqMt")).roles("ADMIN");
        }

        @Bean
        public PasswordEncoder getPasswordEncoder() {
            return new BCryptPasswordEncoder();
        }


        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .logout().logoutUrl("/admin/logout").permitAll()
                    .logoutSuccessHandler((request, response, authentication) -> {response.setStatus(HttpServletResponse.SC_ACCEPTED);})
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
            ;

            http.csrf().disable()
                    .antMatcher("/admin/**").authorizeRequests().anyRequest().hasRole("ADMIN")
                    .and()
                    .antMatcher("/admin/*").authorizeRequests().anyRequest().hasRole("ADMIN")
                    .and()
                    .httpBasic()
            ;
        }
    }

    @Configuration
    @Order(2)
    public static class JWTSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

        private CasAuthenticationProvider casAuthenticationProvider;
        private CasAuthenticationEntryPoint casAuthenticationEntryPoint;


        @Autowired
        public void CASSecurityConfiguration(CasAuthenticationProvider casAuthenticationProvider, CasAuthenticationEntryPoint casAuthenticationEntryPoint) {
            this.casAuthenticationProvider = casAuthenticationProvider;
            this.casAuthenticationEntryPoint = casAuthenticationEntryPoint;

        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) {
            auth.authenticationProvider(this.casAuthenticationProvider);
        }

        @Override
        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .logout().permitAll()
                    .logoutSuccessHandler((request, response, authentication) -> {response.setStatus(HttpServletResponse.SC_CREATED);})
                    .clearAuthentication(true)
                    .invalidateHttpSession(true)
            ;

            http
                    .authorizeRequests()
                    .antMatchers("/login/cas").permitAll()
                    .anyRequest().authenticated()
                    .and().httpBasic().authenticationEntryPoint(this.casAuthenticationEntryPoint)
            ;


        }
    }
}
