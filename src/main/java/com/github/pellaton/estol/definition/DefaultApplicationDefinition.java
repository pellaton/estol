/*
 * Copyright (C) 2013 by Netcetera AG.
 * All rights reserved.
 *
 * The copyright to the computer program(s) herein is the property of Netcetera AG, Switzerland.
 * The program(s) may be used and/or copied only with the written permission of Netcetera AG or
 * in accordance with the terms and conditions stipulated in the agreement/contract under which 
 * the program(s) have been supplied.
 */
package com.github.pellaton.estol.definition;

import java.util.ArrayList;
import java.util.List;

import com.github.pellaton.estol.context.ApplicationContextLoader;
import com.github.pellaton.estol.context.DefaultApplicationContextLoader;

/**
 * The {@link ApplicationDefinition} default implementation used in ESTOL unless instructed otherwise.
 * 
 * @author Michael Pellaton
 */
public class DefaultApplicationDefinition implements ApplicationDefinition {

  private final ApplicationContextLoader applicationContextLoader = new DefaultApplicationContextLoader();
  private final List<Class<?>> configurationClasses;
  

  /**
   * Constructor.
   * 
   * @param configurationClasses the configuration classes that define the Spring application context.
   * 
   * @throws IllegalArgumentException if not at least one configuration class is provided
   */
  public DefaultApplicationDefinition(Class<?>... configurationClasses) {
    this.configurationClasses = new ArrayList<>();
    if (configurationClasses == null || configurationClasses.length == 0) {
      throw new IllegalArgumentException("At least one configuration class must be provided.");
    }
    for (Class<?> configurationClass : configurationClasses) {
      if (configurationClass != null) {
        this.configurationClasses.add(configurationClass);
      }
    }
    
    if (this.configurationClasses.isEmpty()) {
      throw new IllegalArgumentException("At least one non-null configuration class must be provided.");
    }
  }
  
  
  /**
   * This default implementation uses the name of the first configuration class name as application name.
   * 
   * {@inheritDoc}
   */
  @Override
  public String getApplicationName() {
    if (!configurationClasses.isEmpty()) {
      return configurationClasses.get(0).getSimpleName();
    }
    return "application";
  }

  @Override
  public List<Class<?>> getConfigurationClasses() {
    return this.configurationClasses;
  }

  /**
   * This default implementation uses a {@link DefaultApplicationContextLoader}.
   * 
   * {@inheritDoc}
   */
  @Override
  public ApplicationContextLoader getApplicationContextLoader() {
    return this.applicationContextLoader;
  }
}
