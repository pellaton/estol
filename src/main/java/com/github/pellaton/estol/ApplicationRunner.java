package com.github.pellaton.estol;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;

import com.github.pellaton.estol.context.ApplicationContextLoader;
import com.github.pellaton.estol.definition.ApplicationDefinition;
import com.github.pellaton.estol.definition.DefaultApplicationDefinition;
import com.github.pellaton.estol.examples.HelloWorldConfiguration;
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
   * @param configurationClasses the spring application context configuration classes. If no configuration class is 
   * specified, the hello world example application is started instead. 
   */
  public static void runApplication(Class<?>... configurationClasses) {
    DefaultApplicationDefinition applicationDefinition;
    if (configurationClasses == null || configurationClasses.length == 0) {
      applicationDefinition = new DefaultApplicationDefinition(HelloWorldConfiguration.class);
    } else {
      applicationDefinition = new DefaultApplicationDefinition(configurationClasses);
    }
    
    runApplication(applicationDefinition);
  }
  
  /**
   * Runs the application defined in the {@link ApplicationDefinition} passed. 
   * 
   * @param applicationDefinition the {@link ApplicationDefinition} defining the application to run  
   */
  public static void runApplication(ApplicationDefinition applicationDefinition) {
    ApplicationContextLoader applicationContextLoader = applicationDefinition.getApplicationContextLoader();
    List<Class<?>> configurationClassList = applicationDefinition.getConfigurationClasses();
    
    try (ConfigurableApplicationContext applicationContext = applicationContextLoader.loadApplicationContext(
        configurationClassList)) {
      applicationContext.getBean(ApplicationExecutable.class).run();
    }
  }
}
