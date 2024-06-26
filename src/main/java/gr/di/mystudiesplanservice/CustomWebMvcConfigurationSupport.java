package gr.di.mystudiesplanservice;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import java.util.List;

@Configuration
public class CustomWebMvcConfigurationSupport extends WebMvcConfigurationSupport {
    @Override
    protected void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // put the jackson converter to the front of the list so that application/json content-type strings will be treated as JSON
        converters.add(new MappingJackson2HttpMessageConverter());
        // and probably needs a string converter too for text/plain content-type strings to be properly handled
        converters.add(new StringHttpMessageConverter());   // could be removed
    }

}
