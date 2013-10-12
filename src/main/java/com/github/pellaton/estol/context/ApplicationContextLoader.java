package com.github.pellaton.estol.context;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * Interface to components loading Spring application contexts.
 * 
 * @author Michael Pellaton
 */
public interface ApplicationContextLoader {

  /**
   * Loads the Spring application context.
   * 
   * @param configurationClasses the spring application context configuration classes 
   * @return the application context
   */
  ConfigurableApplicationContext loadApplicationContext(List<Class<?>> configurationClasses);
}
