package com.github.pellaton.estol.context;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;

/**
 * Interface to components loading Spring application contexts.
 * 
 * @author Michael Pellaton
 * 
 * @see ConfigurableApplicationContext
 */
public interface ApplicationContextLoader {

  /**
   * Loads the Spring application context.
   * 
   * @param configurationClasses the spring application context configuration classes
   * @return the application context
   * 
   * @see ConfigurableApplicationContext
   * @throws IllegalArgumentException if the the configuration classes list is {@code null}, empty or contains
   * {@code null} entries only
   */
  ConfigurableApplicationContext loadApplicationContext(List<Class<?>> configurationClasses);
}
