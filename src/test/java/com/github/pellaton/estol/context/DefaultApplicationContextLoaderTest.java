package com.github.pellaton.estol.context;

import java.util.Collections;

import org.junit.Test;
import org.springframework.context.ConfigurableApplicationContext;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

/**
 * Tests {@link DefaultApplicationContextLoader}.
 */
public class DefaultApplicationContextLoaderTest {

  @Test(expected = IllegalArgumentException.class)
  public void loadApplicationContextWithNullConfigurationClasses() {
    new DefaultApplicationContextLoader().loadApplicationContext(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadApplicationContextWithEmptyConfigurationClasses() {
    new DefaultApplicationContextLoader().loadApplicationContext(Collections.<Class<?>>emptyList());
  }

  @Test(expected = IllegalArgumentException.class)
  public void loadApplicationContextWithOnlyNullConfigurationClasses() {
    new DefaultApplicationContextLoader().loadApplicationContext(Collections.<Class<?>>singletonList(null));
  }

  @Test
  public void loadApplicationContextAllOK() {
    ConfigurableApplicationContext context = new DefaultApplicationContextLoader().loadApplicationContext(
        Collections.<Class<?>>singletonList(TestConfiguration.class));
    assertNotNull(context);
    assertEquals("JUNIT", context.getBean(String.class));
  }
}
