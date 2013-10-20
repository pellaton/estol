package com.github.pellaton.estol.definition;

import java.util.List;

import org.junit.Test;
import org.springframework.context.annotation.Configuration;

import com.github.pellaton.estol.context.ApplicationContextLoader;
import com.github.pellaton.estol.context.DefaultApplicationContextLoader;

import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Tests {@link DefaultApplicationDefinition}.
 */
public class DefaultApplicationDefinitionTest {

  @Test(expected = IllegalArgumentException.class)
  public void constructorWithNullConfigurationClasses() {
    new DefaultApplicationDefinition((Class<?>)null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorWithEmptyConfigurationClasses() {
    new DefaultApplicationDefinition(new Class<?>[]{});
  }

  @Test(expected = IllegalArgumentException.class)
  public void constructorWithOnlyNullConfigurationClasses() {
    new DefaultApplicationDefinition(new Class<?>[]{null, null});
  }

  @Test
  public void constructorAllFine() {
    new DefaultApplicationDefinition(new Class<?>[]{TestConfiguration.class});
  }

  @Test
  public void getConfigurationClasses() {
    DefaultApplicationDefinition definition = new DefaultApplicationDefinition(new Class<?>[]{TestConfiguration.class});
    List<Class<?>> configurationClasses = definition.getConfigurationClasses();
    assertThat(configurationClasses, not(empty()));
    assertEquals(configurationClasses.get(0), TestConfiguration.class);
  }

  @Test
  public void getApplicationContextLoader() {
    DefaultApplicationDefinition definition = new DefaultApplicationDefinition(new Class<?>[]{TestConfiguration.class});
    ApplicationContextLoader loader = definition.getApplicationContextLoader();
    assertNotNull(loader);
    assertThat(loader, instanceOf(DefaultApplicationContextLoader.class));
  }


  @Configuration
  private static class TestConfiguration { /* empty on purpose */  }

}
