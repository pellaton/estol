package com.github.pellaton.estol.context;

import java.util.List;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * The {@link ApplicationContextLoader} default implementation used in ESTOL unless instructed otherwise. This 
 * implementation loads a {@link AnnotationConfigApplicationContext} that is suitable for Springs Java-based  
 * context configuration style. 
 * 
 * @author Michael Pellaton
 */
public class DefaultApplicationContextLoader implements ApplicationContextLoader {

  @Override
  public ConfigurableApplicationContext loadApplicationContext(List<Class<?>> configurationClasses) {
    AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
    context.register(configurationClasses.toArray(new Class<?>[configurationClasses.size()]));
    context.refresh();
    return context;
  }
}
