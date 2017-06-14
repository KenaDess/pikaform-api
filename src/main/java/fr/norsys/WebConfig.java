package fr.norsys;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/*@Configuration
@EnableWebMvc*/
public class WebConfig extends WebMvcConfigurerAdapter {
/*
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/login")
                .allowedOrigins("http://127.0.0.1:8081")
                .allowedMethods("POST", "GET", "OPTIONS");
    }*/
}