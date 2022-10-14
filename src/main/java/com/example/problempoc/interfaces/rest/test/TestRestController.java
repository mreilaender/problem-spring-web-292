package com.example.problempoc.interfaces.rest.test;

import com.example.problempoc.infrastructure.config.spring.SecurityConfig;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestRestController {

  @GetMapping
  public void get() {
    SecurityContext securityContext = SecurityContextHolder.getContext();

    Authentication authentication = securityContext.getAuthentication();

    return;
  }
}
