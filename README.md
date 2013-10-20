#ESTOL [![Build Status](https://travis-ci.org/pellaton/estol.png?branch=master)](https://travis-ci.org/pellaton/estol)

**Easily build and run Spring Framwork based Java SE applications with ESTOL!**

**What about spring boot?**<br/>
While the general aim of Spring Boot and ESTOL are similar, ESTOL is based on Spring 3.2 
and it follows a more conservative design approach with way less under-the-hood magic.


**ESTOL?**<br/>
The aviation lingo acronym ESTOL  stands for <i>Extremely Short Take-Off and Landing</i>
which describes the aim of this project in terms of Java SE main application development 
quite accurately.

##Quick Start

**Add the following dependency to your Maven POM:**

    <dependencies>
      <dependency>
        <groupId>com.github.pellaton.estol</groupId>
        <artifactId>estol</artifactId>
        <version>1.0.0</version>
      </dependency>
    </dependencies>

**Write a Spring <code>@Configuration</code> class containing a bean of type <code>ApplicationExecutable</code>:**

    @Configuration
    public class HelloWorldConfiguration {
  
      @Bean
      public ApplicationExecutable executable() {
        return new ApplicationExecutable() {
          public void run() {
            System.out.println("Hello World!");
          }
        };
      }
    }

**Write the main class of your application feeding the configuration class into the <code>ApplicationRunner</code>:**

    public class HelloWorld {
      public static void main(String[] args) {
        ApplicationRunner.runApplication(HelloWorldConfiguration.class);
      } 
    }

