package com.example.problempoc.infrastructure.config.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.zalando.problem.spring.web.advice.security.SecurityProblemSupport;

@Configuration
@EnableWebSecurity
@Import(SecurityProblemSupport.class)
public class SecurityConfig {

  @Autowired
  private SecurityProblemSupport problemSupport;

  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    //@formatter:off
    http
        .exceptionHandling()
          .authenticationEntryPoint(problemSupport)
          .accessDeniedHandler(problemSupport)
        .and()
          .httpBasic()
        .and()
          .authorizeRequests()
            .anyRequest().authenticated()
        .and()
          .csrf()
          .disable()
        .anonymous()
          .disable();
    return http.build();
    //@formatter:on
  }

  @Bean
  public UserDetailsService users() {
    UserDetails user = User.builder()
        .username("test")
        .password("{bcrypt}$2a$10$ewIN4e4as8QdRCSsACN16efDMbLULZbxwcR4fEZg9YRDif5omjMzu")
        .roles("USER", "ADMIN")
        .build();
    return new InMemoryUserDetailsManager(user);
  }
}
