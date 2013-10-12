package com.github.pellaton.estol.examples;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pellaton.estol.executable.ApplicationExecutable;

/**
 * This example defines a hello world example.
 */
@Configuration
public class HelloWorldConfiguration {

  @Bean
  ApplicationExecutable executable() {
    return new ApplicationExecutable() {
      
      @Override
      public void run() {
        System.out.println("\nHello World!\n");
      }
    };
  }
}
