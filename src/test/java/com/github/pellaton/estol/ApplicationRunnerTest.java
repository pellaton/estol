package com.github.pellaton.estol;

import java.util.Collections;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.github.pellaton.estol.context.ApplicationContextLoader;
import com.github.pellaton.estol.context.DefaultApplicationContextLoader;
import com.github.pellaton.estol.definition.ApplicationDefinition;

import static org.junit.Assert.assertTrue;

/**
 * Tests {@link ApplicationRunner}.
 */
public class ApplicationRunnerTest {


  @Before
  public void resetMocks() {
    TestConfigurationClass.ressetRunCalled();
  }


  @Test(expected = IllegalArgumentException.class)
  public void runApplicationWithNullConfigClass() {
    ApplicationRunner.runApplication((Class<?>)null);
  }


  @Test(expected = IllegalArgumentException.class)
  public void runApplicationWithEmptyConfigClasses() {
    ApplicationRunner.runApplication(new Class<?>[]{});
  }

  @Test(expected = IllegalArgumentException.class)
  public void runApplicationWithNullApplicationDefinition() {
    ApplicationRunner.runApplication((ApplicationDefinition)null);
  }

  @Test(expected = IllegalStateException.class)
  public void runApplicationWithNullApplicationContextLoader() {
    ApplicationRunner.runApplication(new ApplicationDefinition() {

      @Override
      public List<Class<?>> getConfigurationClasses() {
        return null;
      }

      @Override
      public ApplicationContextLoader getApplicationContextLoader() {
        return null;
      }
    });
  }

  @Test(expected = IllegalStateException.class)
  public void runApplicationWithNullConfigrationClasses() {
    ApplicationRunner.runApplication(new ApplicationDefinition() {

      @Override
      public List<Class<?>> getConfigurationClasses() {
        return null;
      }

      @Override
      public ApplicationContextLoader getApplicationContextLoader() {
        return new DefaultApplicationContextLoader();
      }
    });
  }

  @Test(expected = IllegalStateException.class)
  public void runApplicationWithEmptyConfigrationClasses() {
    ApplicationRunner.runApplication(new ApplicationDefinition() {

      @Override
      public List<Class<?>> getConfigurationClasses() {
        return Collections.emptyList();
      }

      @Override
      public ApplicationContextLoader getApplicationContextLoader() {
        return new DefaultApplicationContextLoader();
      }
    });
  }

  @Test
  public void runApplicationWithValidApplicationDefinition() {
    ApplicationRunner.runApplication(new ApplicationDefinition() {

      Class<?> configuration = TestConfigurationClass.class;

      @Override
      public List<Class<?>> getConfigurationClasses() {
        return Collections.<Class<?>>singletonList(this.configuration);
      }

      @Override
      public ApplicationContextLoader getApplicationContextLoader() {
        return new DefaultApplicationContextLoader();
      }
    });

    assertTrue(TestConfigurationClass.runCalled());
  }

  @Test
  public void runApplicationWithValidConfigurationClass() {
    ApplicationRunner.runApplication(TestConfigurationClass.class);
    assertTrue(TestConfigurationClass.runCalled());
  }
}
