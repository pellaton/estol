package com.github.pellaton.estol;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.github.pellaton.estol.executable.ApplicationExecutable;

/**
 * {@link @Configuration} class used to test {@link ApplicationRunner}.
 */
@Configuration
class TestConfigurationClass {

  private static boolean runCalled = false;

  @Bean
  ApplicationExecutable applicationExecutable() {
    return new ApplicationExecutable() {

      @Override
      public void run() {
        runCalled = true;
      }
    };
  }

  static boolean runCalled() {
    return runCalled;
  }

  public static void ressetRunCalled() {
    runCalled = false;
  }
}