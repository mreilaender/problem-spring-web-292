package com.example.problempoc;

import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

public class NoSecurityContextFactory implements WithSecurityContextFactory<WithoutSecurityContext> {

  @Override
  public SecurityContext createSecurityContext(WithoutSecurityContext annotation) {
    return new SecurityContextImpl(null);
  }
}
