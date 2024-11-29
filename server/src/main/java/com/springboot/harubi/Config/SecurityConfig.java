package com.springboot.harubi.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private String[] possibleAccess = { "/api/auth/signup", "api/auth/login",
                    "/api/error", "/api", "/error", "/auth/**" };

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable)  // HTTP Basic 인증 비활성화
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.IF_REQUIRED)
                )
                .formLogin(formLogin ->
                        formLogin
                                .loginPage("/auth/login")  // 로그인 페이지 경로 설정
                                .permitAll()
                )
                .authorizeHttpRequests(auth ->
                        auth
                                .requestMatchers(possibleAccess).permitAll()  // 배열로 허용 경로 설정
                                .anyRequest().authenticated()
                );
        return http.build();
    }
}
