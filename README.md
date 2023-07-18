# Hydra Dev Challenge

Application created in order to demonstrate knowledge in Java, Spring and test implementation.

# Technologies Used

 - [Java 17](https://www.oracle.com/java/technologies/downloads/#java17)
 - [Spring Boot](https://spring.io/projects/spring-boot)
 - [Spring Data JPA](https://spring.io/projects/spring-data-jpa)
 - [Spring REST Docs](https://docs.spring.io/spring-restdocs/docs/2.0.7.RELEASE/reference/html5/)
 - [H2 Database](https://www.h2database.com/html/main.html)
 - [Maven 3](https://maven.apache.org/index.html)
 - [Lombok](https://projectlombok.org/)
 - [JUnit 5](https://junit.org/junit5/)
 - [Flyway](https://flywaydb.org/)

## How to run the Application

There are several ways to run a Spring Boot application on your local machine.  If you are using an IDE (such as Intellij or Eclipse), just run the main method in the **[**ChallengeApplication**](https://github.com/rogerioallves/hydra-dev/blob/main/src/main/java/com/hydra/dev/application/ChallengeApplication.java)** class.
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

    mvn spring-boot:run

## How to run Application Tests

Here we can also run the tests in various ways.  When using an IDE (like Intellij or Eclipse) just run the main class [**PriceControllerTest**](https://github.com/rogerioallves/hydra-dev/blob/main/src/test/java/com/hydra/dev/application/PriceControllerTest.java)
Alternatively you can use the [Spring Boot Maven plugin](https://docs.spring.io/spring-boot/docs/current/reference/html/build-tool-plugins-maven-plugin.html) like so:

    mvn surefire:test

## How to run Flyway Migration

Flyway execution was added at the start of the application, but if you want to run it manually, just run this command:

    mvn flyway:migration
