package com.github.pellaton.estol.context;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class TestConfiguration {

  @Bean
  String getValue() {
    return "JUNIT";
  }
}