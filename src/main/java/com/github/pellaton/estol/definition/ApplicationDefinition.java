package com.github.pellaton.estol.definition;

import java.util.List;

import org.springframework.context.annotation.Configuration;

import com.github.pellaton.estol.context.ApplicationContextLoader;

/**
 * {@link ApplicationDefinition}s define the fundamental setup for the bootstrap of the application. This means it
 * defines which Spring application context configuration is fed into which application context loader to load an
 * application.
 * 
 * @author Michael Pellaton
 */
public interface ApplicationDefinition {

  /**
   * Defines the Spring application context configuration classes. These follow Springs Java-based configuration style
   * and are therefore annotated with {@link Configuration}.
   * 
   * @return the {@link Configuration} classes containing the Spring bean definitions of this application.
   * Implementations may not return {@code null}.
   * 
   * @see Configuration
   */
  List<Class<?>> getConfigurationClasses();

  /**
   * Defines the instance of {@link ApplicationContextLoader} to be used to load the Spring application context for the
   * application.
   * 
   * @return the {@link ApplicationContextLoader} to use to load the Spring application context for the application.
   * Implementations may not return {@code null}.
   */
  ApplicationContextLoader getApplicationContextLoader();
}
