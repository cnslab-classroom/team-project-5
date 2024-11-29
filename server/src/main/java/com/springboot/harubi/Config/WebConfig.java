package com.springboot.harubi.Config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    String[] accessURL = {"http://localhost:8080", "https://localhost:8080"};
    
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins(accessURL)
                .allowedMethods(accessURL)
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")  // 허용할 Http 메서드
                .allowedHeaders("*")
                .maxAge(3000);  // 원하는 시간만큼 pre-flight 리퀘스트를 캐싱
    }
}
