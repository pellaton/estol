#ESTOL [![Build Status](https://travis-ci.org/pellaton/estol.png?branch=master)](https://travis-ci.org/pellaton/estol)
**Easily build and run Spring Framwork based Java SE applications with ESTOL.**

**What about spring boot?**<br/>
While the general aim of [Spring Boot](http://projects.spring.io/spring-boot/) and ESTOL are similar, ESTOL is based on Spring 3.2 
and it follows a more conservative design approach with way less under-the-hood magic.


**ESTOL?**<br/>
The aviation lingo acronym [ESTOL](http://de.wikipedia.org/wiki/STOL) stands for <i>Extremely Short Take-Off and Landing</i>
which describes the aim of this project in terms of Java SE main application development 
quite accurately.

##Quick Start
**Add the following dependency to your Maven POM:**
``` xml
<dependencies>
  <dependency>
    <groupId>com.github.pellaton.estol</groupId>
    <artifactId>estol</artifactId>
    <version>1.0.0</version>
  </dependency>
</dependencies>
```

**Write a Spring <code>[@Configuration](http://docs.spring.io/spring/docs/3.2.4.RELEASE/javadoc-api/org/springframework/context/annotation/Configuration.html)</code> class containing a bean of type <code>[ApplicationExecutable](https://github.com/pellaton/estol/blob/master/src/main/java/com/github/pellaton/estol/executable/ApplicationExecutable.java)</code>:**
``` java
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
```
**Write the main class of your application feeding the configuration class into the <code>[ApplicationRunner](https://github.com/pellaton/estol/blob/master/src/main/java/com/github/pellaton/estol/ApplicationRunner.java)</code>:**
``` java
public class HelloWorld {
  public static void main(String[] args) {
    ApplicationRunner.runApplication(HelloWorldConfiguration.class);
  } 
}
```
