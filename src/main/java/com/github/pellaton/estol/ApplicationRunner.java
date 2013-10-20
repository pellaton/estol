package com.github.pellaton.estol;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;

import com.github.pellaton.estol.context.ApplicationContextLoader;
import com.github.pellaton.estol.definition.ApplicationDefinition;
import com.github.pellaton.estol.definition.DefaultApplicationDefinition;
import com.github.pellaton.estol.executable.ApplicationExecutable;

/**
 * Main entry point for running ESTOL-based applications.
 * 
 * @author Michael Pellaton
 */
public class ApplicationRunner {

  /**
   * Runs the application using the Spring beans defined in the configuration classes passed. Please note that the
   * {@link DefaultApplicationDefinition} is used and that the configuration classes must define exactly one Spring
   * bean of type {@link ApplicationExecutable}.
   * 
   * @param configurationClasses the spring application context configuration classes; must not be {@code null}
   * @throws IllegalArgumentException in case no configuration class is passed
   */
  public static void runApplication(Class<?>... configurationClasses) {
    DefaultApplicationDefinition applicationDefinition;
    if (configurationClasses == null || configurationClasses.length == 0) {
      throw new IllegalArgumentException("At least one configuration class must be provided.");
    } else {
      applicationDefinition = new DefaultApplicationDefinition(configurationClasses);
    }

    runApplication(applicationDefinition);
  }

  /**
   * Runs the application defined in the {@link ApplicationDefinition} passed.
   * 
   * @param applicationDefinition the {@link ApplicationDefinition} defining the application to run; must not be
   * {@code null}
   * @throws IllegalArgumentException if no application definition is passed as argument
   * @throws IllegalStateException if the application definition returns a {@code null} application context loader
   * @throws IllegalStateException if the application definition returns a {@code null} or empty configuration class
   * list
   */
  public static void runApplication(ApplicationDefinition applicationDefinition) {
    if (applicationDefinition == null) {
      throw new IllegalArgumentException("The 'applicationDefinition' argument is required but was null.");
    }

    ApplicationContextLoader applicationContextLoader = applicationDefinition.getApplicationContextLoader();
    if (applicationContextLoader == null) {
      throw new IllegalStateException("The ApplicationDefinition returned a null application context loader.");
    }

    List<Class<?>> configurationClassList = applicationDefinition.getConfigurationClasses();
    if (configurationClassList == null || configurationClassList.isEmpty()) {
      throw new IllegalStateException("The ApplicationDefinition returned a null or empty configuration classes list.");
    }

    try (ConfigurableApplicationContext applicationContext = applicationContextLoader.loadApplicationContext(
        configurationClassList)) {
      applicationContext.getBean(ApplicationExecutable.class).run();
    }
  }
}
