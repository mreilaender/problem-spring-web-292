package com.example.problempoc;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithAnonymousUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@SpringBootTest
class ProblemPocApplicationTests {

  @Autowired
  WebApplicationContext webApplicationContext;

  MockMvc mockMvc;
  @BeforeEach
  void setUp() {
    this.mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
        .apply(springSecurity())
        .build();
  }

  @Test
  @WithAnonymousUser
  void withAnonymousUser_shouldReturn400() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/test"))
        .andExpect(status().isUnauthorized());
  }

  @Test
  void withoutUser_shouldReturn400() throws Exception {
    mockMvc.perform(MockMvcRequestBuilders.get("/test"))
        .andExpect(status().isUnauthorized());
  }
}
